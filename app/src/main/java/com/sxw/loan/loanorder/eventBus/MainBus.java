package com.sxw.loan.loanorder.eventBus;

/**
 * Created by Sxw on 2017-07-20.
 */

public class MainBus {
    private final String msg;

    public MainBus(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
