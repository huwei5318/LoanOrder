package com.sxw.loan.loanorder.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.activity.CityPickerActivity;
import com.sxw.loan.loanorder.activity.ListDetailsActivity;
import com.sxw.loan.loanorder.activity.ListDetailsQdActivity;
import com.sxw.loan.loanorder.activity.WebViewActivity;
import com.sxw.loan.loanorder.adapter.FirstFragmentAdapter;
import com.sxw.loan.loanorder.adapter.RollViewPagerAdapter;
import com.sxw.loan.loanorder.eventBus.EventData;
import com.sxw.loan.loanorder.eventBus.MainBus;
import com.sxw.loan.loanorder.moudle.BannerData;
import com.sxw.loan.loanorder.moudle.FirstRespon;
import com.sxw.loan.loanorder.moudle.OrderBean;
import com.sxw.loan.loanorder.moudle.OrderVo;
import com.sxw.loan.loanorder.moudle.PushReg;
import com.sxw.loan.loanorder.moudle.PushRet;
import com.sxw.loan.loanorder.moudle.PushUpdata;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.MyFragment;
import com.sy.alex_library.pullToRefresh.PullToRefreshBase;
import com.sy.alex_library.pullToRefresh.PullToRefreshListView;
import com.sy.alex_library.tools.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.Call;
import okhttp3.MediaType;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2017-04-11.
 */
public class FirstFragment extends MyFragment {
    PullToRefreshListView listview;
    private View view;
    private ListView listViews;
    private TabLayout tabLayout;
    private RollPagerView rollPagerView;
    private FirstFragmentAdapter firstFragmentAdapter;
    //城市选择返回码
    private static final int REQUEST_CODE_PICK_CITY = 233;
    //顶部变色
    private LinearLayout home_city;
    private TextView txt_city;
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    private String flag = "0";
    private String city = "成都市";
    private String TAG = "firstfragment";
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
    private int userid;
    private RollViewPagerAdapter testNormalAdapter;
    private List<BannerData> banners = new ArrayList<>();
    private String pushcity;
    private TextView first_search;
    private DrawerLayout drawerLayout;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(this.getContext());
//        ButterKnife.bind(this, view);
        if (view == null) {
//            drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.id_drawerLayout);
//            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            view = inflater.inflate(R.layout.fragment_first, null);
            StatusBarUtil.setColor(getActivity(),getResources().getColor(R.color.main_nav_blue),0);

            SharedPreferences sharedPreferences = getContext().getSharedPreferences("jidai",
                    Activity.MODE_PRIVATE);
            userid = sharedPreferences.getInt("userid", 0);
            data();
            tabLayout = (TabLayout) view.findViewById(R.id.TabLayout);
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    tabLayout.addTab(tabLayout.newTab().setText("抢单中"), 0);
                }
                if (i == 1) {
                    tabLayout.addTab(tabLayout.newTab().setText("优质单"), 1);
                }
                if (i == 2) {
                    tabLayout.addTab(tabLayout.newTab().setText("普通单"), 2);
                }
                if (i == 3) {
                    tabLayout.addTab(tabLayout.newTab().setText("全部"), 3);
                }
            }
            //设置tab的点击监听器
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    flag = String.valueOf(tab.getPosition());
                    pageInfoBeen.clear();
                    if (tab.getPosition() == 0) {
                        productType = "108";
                        flag = "0";
                        page = 1;
                        load(city, flag, page, productType, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);
                    }
                    if (tab.getPosition() == 1) {
                        flag = productType = "-1";
                        page = 1;
                        load(city, flag, page, productType, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);

                    }
                    if (tab.getPosition() == 2) {
                        productType = "0";
                        flag = "1";
                        page = 1;
                        load(city, flag, page, productType, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);

                    }
                    if (tab.getPosition() == 3) {
                        flag = productType = "1";
                        page = 1;
                        load(city, flag, page, productType, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);

                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    flag = String.valueOf(tab.getPosition());
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    flag = String.valueOf(tab.getPosition());
                }
            });
