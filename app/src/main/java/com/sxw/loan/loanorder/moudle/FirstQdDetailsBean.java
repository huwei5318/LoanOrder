package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-07-24.
 */

public class FirstQdDetailsBean {


    /**
     * orderId : 104
     * orderStatus : 1
     * cancelReason : 取消工单原因
     * dkFailReason : 放贷失败原因
     * dkSuccCredential : 贷款成功凭据
     * jlId : 75
     */

    private int orderId;
    private int orderStatus;
    private String cancelReason;
    private String dkFailReason;
    private String dkSuccCredential;
    private int jlId;

    public static FirstQdDetailsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), FirstQdDetailsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getDkFailReason() {
        return dkFailReason;
    }

    public void setDkFailReason(String dkFailReason) {
        this.dkFailReason = dkFailReason;
    }

    public String getDkSuccCredential() {
        return dkSuccCredential;
    }

    public void setDkSuccCredential(String dkSuccCredential) {
        this.dkSuccCredential = dkSuccCredential;
    }

    public int getJlId() {
        return jlId;
    }

    public void setJlId(int jlId) {
        this.jlId = jlId;
    }
}
