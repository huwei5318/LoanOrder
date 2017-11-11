package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-08-02.
 */

public class ShareBeab {

    /**
     * userId : 1
     */

    private int userId;

    public static ShareBeab objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ShareBeab.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