//            Tablayouts.setIndicator(getContext(), tabLayout, 60, 60);
            initview();
            home_city = (LinearLayout) view.findViewById(R.id.home_city);
            txt_city = (TextView) view.findViewById(R.id.txt_city);
            //选择城市
            txt_city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(getContext(), CityPickerActivity.class),
                            REQUEST_CODE_PICK_CITY);
                }
            });
            location();
            first_search = (TextView) view.findViewById(R.id.first_search);
            first_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postEvent(new MainBus("1"));
                }
            });
            registerEventBus();
        }
        return view;
    }

    private void load(String city, String flag, int page, String productType,
                      Integer id,
                      Double minmoney, Double maxmoney,
                      Integer minmonth, Integer maxmonh,
                      String workType, String fund,
                      String security, String pledgeType) {
        OrderVo firstReq = new OrderVo();
        firstReq.setCity(city);
        // TODO: 2017-07-19  userid bichuan
        if (flag.equals("0")) {
            firstReq.setQdFlag(flag);
        }
        if (!flag.equals("0")) {
            firstReq.setProductType(productType);
        }
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
        firstReq.setUserId(id);
        Log.e(TAG, "load: " + new Gson().toJson(firstReq));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.firsturl)
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
                    listview.onPullUpRefreshComplete();
                    listview.onPullDownRefreshComplete();
                    firstFragmentAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    listview.onPullUpRefreshComplete();
                    listview.onPullDownRefreshComplete();
                    firstFragmentAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void location() {
        mLocationClient = new AMapLocationClient(getActivity());
        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                        aMapLocation.getLatitude();//获取纬度
                        aMapLocation.getLongitude();//获取经度
                        aMapLocation.getAccuracy();//获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        df.format(date);//定位时间
                        if (isFirstLoc) {
                            //设置缩放级别
//                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
//                    //将地图移动到定位点
//                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
//                    //点击定位按钮 能够将地图的中心移动到定位点
//                    mListener.onLocationChanged(aMapLocation);
                            //添加图钉
//                    aMap.addMarker(getMarkerOptions(amapLocation));
                            //获取定位信息
                            StringBuffer buffer = new StringBuffer();
                            Log.e("aaaaaaaaaaaaaa", aMapLocation.getAddress());
                            buffer.append(aMapLocation.getCountry() + "" + aMapLocation.getProvince() + "" + aMapLocation.getCity() + "" + aMapLocation.getProvince() + "" + aMapLocation.getDistrict() + "" + aMapLocation.getStreet() + "" + aMapLocation.getStreetNum());
                            //    Toast.makeText(getContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                            isFirstLoc = false;
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences("user",
                                    Activity.MODE_PRIVATE);
                            //实例化SharedPreferences.Editor对象
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            //用putString的方法保存数据
                            editor.putString("city", aMapLocation.getCity());
                            //   提交当前数据
                            editor.apply();
                            city = aMapLocation.getCity();
                            pushcity = aMapLocation.getCity();
                            txt_city.setText(city);
                            loanpush(userid);
                            pageInfoBeen.clear();
                            load(city, flag, page, productType, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);
//                            txt_city.setText(aMapLocation.getCity());
                        }


                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
//                        if (aMapLocation.getErrorCode() == 13) {
//                            ToastUtils.showToastGravityCenter("请确认以授予权限");
//                        }
//                Toast.makeText(getContext(), "location Error, ErrCode:"
//                        + amapLocation.getErrorCode() + ", errInfo:"
//                        + amapLocation.getErrorInfo(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    private void initview() {
        listview = (PullToRefreshListView) view.findViewById(R.id.listview);
        listview.setPullRefreshEnabled(true);
        listview.setPullLoadEnabled(false);
        listview.setScrollLoadEnabled(true);
        listview.setLastUpdatedLabel(new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis())));   // 设置下拉时间
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageInfoBeen.clear();
                page = 1;
                load(city, flag, page, productType, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);
                firstFragmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                int pages = 1;
                page += pages;
                load(city, flag, page, productType, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);
                firstFragmentAdapter.notifyDataSetChanged();
            }
        });
        listViews = listview.getRefreshableView();
        listViews.setDivider(null); // ListView 去 item分割线
        listViews.setSelector(getResources().getDrawable(R.color.tran)); // 点击背景透明
        View headView = View.inflate(getActivity(), R.layout.hrader_firstfragment_listview, null);
        rollPagerView = (RollPagerView) headView.findViewById(R.id.banners);
        listViews.addHeaderView(headView);
        //设置播放时间间隔
        rollPagerView.setPlayDelay(3000);
        //设置透明度
        rollPagerView.setAnimationDurtion(500);
        testNormalAdapter = new RollViewPagerAdapter(rollPagerView, this.getContext(), banners);
        // rollViewPagerAdapter = new RollViewPagerAdapter(rollPagerView, this.getContext(), url);
        // 设置适配器（请求网络图片，适配器要在网络请求完成后再设置）
        rollPagerView.setAdapter(testNormalAdapter);
        //rollPagerView.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        rollPagerView.setHintView(new ColorPointHintView(this.getContext(), Color.BLUE, Color.WHITE));
        //viewPag更新数据
        rollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e("Aaaaa", banners.get(position).getWeburl());
                if (banners.get(position).getWeburl() != null && banners.get(position).getWeburl().length() > 0) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("web", banners.get(position).getWeburl());
                    startActivity(intent);
                }
            }
        });
        firstFragmentAdapter = new FirstFragmentAdapter(getContext(), pageInfoBeen);
        listViews.setAdapter(firstFragmentAdapter);
        listViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick: " + pageInfoBeen.get(position - 1).getIsSuccess() + "\n" + pageInfoBeen.get(position - 1).getQdFlag());
                if (pageInfoBeen.get(position - 1).getIsSuccess().equals("0")) {
                    if (pageInfoBeen.get(position - 1).getQdFlag().equals("0")) {
                        Intent intent = new Intent(getContext(), ListDetailsActivity.class);
                        intent.putExtra("orderid", pageInfoBeen.get(position - 1).getId());
                        intent.putExtra("qdflag", pageInfoBeen.get(position - 1).getQdFlag());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getContext(), ListDetailsActivity.class);
                        intent.putExtra("orderid", pageInfoBeen.get(position - 1).getId());
                        intent.putExtra("qdflag", pageInfoBeen.get(position - 1).getQdFlag());
                        startActivity(intent);
                    }
                } else if (pageInfoBeen.get(position - 1).getIsSuccess().equals("1")) {
                    if (pageInfoBeen.get(position - 1).getQdFlag().equals("1")) {
                        Intent intent = new Intent(getContext(), ListDetailsQdActivity.class);
                        intent.putExtra("orderid", pageInfoBeen.get(position - 1).getId());
                        startActivity(intent);
                    }
                }
            }
        });
    }

    //轮播
    private void data() {
        OkHttpUtils
                .get()
                .url(ConstantUrl.bannerurl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("lunbo", e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("lunbo", response.toString());
//
                        try {
                            JSONArray myJsonArray = new JSONArray(response);
                            for (int i = 0; i < myJsonArray.length(); i++) {
                                JSONObject myjObject = myJsonArray.getJSONObject(i);
                                String a = myjObject.getString("url");
                                Log.e("Aaaaa", a);
                                BannerData bannerData = new BannerData();
                                bannerData.setImgurl(myjObject.getString("url"));
                                bannerData.setWeburl(myjObject.getString("link"));
                                banners.add(bannerData);
                            }
                            testNormalAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {

                        }
                    }

                });
    }

    //推送
    private void loanpush(final Integer userid) {
        PushReg firstOrderDetails = new PushReg();
        firstOrderDetails.setUserId(userid);
        Log.e(TAG, "loanorder: " + new Gson().toJson(firstOrderDetails));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.pushurl)
                .content(new Gson().toJson(firstOrderDetails))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onResponse: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse:push " + response);
                        PushRet pushRet = new Gson().fromJson(response, PushRet.class);
                        if (pushRet.getPush() != null) {

                        } else {
                            loanpushupdate(pushcity, userid, "0", -1);
                        }

                    }

                });
    }

    private void loanpushupdate(final String city, Integer userid, String flag, Integer pushid) {
        PushUpdata pushUpdata = new PushUpdata();
        if (!city.equals("push")) {
            pushUpdata.setCity(city);
        }

        pushUpdata.setUserId(userid);
        pushUpdata.setFlag(flag);
        if (pushid == -1) {
        } else {
            pushUpdata.setId(pushid);
        }
        Log.e(TAG, "loanorder: " + new Gson().toJson(pushUpdata));
        OkHttpUtils
                .postString()
                .url(ConstantUrl.updatapushurl)
                .content(new Gson().toJson(pushUpdata))
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
                        OrderBean orderBean = new Gson().fromJson(response, OrderBean.class);
                        if (orderBean.getCode().equals("0")) {
                            ToastUtils.showToastGravityCenter(orderBean.getMsg());
//                            PushSettingActivity.this.finish();
                            //极光设置标签
                            Set<String> set = new HashSet<>();
                            set.add(city);//名字任意，可多添加几个
                            JPushInterface.setTags(getContext(), set, new TagAliasCallback() {
                                @Override
                                public void gotResult(int i, String s, Set<String> set) {
                                    Log.e("tagfitst", i + "");
                                }
                            });//设置标签
                        } else {
                        }
                    }

                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String citys = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                txt_city.setText(citys);
                if (citys.equals("全国")) {
                    city = citys;
                } else {
                    city = citys + "市";
                }
//                load(city, flag, page, productType, userId, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);
//                firstFragmentAdapter.notifyDataSetChanged();
            }
        }
    }

    @Subscribe
    public void onEvent(EventData event) {
        super.onEvent(event);
        OrderVo or = event.getOrderVo();
        Log.e(TAG, "onEvent: " + or.getMinAmount() + "\n" + or.getMaxAmount() + "\n" +
                +or.getMinTime() + "\n" + or.getMaxTime() + "\n"
                + or.getWorkType() + "\n" + or.getSecurity() + "\n"
                + or.getPledgeType() + "\n" + or.getFund());

        minAmount = or.getMinAmount();


        maxAmount = or.getMaxAmount();

        minTime = or.getMinTime();

        maxTime = or.getMaxTime();

        workType = or.getWorkType();

        security = or.getSecurity();

        pledgeType = or.getPledgeType();

        fund = or.getFund();
        page = 1;
        pageInfoBeen.clear();
        load(city, flag, page, productType, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + "执行");
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        pageInfoBeen.clear();

        load(city, flag, page, productType, userid, minAmount, maxAmount, minTime, maxTime, workType, fund, security, pledgeType);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterEventBus();
    }
}