package com.sxw.loan.loanorder.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.databinding.ActivityWebviewBinding;
import com.sy.alex_library.base.BaseActivity;

/**
 * Created by Administrator on 2017-05-08.
 */

public class WebViewActivity extends BaseActivity<ActivityWebviewBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        showContentView();
        setTitle("详情");


        String url = getIntent().getExtras().getString("web");
        WebSettings settings = bindingView.webview.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        bindingView.webview.loadUrl(url);
        bindingView.webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }


}
