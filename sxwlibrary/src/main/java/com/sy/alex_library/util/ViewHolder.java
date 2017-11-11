package com.sy.alex_library.util;

import android.util.SparseArray;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * 可用在BaseAdapter中
 * 不用重复创建ViewHolder类
 */

public class ViewHolder {
    /**
     * @param view
     * @param id
     * @param <T>
     * @return
     */

    /**
     * 示例:
     *
     * @Override public View getView(int position, View convertView, ViewGroup parent) {
     * if (convertView == null) {
     * convertView = inflater.inflate(R.layout.listview_item_layout, parent, false);
     * }
     * TextView name = util.ViewHolder.get(convertView, R.id.student_name);
     * TextView age = util.ViewHolder.get(convertView, R.id.student_age);
     * <p>
     * Student data = (Student) getItem(position);
     * name.setText(data.getName());
     * age.setText(data.getAge());
     * <p>
     * return convertView;
     * }
     */

    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
            AutoUtils.autoSize(view);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
