package com.sy.alex_library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.sy.alex_library.R;
import com.sy.alex_library.module.CityModel;
import com.sy.alex_library.module.ProvinceModel;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class ProvincesAdapter extends BaseAdapter {
    private Context pContext;
    public static List<ProvinceModel> provinceList;
    public static List<CityModel> cityModelList = null;
    private String Province;
    public static int pPostion;
    private CityAdapter cityAdapter;


    public ProvincesAdapter(Context context, List<ProvinceModel> list) {
        pContext = context;
        provinceList = list;
    }

    @Override
    public int getCount() {
        return provinceList.size();
    }

    @Override
    public Object getItem(int i) {
        return provinceList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View view;
        provincesHolder holder;
        if (convertView != null) {
            view = convertView;
            holder = (provincesHolder) view.getTag();
        } else {
            view = View.inflate(pContext, R.layout.item_addrs, null);
            holder = new provincesHolder();
            holder.pro = (TextView) view.findViewById(R.id.pro);
            view.setTag(holder);
        }
        Province = provinceList.get(i).getName();
        holder.pro.setText(Province);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pPostion=i;
////                Toast.makeText(pContext, Province, Toast.LENGTH_SHORT).show();
//                cityModelList = provinceList.get(i).getCityList();
//                cityAdapter = new CityAdapter(pContext, cityModelList);
//                ShowAddrActivity.showAddr_list2.setAdapter(cityAdapter);
//                cityAdapter.notifyDataSetChanged();
//                //判断是否更新区县列表
//                if (CityAdapter.districtAdapter != null) {
//                    CityAdapter.districtModelList = cityModelList.get(0).getDistrictList();
//                    CityAdapter.districtAdapter = new DistrictAdapter(pContext, cityModelList.get(0).getDistrictList());
//                    ShowAddrActivity.showAddr_list3.setAdapter(QiuGou.districtAdapter);
//                    CityAdapter.districtAdapter.notifyDataSetChanged();
//                }
//            }
//        });
        return view;
    }

    private class provincesHolder {
        private TextView pro;
    }
}
