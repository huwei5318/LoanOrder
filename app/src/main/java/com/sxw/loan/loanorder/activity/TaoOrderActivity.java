package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.adapter.FirstFragmentAdapter;
import com.sxw.loan.loanorder.moudle.FirstRespon;
import com.sxw.loan.loanorder.moudle.OrderVo;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.pullToRefresh.PullToRefreshBase;
import com.sy.alex_library.pullToRefresh.PullToRefreshListView;
import com.sy.alex_library.tools.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Sxw on 2017-07-15.
 */

public class TaoOrderActivity extends BaseActivity {
    private static final String TAG = "TaoOrderActivity";
    PullToRefreshListView listviews;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    //    @BindView(R.id.btn_search)
//    Button btnSearch;
    private ListView listView;
    private FirstFragmentAdapter firstFragmentAdapter;
    private int userid;
    private String productType = "108";
    private int page = 1;
    private List<FirstRespon.PageInfoBean.ListBean> pageInfoBeen = new ArrayList<>();
    private Double minAmount = 0.0, maxAmount = 0.0;//金额
    private Integer minTime = 0, maxTime = 0, pageNum, userId;//期限
    private String qdFlag;
    private String workType = "108";
    private String fund = "108";
    private String security = "108";
    private String pledgeType = "108";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taoorder);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        initview();
//        load(page, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);
    }


    private void initview() {
        listviews = (PullToRefreshListView) findViewById(R.id.listviews);
        listviews.setPullRefreshEnabled(true);
        listviews.setPullLoadEnabled(false);
        listviews.setScrollLoadEnabled(true);
        listviews.setLastUpdatedLabel(new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis())));   // 设置下拉时间
        listviews.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageInfoBeen.clear();
                page = 1;
                load(page, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);
                firstFragmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                int pages = 1;
                page += pages;
                load(page, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);
                firstFragmentAdapter.notifyDataSetChanged();

            }
        });
        listView = listviews.getRefreshableView();
        listView.setDivider(null); // ListView 去 item分割线
        listView.setSelector(getResources().getDrawable(R.color.tran)); // 点击背景透明
        firstFragmentAdapter = new FirstFragmentAdapter(this, pageInfoBeen);
        firstFragmentAdapter.notifyDataSetChanged();
        listView.setAdapter(firstFragmentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (pageInfoBeen.get(position).getIsSuccess().equals("0")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("orderid", pageInfoBeen.get(position).getId());
                    startActivity(TaoQdActivity.class, bundle);
                }
                if (pageInfoBeen.get(position).getIsSuccess().equals("1")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("orderid", pageInfoBeen.get(position).getId());
                    startActivity(TaoQdDetailsActivity.class, bundle);
                }
            }
        });
    }

    private void load(int page,
                      Integer userid,
                      Double minmoney, Double maxmoney,
                      Integer minmonth, Integer maxmonh,
                      String workType, String fund,
                      String security, String pledgeType) {
        OrderVo firstReq = new OrderVo();

        if (minmoney > 0.0) {
            firstReq.setMinAmount(minmoney);
        }
        if (maxmoney > 0.0) {
            firstReq.setMaxAmount(maxmoney);
        }
        if (minmonth > 0) {
            firstReq.setMinTime(minmonth);
        }
        if (maxmonh > 0) {
            firstReq.setMaxTime(maxmonh);
        }
        if (!workType.equals("108")) {
            firstReq.setWorkType(workType);
        }
        if (!fund.equals("108")) {
            firstReq.setFund(fund);
        }
        if (!security.equals("108")) {
            firstReq.setSecurity(security);
        }
        if (!pledgeType.equals("108")) {
            firstReq.setPledgeType(pledgeType);
        }
        firstReq.setPageNum(page);
        firstReq.setUserId(userid);
        Log.e(TAG, "load: " + new Gson().toJson(firstReq));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.tdqdurl)
                .content(new Gson().toJson(firstReq))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("first", e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        FirstRespon listBean = new Gson().fromJson(response, FirstRespon.class);
                        Log.e(TAG, "onResponse: " + response);
                        if (listBean.getPageInfo().getList().size() > 0) {
                            pageInfoBeen.addAll(listBean.getPageInfo().getList());
                            Message message = new Message();
                            message.what = 1;
                            myHandler.sendMessage(message);
                        } else {
                            ToastUtils.showToastGravityCenter("没有更多数据");
                            Message message = new Message();
                            message.what = 2;
                            myHandler.sendMessage(message);
                        }
                    }

                });
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    listviews.onPullUpRefreshComplete();
                    listviews.onPullDownRefreshComplete();
                    firstFragmentAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    listviews.onPullUpRefreshComplete();
                    listviews.onPullDownRefreshComplete();
                    firstFragmentAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    //        , R.id.btn_search
    @OnClick({R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                this.finish();
                break;
//            case R.id.btn_search:
//                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + "aaaaa1111");
        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        pageInfoBeen.clear();
        firstFragmentAdapter = new FirstFragmentAdapter(this, pageInfoBeen);
        firstFragmentAdapter.notifyDataSetChanged();
        listView.setAdapter(firstFragmentAdapter);
        load(page, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);


    }
}
