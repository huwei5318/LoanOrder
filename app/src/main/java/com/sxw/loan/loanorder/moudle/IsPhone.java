package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017-06-02.
 */

public class IsPhone {
    /**
     * code : 1
     */

    private String code;

    public static IsPhone objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), IsPhone.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
