package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-07-23.
 */

public class BtTxQdReg {

    /**
     * id : 105
     * jlId : 1
     */

    private int id;
    private int jlId;

    public static BtTxQdReg objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), BtTxQdReg.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJlId() {
        return jlId;
    }

    public void setJlId(int jlId) {
        this.jlId = jlId;
    }
}
