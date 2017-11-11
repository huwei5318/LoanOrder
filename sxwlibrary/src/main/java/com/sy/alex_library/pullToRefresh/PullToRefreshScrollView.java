package com.sy.alex_library.pullToRefresh;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 封装了ScrollView的下拉刷新
 *
 * @author Li Hong
 * @since 2013-8-22
 */
public class PullToRefreshScrollView extends PullToRefreshBase<NestedScrollView> {
    /**
     * 构造方法
     *
     * @param context context
     */
    public PullToRefreshScrollView(Context context) {
        this(context, null);
    }

    /**
     * 构造方法
     *
     * @param context context
     * @param attrs attrs
     */
    public PullToRefreshScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造方法
     *
     * @param context context
     * @param attrs attrs
     * @param defStyle defStyle
     */
    public PullToRefreshScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @see PullToRefreshBase#createRefreshableView(Context, AttributeSet)
     */
    @Override
    protected NestedScrollView createRefreshableView(Context context, AttributeSet attrs) {
        NestedScrollView scrollView = new NestedScrollView(context);
        return scrollView;
    }

    /**
     * @see PullToRefreshBase#isReadyForPullDown()
     */
    @Override
    protected boolean isReadyForPullDown() {
        return mRefreshableView.getScrollY() == 0;
    }

    /**
     * @see PullToRefreshBase#isReadyForPullUp()
     */
    @Override
    protected boolean isReadyForPullUp() {
        View scrollViewChild = mRefreshableView.getChildAt(0);
        if (null != scrollViewChild) {
            return mRefreshableView.getScrollY() >= (scrollViewChild.getHeight() - getHeight());
        }

        return false;
    }
}
