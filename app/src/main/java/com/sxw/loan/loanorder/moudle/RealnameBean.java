package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-08-03.
 */

public class RealnameBean {

    /**
     * code : 0
     * userDetails : {"id":62,"userId":86,"name":"姚成兵","sex":null,"idCode":"342425199005296710","permanentAddress":null,"education":null,"marriage":null,"householdAssets":"北京市","idFront":"http://osef7u7wk.bkt.clouddn.com/apple150244456817","idOpposite":"测试一下","idHand":"http://osef7u7wk.bkt.clouddn.com/apple150244455217","qq":"05518384136","weixin":null,"state":"0","createTime":1501743435000,"updateTime":1502444578000,"remarks":"请上传真实身份证信息后重新提交"}
     */

    private String code;
    private UserDetailsBean userDetails;

    public static RealnameBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), RealnameBean.class);
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

    public UserDetailsBean getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsBean userDetails) {
        this.userDetails = userDetails;
    }

    public static class UserDetailsBean {
        /**
         * id : 62
         * userId : 86
         * name : 姚成兵
         * sex : null
         * idCode : 342425199005296710
         * permanentAddress : null
         * education : null
         * marriage : null
         * householdAssets : 北京市
         * idFront : http://osef7u7wk.bkt.clouddn.com/apple150244456817
         * idOpposite : 测试一下
         * idHand : http://osef7u7wk.bkt.clouddn.com/apple150244455217
         * qq : 05518384136
         * weixin : null
         * state : 0
         * createTime : 1501743435000
         * updateTime : 1502444578000
         * remarks : 请上传真实身份证信息后重新提交
         */

        private int id;
        private int userId;
        private String name;
        private Object sex;
        private String idCode;
        private Object permanentAddress;
        private Object education;
        private Object marriage;
        private String householdAssets;
        private String idFront;
        private String idOpposite;
        private String idHand;
        private String qq;
        private Object weixin;
        private String state;
        private long createTime;
        private long updateTime;
        private String remarks;

        public static UserDetailsBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), UserDetailsBean.class);
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public String getIdCode() {
            return idCode;
        }

        public void setIdCode(String idCode) {
            this.idCode = idCode;
        }

        public Object getPermanentAddress() {
            return permanentAddress;
        }

        public void setPermanentAddress(Object permanentAddress) {
            this.permanentAddress = permanentAddress;
        }

        public Object getEducation() {
            return education;
        }

        public void setEducation(Object education) {
            this.education = education;
        }

        public Object getMarriage() {
            return marriage;
        }

        public void setMarriage(Object marriage) {
            this.marriage = marriage;
        }

        public String getHouseholdAssets() {
            return householdAssets;
        }

        public void setHouseholdAssets(String householdAssets) {
            this.householdAssets = householdAssets;
        }

        public String getIdFront() {
            return idFront;
        }

        public void setIdFront(String idFront) {
            this.idFront = idFront;
        }

        public String getIdOpposite() {
            return idOpposite;
        }

        public void setIdOpposite(String idOpposite) {
            this.idOpposite = idOpposite;
        }

        public String getIdHand() {
            return idHand;
        }

        public void setIdHand(String idHand) {
            this.idHand = idHand;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
            this.weixin = weixin;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
