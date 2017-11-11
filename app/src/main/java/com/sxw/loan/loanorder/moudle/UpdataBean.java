package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-08-28.
 */

public class UpdataBean {

    /**
     * msg : 需要到应用商城更新最新版本后，方可正常使用
     * version : 1.3
     */

    private String msg;
    private double version;

    public static UpdataBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), UpdataBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }
}
