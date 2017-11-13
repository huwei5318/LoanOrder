package com.sxw.loan.loanorder.activity;

import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.databinding.ActivityQuestionBinding;
import com.sy.alex_library.base.BaseActivity;

/**
 * Created by Sxw on 2017-07-30.
 */

public class QuestionActivity extends BaseActivity<ActivityQuestionBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        showContentView();
        setTitle("常见问题");

        String path = "file:///android_asset/wenda.html";
        bindingView.agreementWebView.loadUrl(path);
    }


}
