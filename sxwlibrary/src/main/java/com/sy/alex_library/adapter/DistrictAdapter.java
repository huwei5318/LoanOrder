package com.sy.alex_library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.sy.alex_library.R;
import com.sy.alex_library.module.DistrictModel;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class DistrictAdapter extends BaseAdapter {
    private Context dContext;
    public static List<DistrictModel> districtModelList;
    public static int dPostion;

    public DistrictAdapter(Context context, List<DistrictModel> list) {
        dContext = context;
        districtModelList = list;
    }

    @Override
    public int getCount() {
        return districtModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return districtModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View view;
        districtHolder holder;
        if (convertView != null) {
            view = convertView;
            holder = (districtHolder) view.getTag();
        } else {
            view = View.inflate(dContext, R.layout.item_addrs, null);
            holder = new districtHolder();
            holder.district = (TextView) view.findViewById(R.id.pro);
            view.setTag(holder);
        }
        final String district = districtModelList.get(i).getName();
        holder.district.setText(district);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dPostion = i;
//                Toast.makeText(dContext, ProvincesAdapter.provinceList.get(ProvincesAdapter.pPostion).getName() + "," + CityAdapter.cityModelList.get(CityAdapter.cPostion).getName() + "," + district, Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    private class districtHolder {
        private TextView district;
    }
}
