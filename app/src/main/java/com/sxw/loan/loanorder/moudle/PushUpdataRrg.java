package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-07-29.
 */

public class PushUpdataRrg {

    /**
     * city : 成都市
     * flag : 0
     * userId : 2
     * id : 2
     */

    private String city;
    private String flag;
    private int userId;
    private int id;

    public static PushUpdataRrg objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PushUpdataRrg.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
