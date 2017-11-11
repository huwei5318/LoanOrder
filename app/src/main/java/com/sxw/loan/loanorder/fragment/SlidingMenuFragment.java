package com.sxw.loan.loanorder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.sxw.loan.loanorder.MainActivity;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.adapter.SlidingAdapterGride;
import com.sxw.loan.loanorder.eventBus.EventData;
import com.sxw.loan.loanorder.eventBus.MainBus;
import com.sxw.loan.loanorder.moudle.OrderVo;
import com.sy.alex_library.base.MyFragment;
import com.sy.alex_library.ui.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sxw on 2017-07-11.
 */

public class SlidingMenuFragment extends MyFragment {
    @BindView(R.id.ed_moneyone)
    EditText edMoneyone;
    @BindView(R.id.ed_moneytwo)
    EditText edMoneytwo;
    //    @BindView(R.id.ed_priceone)
//    EditText edPriceone;
//    @BindView(R.id.ed_pricetwo)
//    EditText edPricetwo;
    @BindView(R.id.ed_monthone)
    EditText edMonthone;
    @BindView(R.id.ed_monthtwo)
    EditText edMonthtwo;
    //    @BindView(R.id.ed_ageone)
//    EditText edAgeone;
//    @BindView(R.id.ed_agetwo)
//    EditText edAgetwo;
    @BindView(R.id.grid_zy)
    ScrollGridView gridZy;
    @BindView(R.id.grid_gz)
    ScrollGridView gridGz;
    @BindView(R.id.grid_dy)
    ScrollGridView gridDy;
    @BindView(R.id.grid_sb)
    ScrollGridView gridSb;
    @BindView(R.id.btn_return)
    Button btnReturn;
    @BindView(R.id.btn_sure)
    Button btnSure;
    private String zy[] = {"不限", "企业主", "上班族",
            "个体户", "公务员",
            "自由职业"};
    private String gz[] = {"不限", "有", "无"};
    private String dy[] = {"不限", "可抵押"};
    private String sb[] = {"不限", "有社保"};
    private SlidingAdapterGride slidingAdapterGridezy, slidingAdapterGridedy,
            slidingAdapterGridegz, slidingAdapterGridesb;
    private Double minAmount = 0.0, maxAmount = 0.0;//金额
    private Integer minTime = 0, maxTime = 0;//期限

    private String qdFlag;
    private String productType;
    private String city;
    private String workType = "108";
    private String fund = "108";
    private String security = "108";
    private String pledgeType = "108";
    private DrawerLayout mDrawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slidingmenu, container, false);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.id_drawerLayout);
        ButterKnife.bind(this, view);
        initview();
        return view;
    }

    private void initview() {
        slidingAdapterGridezy = new SlidingAdapterGride(getContext(), mlistzy());
        gridZy.setAdapter(slidingAdapterGridezy);
        gridZy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                slidingAdapterGridezy.setSeclection(position);
                slidingAdapterGridezy.notifyDataSetChanged();
                if (position == 0) {
                    workType = "108";
                } else {
                    workType = String.valueOf(position - 1);
                }
            }
        });
        slidingAdapterGridegz = new SlidingAdapterGride(getContext(), mlistgz());
        gridGz.setAdapter(slidingAdapterGridegz);
        gridGz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                slidingAdapterGridegz.setSeclection(position);
                slidingAdapterGridegz.notifyDataSetChanged();
                if (position == 0) {
                    fund = "108";
                } else {
                    fund = String.valueOf(position - 1);
                }
            }
        });
        slidingAdapterGridedy = new SlidingAdapterGride(getContext(), mlistdy());
        gridDy.setAdapter(slidingAdapterGridedy);
        gridDy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                slidingAdapterGridedy.setSeclection(position);
                slidingAdapterGridedy.notifyDataSetChanged();
                if (position == 0) {
                    pledgeType = "108";
                } else {
                    pledgeType = String.valueOf(position - 1);
                }
            }
        });
        slidingAdapterGridesb = new SlidingAdapterGride(getContext(), mlistsb());
        gridSb.setAdapter(slidingAdapterGridesb);
        gridSb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                slidingAdapterGridesb.setSeclection(position);
                slidingAdapterGridesb.notifyDataSetChanged();
                if (position == 0) {
                    security = "108";
                } else {
                    security = String.valueOf(position - 1);
                }
            }
        });
    }

    private List<String> mlistsb() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < sb.length; i++) {
            list.add(sb[i]);
        }
        return list;

    }

    private List<String> mlistdy() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < dy.length; i++) {
            list.add(dy[i]);
        }
        return list;

    }

    private List<String> mlistgz() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < gz.length; i++) {
            list.add(gz[i]);
        }
        return list;
    }

    private List<String> mlistzy() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < zy.length; i++) {
            list.add(zy[i]);
        }
        return list;
    }

    @OnClick({R.id.btn_return, R.id.btn_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_return:
                edMoneyone.setText("");
                edMoneytwo.setText("");
                edMonthone.setText("");
                edMonthtwo.setText("");
                slidingAdapterGridezy.setSeclection(0);
                slidingAdapterGridezy.notifyDataSetChanged();
                slidingAdapterGridegz.setSeclection(0);
                slidingAdapterGridegz.notifyDataSetChanged();
                slidingAdapterGridedy.setSeclection(0);
                slidingAdapterGridedy.notifyDataSetChanged();
                slidingAdapterGridesb.setSeclection(0);
                slidingAdapterGridesb.notifyDataSetChanged();
                workType = "108";
                fund = "108";
                security = "108";
                pledgeType = "108";
                minAmount = 0.0;
                maxAmount = 0.0;
                minTime = 0;
                maxTime = 0;
                OrderVo orderVo = new OrderVo();
                orderVo.setMinAmount(0.0);
                orderVo.setMaxAmount(0.0);
                orderVo.setMinTime(0);
                orderVo.setMaxTime(0);
                orderVo.setWorkType(workType);
                orderVo.setFund(fund);
                orderVo.setSecurity(security);
                orderVo.setPledgeType(pledgeType);
                postEvent(new EventData(orderVo));
                postEvent(new MainBus("0"));
                break;
            case R.id.btn_sure:
                if (edMoneyone.getText().toString().length() > 0) {
                    minAmount = Double.valueOf(edMoneyone.getText().toString());
                }
                if (edMoneytwo.getText().toString().length() > 0) {
                    maxAmount = Double.valueOf(edMoneytwo.getText().toString());

                }
                if (edMonthone.getText().toString().length() > 0) {
                    minTime = Integer.valueOf(edMonthone.getText().toString());
                }
                if (edMonthtwo.getText().toString().length() > 0) {
                    maxTime = Integer.valueOf(edMonthtwo.getText().toString());

                }
                OrderVo orderVo1 = new OrderVo();
                orderVo1.setMinAmount(minAmount);
                orderVo1.setMaxAmount(maxAmount);
                orderVo1.setMinTime(minTime);
                orderVo1.setMaxTime(maxTime);
                orderVo1.setWorkType(workType);
                orderVo1.setFund(fund);
                orderVo1.setSecurity(security);
                orderVo1.setPledgeType(pledgeType);
                postEvent(new EventData(orderVo1));
                postEvent(new MainBus("0"));
                break;
        }
    }
}
