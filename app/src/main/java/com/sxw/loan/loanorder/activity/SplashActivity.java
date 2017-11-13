package com.sxw.loan.loanorder.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;

import com.sxw.loan.loanorder.MainActivity;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.databinding.ActivitySplashBinding;
import com.sy.alex_library.base.BaseActivity;

/**
 * Created by Sxw on 2017-07-20.
 */

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
        setContentView(R.layout.activity_splash);

        showContentView();
        setTitleBar(false);
        SharedPreferences sharedPreferences = getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        userid = sharedPreferences.getInt("userid", 0);
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(2000, 1000) {
        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            if (userid > 0) {
                startActivity(MainActivity.class);
                SplashActivity.this.finish();
            } else {
                startActivity(LoginActivity.class);
                SplashActivity.this.finish();
            }
        }
    };
}
