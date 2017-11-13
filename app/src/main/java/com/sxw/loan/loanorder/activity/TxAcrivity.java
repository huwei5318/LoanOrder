package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.adapter.BtTxAdapter;
import com.sxw.loan.loanorder.databinding.ActivityTxBinding;
import com.sxw.loan.loanorder.moudle.BtData;
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

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Sxw on 2017-07-15.
 */

public class TxAcrivity extends BaseActivity<ActivityTxBinding> {

    private int pagenum = 1, userid;
    private String TAG = "txactivity";
    private List<BtData.PageInfoBean.ListBean> listBeen = new ArrayList<>();
    private BtTxAdapter btTxAdapter;
    private PullToRefreshListView pullToRefreshListView;
    private ListView listView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tx);

        showContentView();
        setTitle("信用订单");




        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        initview();
//        loaddata(pagenum, userid);
    }

    private void initview() {
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.txlistview);
        pullToRefreshListView.setPullRefreshEnabled(true);
        pullToRefreshListView.setPullLoadEnabled(false);
        pullToRefreshListView.setScrollLoadEnabled(true);
        pullToRefreshListView.setLastUpdatedLabel(new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis())));   // 设置下拉时间
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                listBeen.clear();
                pagenum = 1;
                loaddata(pagenum, userid);
                btTxAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                int pages = 1;
                pagenum += pages;
                loaddata(pagenum, userid);
                btTxAdapter.notifyDataSetChanged();
            }
        });
        listView = pullToRefreshListView.getRefreshableView();
        listView.setDivider(null); // ListView 去 item分割线
        listView.setSelector(getResources().getDrawable(R.color.tran));
        btTxAdapter = new BtTxAdapter(this, listBeen);
        listView.setAdapter(btTxAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick: " + listBeen.get(position).getIsSuccess());
                if (listBeen.get(position).getIsSuccess().equals("0")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("orderid", listBeen.get(position).getOrderId());
                    startActivity(BtTxQdActivity.class, bundle);
                }
                if (listBeen.get(position).getIsSuccess().equals("1")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("orderid", listBeen.get(position).getOrderId());
                    startActivity(BtTxQdDetailsActivity.class, bundle);
                }

            }
        });
    }

    private void loaddata(int pagenum, int userid) {
        OrderVo orderVo = new OrderVo();
        orderVo.setPageNum(pagenum);
        orderVo.setUserId(userid);
        OkHttpUtils
                .postString()
                .url(ConstantUrl.btorder)
                .content(new Gson().toJson(orderVo))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onResponse: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: " + response);
                        BtData listBeans = new Gson().fromJson(response, BtData.class);
                        Log.e(TAG, "onResponse: " + response);
                        if (listBeans.getPageInfo().getList().size() > 0) {
                            listBeen.addAll(listBeans.getPageInfo().getList());
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
                    pullToRefreshListView.onPullUpRefreshComplete();
                    pullToRefreshListView.onPullDownRefreshComplete();
                    btTxAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    pullToRefreshListView.onPullUpRefreshComplete();
                    pullToRefreshListView.onPullDownRefreshComplete();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        listBeen.clear();
        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        btTxAdapter = new BtTxAdapter(this, listBeen);
        listView.setAdapter(btTxAdapter);
        loaddata(pagenum, userid);
    }
}
