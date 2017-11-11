package com.sy.alex_library.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * Created by AlexFugui on 2017/3/22.
 */

public class AutoCoordinatorLayout extends CoordinatorLayout {
    private AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoCoordinatorLayout(Context context) {
        super(context);
    }

    public AutoCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode())
            mHelper.adjustChildren();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

//    @Override
//    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return new LayoutParams(getContext(), attrs);
//    }

//    public static class LayoutParams extends CoordinatorLayout.LayoutParams implements AutoLayoutHelper.AutoLayoutParams {
//        private AutoLayoutInfo mAutoLayoutInfo;
//
//        public LayoutParams(int width, int height) {
//            super(width, height);
//        }
//
//        public LayoutParams(CoordinatorLayout.LayoutParams p) {
//            super(p);
//        }
//
//        public LayoutParams(MarginLayoutParams p) {
//            super(p);
//        }
//
//        public LayoutParams(ViewGroup.LayoutParams p) {
//            super(p);
//        }
//
//        @Override
//        public AutoLayoutInfo getAutoLayoutInfo() {
//            return mAutoLayoutInfo;
//        }
////        public LayoutParams(Context c, AttributeSet attrs) {
////            super(c, attrs);
////            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
////        }
////
////        @Override
////        public AutoLayoutInfo getAutoLayoutInfo() {
////            return mAutoLayoutInfo;
////        }
////
////
////        public LayoutParams(int width, int height) {
////            super(width, height);
////        }
////
////        public LayoutParams(ViewGroup.LayoutParams source) {
////            super(source);
////        }
////
////        public LayoutParams(MarginLayoutParams source) {
////            super(source);
////        }
//    }

}
