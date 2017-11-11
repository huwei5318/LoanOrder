package com.sxw.loan.loanorder.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.moudle.BannerData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-03-13.
 */

public class RollViewPagerAdapter extends LoopPagerAdapter {
    private List<BannerData> mUrl = new ArrayList<>();
    private Context mContext;

    public RollViewPagerAdapter(RollPagerView rollPagerView, Context context, List<BannerData> url) {
        super(rollPagerView);
        mContext = context;
        mUrl = url;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_rollviewpager, null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.rollPagerView_sim = (SimpleDraweeView) view.findViewById(R.id.rollPagerView_sim);
        viewHolder.rollPagerView_sim.setImageURI(mUrl.get(position).getImgurl());
        AutoUtils.auto(container);
        return view;
    }

    private class ViewHolder {
        SimpleDraweeView rollPagerView_sim;
    }

    @Override
    public int getRealCount() {
        return mUrl.size();
    }
}
