package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.adapter.MineOrderAdapter;
import com.sxw.loan.loanorder.adapter.MineWhiteAdapter;
import com.sxw.loan.loanorder.databinding.ActivityMineorderBinding;
import com.sxw.loan.loanorder.moudle.MineOrderReg;
import com.sxw.loan.loanorder.moudle.MineOrderRet;
import com.sxw.loan.loanorder.moudle.MineWhiteRet;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.pullToRefresh.PullToRefreshBase;
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
 * Created by Administrator on 2017/7/28.
 */

public class MineOrderActivty extends BaseActivity<ActivityMineorderBinding> {
    private static final String TAG = "MineOrderActivty";

    private int userid, page = 1;
    private TabLayout tabLayout;
    private ListView listView;
    private List<MineOrderRet.PageInfoBean.ListBean> mlistBeenorder = new ArrayList<>();
    private List<MineWhiteRet.PageInfoBean.ListBean> mlistBeenwhite = new ArrayList<>();
    private MineOrderAdapter adapter;
    private MineWhiteAdapter mineWhiteAdapter;
    private ListView listwhiteview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mineorder);


        showContentView();
        setTitle("我的订单");



        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                tabLayout.addTab(tabLayout.newTab().setText("我的订单"), 0);
            }
            if (i == 1) {
                tabLayout.addTab(tabLayout.newTab().setText("信用订单"), 1);
            }
        }
        //设置tab的点击监听器
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    page = 1;
                    mlistBeenorder.clear();
                    loanorder(page, userid);
                    bindingView.orderlist.setVisibility(View.VISIBLE);
                    bindingView.whitelist.setVisibility(View.GONE);
                }
                if (tab.getPosition() == 1) {
                    page = 1;
                    mlistBeenwhite.clear();
                    loanwhite(page, userid);
                    bindingView.orderlist.setVisibility(View.GONE);
                    bindingView.whitelist.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        initvieworder();
        initviewwhite();
        loanorder(page, userid);
    }

    //白条a
    private void initviewwhite() {
        bindingView.whitelist.setPullRefreshEnabled(true);
        bindingView.whitelist.setPullLoadEnabled(false);
        bindingView.whitelist.setScrollLoadEnabled(true);
        bindingView.whitelist.setLastUpdatedLabel(new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis())));   // 设置下拉时间
        bindingView.whitelist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mlistBeenwhite.clear();
                page = 1;
                loanwhite(page, userid);
                mineWhiteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                int pages = 1;
                page += pages;
                loanwhite(page, userid);
                mineWhiteAdapter.notifyDataSetChanged();
            }
        });
        listwhiteview = bindingView.whitelist.getRefreshableView();
        listwhiteview.setDivider(null); // ListView 去 item分割线
        listwhiteview.setSelector(getResources().getDrawable(R.color.tran)); // 点击背景透明
        mineWhiteAdapter = new MineWhiteAdapter(this, mlistBeenwhite);
        listwhiteview.setAdapter(mineWhiteAdapter);
        listwhiteview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick: " + mlistBeenwhite.get(position).getOrderId());
                Bundle bundle = new Bundle();
                bundle.putInt("orderid", mlistBeenwhite.get(position).getOrderId());
                startActivity(MineWhiteQDactivity.class, bundle);
            }
        });

    }

    //我得订单
    private void initvieworder() {
        bindingView.orderlist.setPullRefreshEnabled(true);
        bindingView.orderlist.setPullLoadEnabled(false);
        bindingView.orderlist.setScrollLoadEnabled(true);
        bindingView.orderlist.setLastUpdatedLabel(new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis())));   // 设置下拉时间
        bindingView.orderlist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mlistBeenorder.clear();
                page = 1;
                loanorder(page, userid);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                int pages = 1;
                page += pages;
                loanorder(page, userid);
                adapter.notifyDataSetChanged();
            }
        });
        listView = bindingView.orderlist.getRefreshableView();
        listView.setDivider(null); // ListView 去 item分割线
        listView.setSelector(getResources().getDrawable(R.color.tran)); // 点击背景透明
        adapter = new MineOrderAdapter(this, mlistBeenorder);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick: " + mlistBeenorder.get(position).getOrderId());
                Bundle bundle = new Bundle();
                bundle.putInt("orderid", mlistBeenorder.get(position).getOrderId());
                startActivity(MineOrderQDActivty.class, bundle);

            }
        });

    }

    //我得订单
    private void loanorder(Integer page, Integer userid) {
        MineOrderReg mineorder = new MineOrderReg();
        mineorder.setPageNum(page);
        mineorder.setUserId(userid);
        Log.e(TAG, "loanorder: " + new Gson().toJson(mineorder));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.mineorderurl)
                .content(new Gson().toJson(mineorder))
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
                        MineOrderRet mineOrderRet = new Gson().fromJson(response, MineOrderRet.class);
                        if (mineOrderRet.getPageInfo().getList().size() > 0) {
                            mlistBeenorder.addAll(mineOrderRet.getPageInfo().getList());
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

    //白条a
    private void loanwhite(Integer page, Integer userid) {
        MineOrderReg mineorder = new MineOrderReg();
        mineorder.setPageNum(page);
        mineorder.setUserId(userid);
        Log.e(TAG, "loanorder: " + new Gson().toJson(mineorder));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.minewhiteurl)
                .content(new Gson().toJson(mineorder))
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
                        MineWhiteRet mineOrderRet = new Gson().fromJson(response, MineWhiteRet.class);
                        if (mineOrderRet.getPageInfo().getList().size() > 0) {
                            mlistBeenwhite.addAll(mineOrderRet.getPageInfo().getList());
                            Message message = new Message();
                            message.what = 3;
                            myHandler.sendMessage(message);
                        } else {
                            ToastUtils.showToastGravityCenter("没有更多数据");
                            Message message = new Message();
                            message.what = 4;
                            myHandler.sendMessage(message);
                        }
                    }

                });
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    bindingView.orderlist.onPullUpRefreshComplete();
                    bindingView.orderlist.onPullDownRefreshComplete();
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    bindingView.orderlist.onPullUpRefreshComplete();
                    bindingView.orderlist.onPullDownRefreshComplete();
                    adapter.notifyDataSetChanged();
                    break;
                case 3:
                    bindingView.whitelist.onPullUpRefreshComplete();
                    bindingView.whitelist.onPullDownRefreshComplete();
                    mineWhiteAdapter.notifyDataSetChanged();
                    break;
                case 4:
                    bindingView.whitelist.onPullUpRefreshComplete();
                    bindingView.whitelist.onPullDownRefreshComplete();
                    mineWhiteAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
