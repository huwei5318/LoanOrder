package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-07-22.
 */

public class FirstOrderDetails {

    /**
     * orderId : 105
     * userId : 1
     */

    private int orderId;
    private int userId;

    public static FirstOrderDetails objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), FirstOrderDetails.class);
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
