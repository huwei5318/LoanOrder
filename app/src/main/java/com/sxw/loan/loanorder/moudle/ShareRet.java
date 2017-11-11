package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-08-02.
 */

public class ShareRet {

    /**
     * code : 0
     * url : http://112.74.208.156/yxsm_api/share?userId=219
     */

    private String code;
    private String url;

    public static ShareRet objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ShareRet.class);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
