package com.sxw.loan.loanorder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.MineOrderRet;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */

public class MineOrderAdapter extends BaseAdapter {
    private Context mContext;
    private List<MineOrderRet.PageInfoBean.ListBean> mlistBeen;

    public MineOrderAdapter(Context context, List<MineOrderRet.PageInfoBean.ListBean> listBeen) {
        mContext = context;
        mlistBeen = listBeen;
    }

    @Override
    public int getCount() {
        return mlistBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder;
        if (convertView != null) {
            viewHoder = (ViewHoder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mineorder, parent, false);
            viewHoder = new ViewHoder();
            viewHoder.name = (TextView) convertView.findViewById(R.id.name);
            viewHoder.city = (TextView) convertView.findViewById(R.id.city);
            viewHoder.time = (TextView) convertView.findViewById(R.id.time);
            viewHoder.work = (TextView) convertView.findViewById(R.id.work);
            viewHoder.money = (TextView) convertView.findViewById(R.id.money);
            viewHoder.month = (TextView) convertView.findViewById(R.id.month);
            viewHoder.imageSta = (ImageView) convertView.findViewById(R.id.imageSta);
            convertView.setTag(viewHoder);
            AutoUtils.auto(convertView);
        }
        //名字
        viewHoder.name.setText(mlistBeen.get(position).getName());
        //城市
        if (mlistBeen.get(position).getCity() != null) {
            viewHoder.city.setText("城市:" + mlistBeen.get(position).getCity());
        } else {
            viewHoder.city.setText("城市:" + "暂未提供");
        }
        //期限
        if (mlistBeen.get(position).getCreditPeriod() >= 0) {
            viewHoder.month.setText("贷款期限:" + mlistBeen.get(position).getCreditPeriod() + "月");
        } else {
            viewHoder.month.setText("贷款期限:" + "详情请联系客户");
        }
        //工作
        if (mlistBeen.get(position).getWorkType() != null && mlistBeen.get(position).getWorkType().equals("0")) {
            viewHoder.work.setText("职业:" + "企业主");
        } else if (mlistBeen.get(position).getWorkType() != null && mlistBeen.get(position).getWorkType().equals("1")) {
            viewHoder.work.setText("职业:" + "上班族");
        } else if (mlistBeen.get(position).getWorkType() != null && mlistBeen.get(position).getWorkType().equals("2")) {
            viewHoder.work.setText("职业:" + "个体户");
        } else if (mlistBeen.get(position).getWorkType() != null && mlistBeen.get(position).getWorkType().equals("3")) {
            viewHoder.work.setText("职业:" + "学生");
        } else if (mlistBeen.get(position).getWorkType() != null && mlistBeen.get(position).getWorkType().equals("4")) {
            viewHoder.work.setText("职业:" + "公务员/事业编制");
        } else if (mlistBeen.get(position).getWorkType() != null && mlistBeen.get(position).getWorkType().equals("5")) {
            viewHoder.work.setText("职业:" + "自由职业");
        } else {
            viewHoder.work.setText("职业:" + "其他");
        }
        //金额
        if (mlistBeen.get(position).getAmount() >= 0) {
            viewHoder.money.setText("贷款金额:" + mlistBeen.get(position).getAmount() + "元");
        } else {
            viewHoder.money.setText("贷款金额:" + "详情请联系客户");

        }
        if (mlistBeen.get(position).getStatus() == 0) {
            viewHoder.imageSta.setBackgroundResource(R.mipmap.icon_qdcg);
        } else if (mlistBeen.get(position).getStatus() == 1) {
            viewHoder.imageSta.setBackgroundResource(R.mipmap.icon_fq);

        } else if (mlistBeen.get(position).getStatus() == 2) {
            viewHoder.imageSta.setBackgroundResource(R.mipmap.icon_fksb);

        } else if (mlistBeen.get(position).getStatus() == 3) {
            viewHoder.imageSta.setBackgroundResource(R.mipmap.icon_fdcg);

        } else if (mlistBeen.get(position).getStatus() == 4) {
            viewHoder.imageSta.setBackgroundResource(R.mipmap.icon_hold);

        }
        if (mlistBeen.get(position).getLastDealTime() != null) {
            viewHoder.time.setText("最后处理时间:"+stampToDate(mlistBeen.get(position).getLastDealTime()));
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

    public class ViewHoder {
        private TextView name;
        private TextView city;
        private TextView time;
        private TextView work;
        private TextView money;
        private TextView month;
        private ImageView imageSta;
    }
}
