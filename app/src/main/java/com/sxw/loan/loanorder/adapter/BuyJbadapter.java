package com.sxw.loan.loanorder.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.JBData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by Sxw on 2017-07-14.
 */

public class BuyJbadapter extends BaseAdapter {
    private List<JBData> chargeMoneys;
    private Context context;
    //点击变色
    private int clickTemp = 0;

    public BuyJbadapter(Context mContext, List<JBData> mlist) {
        chargeMoneys = mlist;
        context = mContext;
    }

    public void setSeclection(int position) {
        clickTemp = position;
    }


    @Override
    public int getCount() {
        return chargeMoneys.size();
    }

    @Override
    public Object getItem(int position) {
        return chargeMoneys.get(position);
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
            convertView = View.inflate(context, R.layout.item_buy_jb, null);
            viewHolder = new ViewHolder();
            viewHolder.money = (TextView) convertView.findViewById(R.id.buy_money);
            viewHolder.discount = (TextView) convertView.findViewById(R.id.buy_discount);
            viewHolder.linebuy = (LinearLayout) convertView.findViewById(R.id.line_buy);

            convertView.setTag(viewHolder);
            AutoUtils.auto(convertView);
        }
        if (clickTemp == position) {
            viewHolder.linebuy.setBackgroundResource(R.drawable.text);
            viewHolder.discount.setTextColor(context.getResources().getColor(R.color.white));
            viewHolder.money.setTextColor(context.getResources().getColor(R.color.white));

        } else {
            viewHolder.linebuy.setBackgroundResource(R.drawable.bg_line);
            viewHolder.discount.setTextColor(context.getResources().getColor(R.color.orgred));
            viewHolder.money.setTextColor(context.getResources().getColor(R.color.orgred));
        }
        viewHolder.money.setText(chargeMoneys.get(position).getOriginalPrice() + "元");
        viewHolder.discount.setText("售价:" + chargeMoneys.get(position).getPresentPrice() + "元");

        return convertView;
    }

    public class ViewHolder {
        TextView money, discount;
        LinearLayout linebuy;
    }
}
