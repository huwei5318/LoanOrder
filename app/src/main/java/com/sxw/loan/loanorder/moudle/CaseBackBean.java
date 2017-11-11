package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-06-23.
 */

public class CaseBackBean {

    /**
     * amountNumber : 测试
     * integralNum : 1000
     * userid : 1
     */

    private String amountNumber;
    private int integralNum;
    private int userid;

    public static CaseBackBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), CaseBackBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getAmountNumber() {
        return amountNumber;
    }

    public void setAmountNumber(String amountNumber) {
        this.amountNumber = amountNumber;
    }

    public int getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(int integralNum) {
        this.integralNum = integralNum;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
