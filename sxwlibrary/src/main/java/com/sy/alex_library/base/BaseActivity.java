package com.sy.alex_library.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.sy.alex_library.R;
import com.sy.alex_library.databinding.ActivityBaseBinding;
import com.sy.alex_library.tools.PerfectClickListener;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by alexFugui on 2017/3/2.
 */

public abstract class BaseActivity<SV extends ViewDataBinding> extends BaseAutoLayoutActivity implements View.OnClickListener{
    // 布局view
    protected SV bindingView;
    private LinearLayout llProgressBar;
    private View refresh;
    private ActivityBaseBinding mBaseBinding;
    private AnimationDrawable mAnimationDrawable;
    //    private CompositeSubscription mCompositeSubscription;
    protected String SUCCESS_CODE = "0";

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    //全局加载框
    protected ProgressDialog progressDialog;
    protected BaseActivity context;

    //点击同一 view 最小的时间间隔，如果小于这个数则忽略此次单击。
    private static long intervalTime = 800;
    //最后点击时间
    private long lastClickTime = 0;
    //最后被单击的 View 的ID
    private long lastClickView = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        BaseAppManager.getInstance().addActivity(this);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base, null, false);
        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);

        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        RelativeLayout mContainer = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());

        // 设置透明状态栏
        StatusBarUtil.setColor(this, getResources().getColor(R.color.main_nav_blue), 0);
        llProgressBar = getView(R.id.ll_progress_bar);
        refresh = getView(R.id.ll_error_refresh);
        ImageView img = getView(R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        setToolBar();
        // 点击加载失败布局
        refresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        bindingView.getRoot().setVisibility(View.GONE);
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        mBaseBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void setTitleBar(boolean visible) {
        mBaseBinding.titleBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setBackAction(boolean visible) {
        mBaseBinding.ivBack.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    public void setActionText(String title) {
        mBaseBinding.actionText.setVisibility(View.VISIBLE);
        mBaseBinding.actionText.setText(title);
    }

    protected void setActionTextClick(View.OnClickListener listener) {
    }


    public void setTitle(CharSequence text) {
        mBaseBinding.title.setText(text);
    }

    protected void showLoading() {
        if (llProgressBar.getVisibility() != View.VISIBLE) {
            llProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    protected void showError() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.VISIBLE) {
            refresh.setVisibility(View.VISIBLE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }

//    public void addSubscription(Subscription s) {
//        if (this.mCompositeSubscription == null) {
//            this.mCompositeSubscription = new CompositeSubscription();
//        }
//        this.mCompositeSubscription.add(s);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
//            this.mCompositeSubscription.unsubscribe();
//        }
//    }
//
//    public void removeSubscription() {
//        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
//            this.mCompositeSubscription.unsubscribe();
//        }
//    }

//    protected Dialog showProgressDialog() {
//        return showProgressDialog("正在加载中");
//    }

//    protected Dialog showProgressDialog(int msgId) {
//        String msg = getString(msgId);
//        return showProgressDialog(msg);
//    }

//    protected Dialog showProgressDialog(String msg) {
//        if (progressDialog != null) {
//            progressDialog.dismiss();
//            progressDialog = null;
//        }
//        progressDialog = DialogFactory.createProgressDialog(context, msg);
//
//        if (!progressDialog.isShowing()) {
//            progressDialog.setMessage(msg);
//            progressDialog.show();
//        }
//        return progressDialog;
//    }

    public void hideProgressDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }

    }

    @Override
    public void onClick(View view) {
        if (!isFastMultiClick(view)) {
            onViewClick(view);
        }
    }

    /**
     * 防短时重复点击回调 <br>
     * 子类使用 View.OnClickListener 设置监听事件时直接覆写该方法完成点击回调事件
     *
     * @param view 被单击的View
     */
    protected void onViewClick(View view) {
        //供字类重写用事件
    }

    /**
     * 是否快速多次点击(连续多点击）
     *
     * @param view 被点击view，如果前后是同一个view，则进行双击校验
     * @return 认为是重复点击时返回true。
     */
    private boolean isFastMultiClick(View view) {
        long time = System.currentTimeMillis() - lastClickTime;

        if (time < intervalTime && lastClickView == view.getId()) {
            lastClickTime = System.currentTimeMillis();
            return true;
        }

        lastClickTime = System.currentTimeMillis();
        lastClickView = view.getId();

        return false;
    }

    //--------------------沉浸式状态栏------------------
    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }

    }

    /**
     * 生成一个和状态栏大小相同的矩形条 * * @param activity 需要设置的activity * @param color 状态栏颜色值 * @return 状态栏矩形条
     */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

    /**
     * 使状态栏透明 * <p> * 适用于图片作为背景的界面,此时需要图片填充到状态栏 * * @param activity 需要设置的activity
     */
    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    // -----------------生命周期的管理，监控-------------------------------------------------


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();


    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    protected void showToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    // -----------------Activity跳转及结束-------------------------------------------------------
    protected void startActivity(Class<?> targetClass) {//普通方式跳转activity
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
    }

    //需先将数据打包到bundle里  然后将bundle传入此方法
    protected void startActivity(Class<?> targetClass, Bundle bundle) {
        //跳转activity并 传递数据
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 切换Activity动画效果
     * overridePendingTransition(R.anim.fade, R.anim.hold);//淡入淡出
     * overridePendingTransition(R.anim.my_scale_action,R.anim.my_alpha_action);//放大淡出
     * overridePendingTransition(R.anim.scale_rotate,R.anim.my_alpha_action);//转动淡出1
     * overridePendingTransition(R.anim.scale_translate_rotate,R.anim.my_alpha_action);//转动淡出2
     * overridePendingTransition(R.anim.scale_translate,R.anim.my_alpha_action);//左上角展开淡出效果
     * overridePendingTransition(R.anim.hyperspace_in,R.anim.hyperspace_out);//压缩变小淡出效果
     * overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);//右往左推出效果
     * overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);//下往上推出效果
     * overridePendingTransition(R.anim.slide_left,R.anim.slide_right);//左右交叉效果
     * overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);//缩小效果
     * overridePendingTransition(R.anim.slide_up_in,R.anim.slide_down_out);//上下文交错
     */
    protected void startActivity(Class<?> targetClass, int inAnimID, int outAnimID) {
        Intent intent = new Intent(this, targetClass);
        this.startActivity(intent);
        this.overridePendingTransition(inAnimID, outAnimID);
    }

    protected void startActivity(Class<?> targetClass, int inAnimID, int outAnimID, Bundle bundle) {
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(inAnimID, outAnimID);
    }

    @Override
    public void finish() {
        BaseAppManager.getInstance().removeActivity(this);
        super.finish();
    }


    //----------------------------隐藏软键盘-----------------------------------
    //当有edittext时 点击空白区域即可关闭软键盘， 集成即可  不用调用方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    //取消注册
    protected void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    //发送事件
    protected void postEvent(Object event) {
        EventBus.getDefault().post(event);
    }

    //接收事件
    public void onEvent(Object event) {

    }
}