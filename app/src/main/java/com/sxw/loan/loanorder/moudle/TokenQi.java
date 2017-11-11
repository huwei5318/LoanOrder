package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-06-10.
 */

public class TokenQi {
    /**
     * token : 212Q9IWVQB8b993hYZOo3xWBziEBuNoe8T8QWUqY:NpKoeFU7sGUDRuqal58fL4eDs1g=:eyJzY29wZSI6InljYjIwMTcwNTE1IiwiZGVhZGxpbmUiOjE0OTY5NzcxODV9
     */

    private String token;

    public static TokenQi objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), TokenQi.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
