package com.sxw.loan.loanorder.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sy.alex_library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sxw on 2017-07-30.
 */

public class QuestionActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.agreement_webView)
    WebView agreementWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
        String path = "file:///android_asset/wenda.html";
        agreementWebView.loadUrl(path);
    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        this.finish();
    }
}
