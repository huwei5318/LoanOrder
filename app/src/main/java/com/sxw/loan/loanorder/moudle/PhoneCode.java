package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017-06-05.
 */

public class PhoneCode {
    /**
     * valid : 123456
     * code : 1
     */

    private String valid;
    private String code;

    public static PhoneCode objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PhoneCode.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
