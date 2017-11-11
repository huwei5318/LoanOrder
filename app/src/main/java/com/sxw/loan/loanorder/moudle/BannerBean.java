package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-06-23.
 */

public class BannerBean {

    /**
     * id : 1
     * imgId : null
     * createUser : admin
     * createDate : 1496160000000
     * updateUser : sina
     * updateDate : null
     * flag : 1
     * sort : 12
     * url : http://opz7xa5jm.bkt.clouddn.com/9e019f5b-7766-47a7-b520-3bb173cd1c95
     * link : null
     * imgType : null
     */

    private int id;
    private Object imgId;
    private String createUser;
    private long createDate;
    private String updateUser;
    private Object updateDate;
    private String flag;
    private int sort;
    private String url;
    private Object link;
    private Object imgType;

    public static BannerBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), BannerBean.class);
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
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

    public Object getLink() {
        return link;
    }

    public void setLink(Object link) {
        this.link = link;
    }

    public Object getImgType() {
        return imgType;
    }

    public void setImgType(Object imgType) {
        this.imgType = imgType;
    }
}
