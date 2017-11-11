package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-07-29.
 */

public class PushRet {

    /**
     * code : 0
     * push : {"id":5,"userId":2,"city":"成都市2","flag":"0","state":null,"createDate":null,"updateDate":null}
     */

    private String code;
    private PushBean push;

    public static PushRet objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), PushRet.class);
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

    public PushBean getPush() {
        return push;
    }

    public void setPush(PushBean push) {
        this.push = push;
    }

    public static class PushBean {
        /**
         * id : 5
         * userId : 2
         * city : 成都市2
         * flag : 0
         * state : null
         * createDate : null
         * updateDate : null
         */

        private int id;
        private int userId;
        private String city;
        private String flag;
        private Object state;
        private Object createDate;
        private Object updateDate;

        public static PushBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), PushBean.class);
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }
    }
}
