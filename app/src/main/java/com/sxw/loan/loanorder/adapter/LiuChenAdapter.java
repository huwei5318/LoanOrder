package com.sxw.loan.loanorder.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.BtTxQdDetailsBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by Sxw on 2017-07-25.
 */

public class LiuChenAdapter extends BaseAdapter {
    private List<String> processBeen;
    private Context mContext;

    public LiuChenAdapter(Context context, List<String> processBeens) {
        processBeen = processBeens;
        mContext = context;
    }

    @Override
    public int getCount() {
        return processBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return processBeen.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_liucheng, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt = (TextView) convertView.findViewById(R.id.txt);
            convertView.setTag(viewHolder);
            AutoUtils.auto(convertView);
        }
        Log.e("position", processBeen.get(position));
        viewHolder.txt.setText(processBeen.get(position));
        return convertView;
    }

    public class ViewHolder {
        TextView txt;
    }
}
