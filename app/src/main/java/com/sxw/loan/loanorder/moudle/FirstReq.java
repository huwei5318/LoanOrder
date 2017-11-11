package com.sxw.loan.loanorder.moudle;

/**
 * Created by Sxw on 2017-07-18.
 */

public class FirstReq {
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * city :
     * fund : null
     * maxAmount : null
     * maxTime : null
     * minAmount : null
     * minTime : null
     * pageNum : 1
     * pledgeType : null
     * productType : null
     * qdFlag : 0
     * security : null
     * workType : null
     */
    private int userid;
    private String city;
    private Object fund;
    private Object maxAmount;
    private Object maxTime;
    private Object minAmount;
    private Object minTime;
    private int pageNum;
    private Object pledgeType;
    private Object productType;
    private String qdFlag;
    private Object security;
    private Object workType;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getFund() {
        return fund;
    }

    public void setFund(Object fund) {
        this.fund = fund;
    }

    public Object getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Object maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Object getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Object maxTime) {
        this.maxTime = maxTime;
    }

    public Object getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Object minAmount) {
        this.minAmount = minAmount;
    }

    public Object getMinTime() {
        return minTime;
    }

    public void setMinTime(Object minTime) {
        this.minTime = minTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Object getPledgeType() {
        return pledgeType;
    }

    public void setPledgeType(Object pledgeType) {
        this.pledgeType = pledgeType;
    }

    public Object getProductType() {
        return productType;
    }

    public void setProductType(Object productType) {
        this.productType = productType;
    }

    public String getQdFlag() {
        return qdFlag;
    }

    public void setQdFlag(String qdFlag) {
        this.qdFlag = qdFlag;
    }

    public Object getSecurity() {
        return security;
    }

    public void setSecurity(Object security) {
        this.security = security;
    }

    public Object getWorkType() {
        return workType;
    }

    public void setWorkType(Object workType) {
        this.workType = workType;
    }

//    /**
//     * city :
//     * fund : 0
//     * maxAmount : 5000
//     * maxTime : 12
//     * minAmount : 0
//     * minTime : 1
//     * pageNum : 1
//     * pledgeType : 0
//     * productType : -1
//     * qdFlag : 0
//     * security : 0
//     * workType : 0
//     */
//
//    private String city;
//    private String fund;
//    private int maxAmount;
//    private int maxTime;
//    private int minAmount;
//    private int minTime;
//    private int pageNum;
//    private String pledgeType;
//    private String productType;
//    private String qdFlag;
//    private String security;
//    private String workType;
//
//    public static FirstReq objectFromData(String str, String key) {
//
//        try {
//            JSONObject jsonObject = new JSONObject(str);
//
//            return new Gson().fromJson(jsonObject.getString(str), FirstReq.class);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getFund() {
//        return fund;
//    }
//
//    public void setFund(String fund) {
//        this.fund = fund;
//    }
//
//    public int getMaxAmount() {
//        return maxAmount;
//    }
//
//    public void setMaxAmount(int maxAmount) {
//        this.maxAmount = maxAmount;
//    }
//
//    public int getMaxTime() {
//        return maxTime;
//    }
//
//    public void setMaxTime(int maxTime) {
//        this.maxTime = maxTime;
//    }
//
//    public int getMinAmount() {
//        return minAmount;
//    }
//
//    public void setMinAmount(int minAmount) {
//        this.minAmount = minAmount;
//    }
//
//    public int getMinTime() {
//        return minTime;
//    }
//
//    public void setMinTime(int minTime) {
//        this.minTime = minTime;
//    }
//
//    public int getPageNum() {
//        return pageNum;
//    }
//
//    public void setPageNum(int pageNum) {
//        this.pageNum = pageNum;
//    }
//
//    public String getPledgeType() {
//        return pledgeType;
//    }
//
//    public void setPledgeType(String pledgeType) {
//        this.pledgeType = pledgeType;
//    }
//
//    public String getProductType() {
//        return productType;
//    }
//
//    public void setProductType(String productType) {
//        this.productType = productType;
//    }
//
//    public String getQdFlag() {
//        return qdFlag;
//    }
//
//    public void setQdFlag(String qdFlag) {
//        this.qdFlag = qdFlag;
//    }
//
//    public String getSecurity() {
//        return security;
//    }
//
//    public void setSecurity(String security) {
//        this.security = security;
//    }
//
//    public String getWorkType() {
//        return workType;
//    }
//
//    public void setWorkType(String workType) {
//        this.workType = workType;
//    }

}
