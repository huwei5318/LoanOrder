package com.sxw.loan.loanorder.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sy.alex_library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017-05-08.
 */

public class WebVIewActivity extends BaseActivity {
    WebView webview;
    @BindView(R.id.btn_back)
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        String url = getIntent().getExtras().getString("web");
        webview = (WebView) findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }


    @OnClick(R.id.btn_back)
    public void onClick() {
        this.finish();
    }
}
