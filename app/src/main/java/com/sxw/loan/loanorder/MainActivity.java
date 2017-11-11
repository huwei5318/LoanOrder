package com.sxw.loan.loanorder;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sxw.loan.loanorder.adapter.FragmentAdapter;
import com.sxw.loan.loanorder.eventBus.MainBus;
import com.sxw.loan.loanorder.fragment.FirstFragment;
import com.sxw.loan.loanorder.fragment.MineFragment;
import com.sxw.loan.loanorder.fragment.SecondFragment;
import com.sxw.loan.loanorder.moudle.UpdataBean;
import com.sxw.loan.loanorder.ui.UpdataDialog;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.BaseActivity;
import com.sy.alex_library.ui.BaseViewPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionGen;
import okhttp3.Call;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_viewpager)
    BaseViewPager mainViewpager;
    @BindView(R.id.home_icon_home)
    ImageView homeIconHome;
    @BindView(R.id.home_txt_home)
    TextView homeTxtHome;
    @BindView(R.id.home_firstPager)
    LinearLayout homeFirstPager;
    @BindView(R.id.home_icon_order)
    ImageView homeIconOrder;
    @BindView(R.id.home_txt_order)
    TextView homeTxtOrder;
    @BindView(R.id.home_add)
    LinearLayout homeAdd;
    @BindView(R.id.home_icon_mine)
    ImageView homeIconMine;
    @BindView(R.id.home_txt_mine)
    TextView homeTxtMine;
    @BindView(R.id.home_mine)
    LinearLayout homeMine;
    FragmentAdapter fragmentAdapter;
    private int mCurrentPosition = 0;
    private DrawerLayout mDrawerLayout;

    // TODO: 2017/7/21  详情更具issuccess判断
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        registerEventBus();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(0, new FirstFragment());
        fragmentList.add(1, new SecondFragment());
        fragmentList.add(2, new MineFragment());
        mainViewpager.setOffscreenPageLimit(0);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        mainViewpager.setAdapter(fragmentAdapter);
        mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getPermission();
        initView();
        loadup();
    }

    private void loadup() {
        OkHttpUtils
                .get()
                .url(ConstantUrl.updataurl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("updata", e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("updata", response.toString());
                        UpdataBean updataBean = new Gson().fromJson(response, UpdataBean.class);
                        if (getVersionCode(MainActivity.this) < updataBean.getVersion()) {
                            UpdataDialog.Builder builder = new UpdataDialog.Builder(MainActivity.this);
                            builder.setTitle(updataBean.getMsg())
                                    .setConfirmButton(new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            MainActivity.this.finish();

                                        }
                                    }).setCancelButton(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (isAvilible(MainActivity.this, "com.tencent.android.qqdownloader")) {
// 市场存在
                                        launchAppDetail(getApplicationContext(), "com.sxw.loan.loanorder", "com.tencent.android.qqdownloader");
                                    } else {
                                        Uri uri = Uri.parse("http://a.app.qq.com/o/simple.jsp?pkgname=com.sxw.loan.loanorder");
                                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                        startActivity(it);
                                    }
                                }
                            }).create().show();
                        }
                    }

                });
    }

    @Subscribe
    public void onEvent(MainBus event) {
        super.onEvent(event);
        Log.e("event", event.getMsg());
        if (event.getMsg().equals("0")) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (event.getMsg().equals("1")) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
//        if (mCurrentPosition > 0) {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//        } else {
//            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
//                    Gravity.RIGHT);
//        }
    }

    private void getPermission() {
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnClick({R.id.home_firstPager, R.id.home_add, R.id.home_mine})
    public void onClick(View view) {
        initTabLayout();
        switch (view.getId()) {
            case R.id.home_firstPager:
                homeIconHome.setBackgroundResource(R.mipmap.home_icon_home_red);
                homeTxtHome.setTextColor(getResources().getColor(R.color.orgred));
                mainViewpager.setCurrentItem(0, true);
                break;
            case R.id.home_add:
                homeIconOrder.setBackgroundResource(R.mipmap.tab2_select);
                homeTxtOrder.setTextColor(getResources().getColor(R.color.orgred));
                mainViewpager.setCurrentItem(1, true);
                break;
            case R.id.home_mine:
                homeIconMine.setBackgroundResource(R.mipmap.home_icon_mine_red);
                homeTxtMine.setTextColor(getResources().getColor(R.color.orgred));
                mainViewpager.setCurrentItem(2, true);
                break;
        }
    }

    //设置图标
    private void initTabLayout() {
        homeIconHome.setBackgroundResource(R.mipmap.home_icon_home_while);
        homeTxtHome.setTextColor(getResources().getColor(R.color.text_black));
        homeIconOrder.setBackgroundResource(R.mipmap.tab2_default);
        homeTxtOrder.setTextColor(getResources().getColor(R.color.text_black));
        homeIconMine.setBackgroundResource(R.mipmap.home_icon_mine_while);
        homeTxtMine.setTextColor(getResources().getColor(R.color.text_black));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initTabLayout();
        switch (mCurrentPosition) {
            case 0:
                homeIconHome.setBackgroundResource(R.mipmap.home_icon_home_red);
                homeTxtHome.setTextColor(getResources().getColor(R.color.orgred));
                break;
            case 1:
                homeIconOrder.setBackgroundResource(R.mipmap.tab2_select);
                homeTxtOrder.setTextColor(getResources().getColor(R.color.orgred));
                break;
            case 2:
                homeIconMine.setBackgroundResource(R.mipmap.home_icon_mine_red);
                homeTxtMine.setTextColor(getResources().getColor(R.color.orgred));
                break;
            default:
                break;
        }
    }

    public static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        List<String> pName = new ArrayList<String>();// 用于存储所有已安装程序的包名
// 从pinfo中将包名字逐一取出，压入pName list中
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg))
                intent.setPackage(marketPkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * get App versionCode
     *
     * @param context
     * @return
     */
    public int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        int versionCode = 0;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
