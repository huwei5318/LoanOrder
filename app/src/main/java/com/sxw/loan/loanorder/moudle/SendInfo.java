package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * app端请求的支付的实体信息
 *
 * @author ycb
 */

public class SendInfo {


    /**
     * flag : 1
     * goodsId : 1
     * price : 87
     * userId : 1
     */

    private String flag;
    private String goodsId;
    private double price;
    private int userId;

    public static SendInfo objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), SendInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
