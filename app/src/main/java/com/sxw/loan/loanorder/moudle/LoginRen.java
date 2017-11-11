package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-06-15.
 */

public class LoginRen {

    /**
     * code : 0
     * user : {"id":75,"name":"及速9339","password":"e10adc3949ba59abbe56e057f20f883e","age":null,"phone":"18013149339","flag":"1","integralSum":80,"integralunused":-441,"validtime":null,"vip":"0","amount":80}
     */

    private String code;
    private UserBean user;

    public static LoginRen objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), LoginRen.class);
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 75
         * name : 及速9339
         * password : e10adc3949ba59abbe56e057f20f883e
         * age : null
         * phone : 18013149339
         * flag : 1
         * integralSum : 80
         * integralunused : -441
         * validtime : null
         * vip : 0
         * amount : 80
         */

        private int id;
        private String name;
        private String password;
        private Object age;
        private String phone;
        private String flag;
        private int integralSum;
        private int integralunused;
        private Object validtime;
        private String vip;
        private int amount;

        public static UserBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), UserBean.class);
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public int getIntegralSum() {
            return integralSum;
        }

        public void setIntegralSum(int integralSum) {
            this.integralSum = integralSum;
        }

        public int getIntegralunused() {
            return integralunused;
        }

        public void setIntegralunused(int integralunused) {
            this.integralunused = integralunused;
        }

        public Object getValidtime() {
            return validtime;
        }

        public void setValidtime(Object validtime) {
            this.validtime = validtime;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
}
