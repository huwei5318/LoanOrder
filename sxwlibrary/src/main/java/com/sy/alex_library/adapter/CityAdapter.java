package com.sy.alex_library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.sy.alex_library.R;
import com.sy.alex_library.module.CityModel;
import com.sy.alex_library.module.DistrictModel;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class CityAdapter extends BaseAdapter {
    private Context cContext;
    public static List<CityModel> cityModelList;
    public static List<DistrictModel> districtModelList;
    public static DistrictAdapter districtAdapter;
    private String city;
    public static int cPostion;

    public CityAdapter(Context context, List<CityModel> list) {
        cContext = context;
        cityModelList = list;
    }

    @Override
    public int getCount() {
        return cityModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return cityModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View view;
        cityHolder holder;
        if (convertView != null) {
            view = convertView;
            holder = (cityHolder) view.getTag();
        } else {
            view = View.inflate(cContext, R.layout.item_addrs, null);
            holder = new cityHolder();
            holder.city = (TextView) view.findViewById(R.id.pro);
            view.setTag(holder);
        }
        city = cityModelList.get(i).getName();
        holder.city.setText(city);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cPostion = i;
////                Toast.makeText(cContext, city, Toast.LENGTH_SHORT).show();
//                districtModelList = cityModelList.get(i).getDistrictList();
//                districtAdapter = new DistrictAdapter(cContext, districtModelList);
//                ShowAddrActivity.showAddr_list3.setAdapter(districtAdapter);
//                districtAdapter.notifyDataSetChanged();
//            }
//        });
        return view;
    }

    private class cityHolder {
        private TextView city;
    }
}
