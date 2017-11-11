package com.sxw.loan.loanorder.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sxw.loan.loanorder.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by Administrator on 2017-05-10.
 */

public class SlidingAdapterGride extends BaseAdapter {
    private List<String> chargeMoneys;
    private Context context;
    //点击变色
    private int clickTemp = 0;

    public SlidingAdapterGride(Context mContext, List<String> mlist) {
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
            convertView = View.inflate(context, R.layout.item_tomoney_ryc, null);
            viewHolder = new ViewHolder();
            viewHolder.txt = (TextView) convertView.findViewById(R.id.item_tomoney_checked);
            convertView.setTag(viewHolder);
            AutoUtils.auto(convertView);
        }
        if (clickTemp == position) {
            viewHolder.txt.setBackgroundResource(R.drawable.text);
            viewHolder.txt.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            viewHolder.txt.setBackgroundResource(R.drawable.textview);
            viewHolder.txt.setTextColor(context.getResources().getColor(R.color.black));
        }
        viewHolder.txt.setText(chargeMoneys.get(position));
        AutoUtils.auto(convertView);
        return convertView;
    }

    public class ViewHolder {
        TextView txt;
    }
}
