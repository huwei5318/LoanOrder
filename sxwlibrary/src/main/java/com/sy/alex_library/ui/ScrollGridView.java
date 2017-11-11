package com.sy.alex_library.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by alexFugui on 2016/9/26.
 * 重写onMeasure 让GridView在listView或ScrollView中重新计算高度
 */
public class ScrollGridView extends GridView {
    public ScrollGridView(Context context) {
        super(context);
    }

    public ScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
