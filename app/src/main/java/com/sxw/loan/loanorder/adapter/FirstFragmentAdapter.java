package com.sxw.loan.loanorder.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.FirstRespon;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Sxw on 2017-07-12.
 */

public class FirstFragmentAdapter extends BaseAdapter {
    private Context mcontext;
    private List<FirstRespon.PageInfoBean.ListBean> pageInfoBeen;

    public FirstFragmentAdapter(Context context, List<FirstRespon.PageInfoBean.ListBean> list) {
        mcontext = context;
        pageInfoBeen = list;
    }

    @Override
    public int getCount() {
        return pageInfoBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return pageInfoBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(mcontext, R.layout.item_firstfragment_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.loanmoney = (TextView) convertView.findViewById(R.id.loanmoney);
            viewHolder.month = (TextView) convertView.findViewById(R.id.month);
            viewHolder.city = (TextView) convertView.findViewById(R.id.city);
            viewHolder.zhiye = (TextView) convertView.findViewById(R.id.zhiye);
            viewHolder.money = (TextView) convertView.findViewById(R.id.money);
            viewHolder.car = (TextView) convertView.findViewById(R.id.car);
            viewHolder.house = (TextView) convertView.findViewById(R.id.house);
            viewHolder.order = (TextView) convertView.findViewById(R.id.order);
            viewHolder.ordername = (TextView) convertView.findViewById(R.id.ordername);
            viewHolder.ordetime = (TextView) convertView.findViewById(R.id.ordertime);

            convertView.setTag(viewHolder);
            AutoUtils.auto(convertView);
        }
        viewHolder.name.setText(pageInfoBeen.get(position).getName());
        viewHolder.loanmoney.setText(pageInfoBeen.get(position).getAmount() + "元");
        viewHolder.month.setText(pageInfoBeen.get(position).getCredit_period() + "月");
        if (pageInfoBeen.get(position).getCity() != null && pageInfoBeen.get(position).getCity().length() > 0) {
            viewHolder.city.setText(pageInfoBeen.get(position).getCity());
        } else {
            viewHolder.city.setText("暂未提供");
        }

        //工作
        if (pageInfoBeen.get(position).getWorkType() != null && pageInfoBeen.get(position).getWorkType().equals("0")) {
            viewHolder.zhiye.setText("企业主");
        } else if (pageInfoBeen.get(position).getWorkType() != null && pageInfoBeen.get(position).getWorkType().equals("1")) {
            viewHolder.zhiye.setText("上班族");
        } else if (pageInfoBeen.get(position).getWorkType() != null && pageInfoBeen.get(position).getWorkType().equals("2")) {
            viewHolder.zhiye.setText("个体户");
        } else if (pageInfoBeen.get(position).getWorkType() != null && pageInfoBeen.get(position).getWorkType().equals("3")) {
            viewHolder.zhiye.setText("学生");
        } else if (pageInfoBeen.get(position).getWorkType() != null && pageInfoBeen.get(position).getWorkType().equals("4")) {
            viewHolder.zhiye.setText("公务员/事业编制");
        } else if (pageInfoBeen.get(position).getWorkType() != null && pageInfoBeen.get(position).getWorkType().equals("5")) {
            viewHolder.zhiye.setText("自由职业");
        } else {
            viewHolder.zhiye.setText("其他");
        }
        //信用
        if (pageInfoBeen.get(position).getCreditRecord() != null && pageInfoBeen.get(position).getCreditRecord().equals("0")) {
            viewHolder.money.setText("无记录");
        } else if (pageInfoBeen.get(position).getCreditRecord() != null && pageInfoBeen.get(position).getCreditRecord().equals("1")) {
            viewHolder.money.setText("良好");
        } else if (pageInfoBeen.get(position).getCreditRecord() != null && pageInfoBeen.get(position).getCreditRecord().equals("2")) {
            viewHolder.money.setText("少数逾期");
        } else if (pageInfoBeen.get(position).getCreditRecord() != null && pageInfoBeen.get(position).getCreditRecord().equals("3")) {
            viewHolder.money.setText("多数逾期");
        } else {
            viewHolder.money.setText("无");
        }
        //车
        if (pageInfoBeen.get(position).getHouseholdAssets() != null && pageInfoBeen.get(position).getHouseholdAssets().equals("0")) {
            viewHolder.car.setText("无");
            viewHolder.house.setText("无");
        } else if (pageInfoBeen.get(position).getHouseholdAssets() != null && pageInfoBeen.get(position).getHouseholdAssets().equals("1")) {
            viewHolder.car.setText("无");
            viewHolder.house.setText("有");
        } else if (pageInfoBeen.get(position).getHouseholdAssets() != null && pageInfoBeen.get(position).getHouseholdAssets().equals("2")) {
            viewHolder.car.setText("有");
            viewHolder.house.setText("无");
        } else if (pageInfoBeen.get(position).getHouseholdAssets() != null && pageInfoBeen.get(position).getHouseholdAssets().equals("3")) {
            viewHolder.car.setText("其他");
            viewHolder.house.setText("其他");
        } else {
            viewHolder.car.setText("无");
            viewHolder.house.setText("无");
        }
        //公积金
        if (pageInfoBeen.get(position).getSecurity() != null && pageInfoBeen.get(position).getSecurity().equals("0")) {
            viewHolder.order.setText("有");
        } else if (pageInfoBeen.get(position).getSecurity() != null && pageInfoBeen.get(position).getSecurity().equals("1")) {
            viewHolder.order.setText("无");
        } else {
            viewHolder.order.setText("无");
        }
        //  抢单
        if (pageInfoBeen.get(position).getProductType() != null && pageInfoBeen.get(position).getProductType().equals("0")) {
            viewHolder.ordername.setText("优质单");
        } else if (pageInfoBeen.get(position).getProductType() != null && pageInfoBeen.get(position).getProductType().equals("1")) {
            viewHolder.ordername.setText("普通单");
        } else if (pageInfoBeen.get(position).getProductType() != null && pageInfoBeen.get(position).getProductType().equals("2")) {
            viewHolder.ordername.setText("可抢单");
        } else if (pageInfoBeen.get(position).getProductType() != null && pageInfoBeen.get(position).getProductType().equals("3")) {
            viewHolder.ordername.setText("已抢单");
        } else if (pageInfoBeen.get(position).getProductType() != null && pageInfoBeen.get(position).getProductType().equals("4")) {
            viewHolder.ordername.setText("未读");
            viewHolder.ordername.setVisibility(View.GONE);
        } else if (pageInfoBeen.get(position).getProductType() != null && pageInfoBeen.get(position).getProductType().equals("5")) {
            viewHolder.ordername.setText("已读");
        }
        if (pageInfoBeen.get(position).getTime() > 0) {
            viewHolder.ordetime.setText(stampToDate(String.valueOf(pageInfoBeen.get(position).getTime())));
        }
        return convertView;
    }

    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    private class ViewHolder {
        private TextView name;
        private TextView loanmoney;
        private TextView month;
        private TextView city;
        private TextView zhiye;
        private TextView money;
        private TextView car;
        private TextView house;
        private TextView order;
        private TextView ordername;
        private TextView ordetime;
    }
}
