package com.sy.alex_library.base;

/**
 * Created by alexFugui on 2017/3/2.
 *
 */
interface BaseView {

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg);

    void showException(String msg);

//    void showNetError();
//
//    void showNormal();

}
