package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017-05-31.
 */

public class Banner {
    /**
     * id : 1
     * imgId : null
     * createUser : null
     * createDate : 1496160000000
     * updateUser : sina
     * updateDate : null
     * flag : 1
     * sort : 12
     * url : http://opz7xa5jm.bkt.clouddn.com/f5a2fc56-6bb8-4ad4-b3bf-5e6cf85
     */

    private int id;
    private Object imgId;
    private Object createUser;
    private long createDate;
    private String updateUser;
    private Object updateDate;
    private String flag;
    private int sort;
    private String url;

    public static Banner objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Banner.class);
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

    public Object getImgId() {
        return imgId;
    }

    public void setImgId(Object imgId) {
        this.imgId = imgId;
    }

    public Object getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Object createUser) {
        this.createUser = createUser;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
