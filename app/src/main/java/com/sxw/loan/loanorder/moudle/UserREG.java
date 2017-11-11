package com.sxw.loan.loanorder.moudle;

/**
 * Created by Administrator on 2017-06-02.
 */

public class UserREG {
    String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public UserREG(String phone, String password, String name) {
        this.phone = phone;
        this.password = password;
        this.name = name;
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
