package com.sxw.loan.loanorder.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.BtData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by Sxw on 2017-07-22.
 */

public class BtTxAdapter extends BaseAdapter {
    private List<BtData.PageInfoBean.ListBean> mListBeen;
    private Context mContext;

    public BtTxAdapter(Context mcontext, List<BtData.PageInfoBean.ListBean> listBeen) {
        mContext = mcontext;
        mListBeen = listBeen;
    }

    @Override
    public int getCount() {
        return mListBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mListBeen.get(position);
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
            convertView = View.inflate(mContext, R.layout.item_bttxactivity_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.btname);
            viewHolder.pt = (TextView) convertView.findViewById(R.id.pt);
            viewHolder.city = (TextView) convertView.findViewById(R.id.btcity);
            viewHolder.btmoney = (TextView) convertView.findViewById(R.id.btmoney);
            viewHolder.btmonth = (TextView) convertView.findViewById(R.id.btmonth);
            viewHolder.ordersta = (TextView) convertView.findViewById(R.id.ordersta);
            convertView.setTag(viewHolder);
            AutoUtils.auto(convertView);
        }
        viewHolder.name.setText("姓名:" + mListBeen.get(position).getName());
        if (mListBeen.get(position).getCity() != null && mListBeen.get(position).getCity().length() > 0) {
            viewHolder.city.setText("城市:" + mListBeen.get(position).getCity());

        } else {
            viewHolder.city.setText("城市:暂未提供");

        }
        viewHolder.pt.setText("平台:" + mListBeen.get(position).getBtName());
        viewHolder.btmoney.setText(mListBeen.get(position).getAmount() + "元");
        viewHolder.btmonth.setText(mListBeen.get(position).getCreditPeriod() + "月");
        if (mListBeen.get(position).getReadFlag().equals("0")) {
            viewHolder.ordersta.setVisibility(View.GONE);
        } else {
            viewHolder.ordersta.setText("已读");
            viewHolder.ordersta.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView name;
        private TextView pt;
        private TextView city;
        private TextView btmoney;
        private TextView btmonth;
        private TextView ordersta;
    }

}
