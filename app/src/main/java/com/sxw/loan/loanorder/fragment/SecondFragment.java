package com.sxw.loan.loanorder.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.activity.TaoOrderActivity;
import com.sxw.loan.loanorder.activity.TxAcrivity;
import com.sxw.loan.loanorder.activity.WebVIewActivity;
import com.sxw.loan.loanorder.adapter.RollViewPagerAdapter;
import com.sxw.loan.loanorder.moudle.BannerData;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.MyFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017-04-11.
 */

public class SecondFragment extends MyFragment {
    RollPagerView rollPagerView;
    @BindView(R.id.rela_tixian)
    RelativeLayout relaTixian;
    @BindView(R.id.rela_taoorder)
    RelativeLayout relaTaoorder;
    private View view;
    private RollViewPagerAdapter testNormalAdapter;
    private List<BannerData> banners = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, null);
        StatusBarUtil.setTransparentForImageViewInFragment(getActivity(), null);
        ButterKnife.bind(this, view);
        data();
        rollPagerView = (RollPagerView) view.findViewById(R.id.fragment_fx);
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
                if (banners.get(position).getWeburl() != null) {
                    Intent intent = new Intent(getContext(), WebVIewActivity.class);
                    intent.putExtra("web", banners.get(position).getWeburl());
                    startActivity(intent);
                }
            }
        });
        return view;
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
    @OnClick({R.id.rela_tixian, R.id.rela_taoorder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rela_tixian:
                Intent intent = new Intent(getContext(), TxAcrivity.class);
                startActivity(intent);
                break;
            case R.id.rela_taoorder:
                Intent intent1 = new Intent(getContext(), TaoOrderActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
