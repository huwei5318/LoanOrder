package com.sxw.loan.loanorder.moudle;

/**
 * Created by Administrator on 2017-05-15.
 */

public class User {
    String phone;



    String name;
    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;
}
