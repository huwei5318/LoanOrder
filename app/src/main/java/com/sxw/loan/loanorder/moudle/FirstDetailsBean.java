package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-07-23.
 */

public class FirstDetailsBean {


    /**
     * process : {"id":16,"orderId":103,"jlId":75,"qdMsg":"抢单成功","cancelReason":null,"dkFailReason":null,"dkSuccCredential":null,"orderStatus":0,"createTime":null,"updateTime":null}
     * order : {"id":103,"name":"宋耀林","age":null,"sex":"1","phoneNumber":"13699663805","idCode":"51132119921210008","workType":null,"workAddress":"轻盈瑜伽馆","city":"成都市","workYear":null,"socialSecurity":"1","accumulationFund":"1","creditRecord":null,"creditLimit":"2","education":null,"monthlyWages":null,"debtState":null,"householdAssets":null,"pledgeType":"0","accpetType":null,"state":"1","userId":130,"productId":-1,"productName":"大额贷款","createTime":1499770221000,"amount":20000,"creditPeriod":3,"remarks":"","schoolname":null,"educational":null,"classes":null,"isSchool":null,"schoolAccount":null,"used":null,"jlId":null,"jAmount":10,"jfAmount":10,"taoFlag":"0"}
     * code : 0
     */

    private ProcessBean process;
    private OrderBean order;
    private String code;

    public static FirstDetailsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), FirstDetailsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ProcessBean getProcess() {
        return process;
    }

    public void setProcess(ProcessBean process) {
        this.process = process;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class ProcessBean {
        /**
         * id : 16
         * orderId : 103
         * jlId : 75
         * qdMsg : 抢单成功
         * cancelReason : null
         * dkFailReason : null
         * dkSuccCredential : null
         * orderStatus : 0
         * createTime : null
         * updateTime : null
         */

        private int id;
        private int orderId;
        private int jlId;
        private String qdMsg;
        private String cancelReason;
        private String dkFailReason;
        private String dkSuccCredential;
        private int orderStatus;
        private String createTime;
        private String updateTime;

        public static ProcessBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ProcessBean.class);
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

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getJlId() {
            return jlId;
        }

        public void setJlId(int jlId) {
            this.jlId = jlId;
        }

        public String getQdMsg() {
            return qdMsg;
        }

        public void setQdMsg(String qdMsg) {
            this.qdMsg = qdMsg;
        }

        public String getCancelReason() {
            return cancelReason;
        }

        public void setCancelReason(String cancelReason) {
            this.cancelReason = cancelReason;
        }

        public String getDkFailReason() {
            return dkFailReason;
        }

        public void setDkFailReason(String dkFailReason) {
            this.dkFailReason = dkFailReason;
        }

        public String getDkSuccCredential() {
            return dkSuccCredential;
        }

        public void setDkSuccCredential(String dkSuccCredential) {
            this.dkSuccCredential = dkSuccCredential;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class OrderBean {
        /**
         * id : 103
         * name : 宋耀林
         * age : null
         * sex : 1
         * phoneNumber : 13699663805
         * idCode : 51132119921210008
         * workType : null
         * workAddress : 轻盈瑜伽馆
         * city : 成都市
         * workYear : null
         * socialSecurity : 1
         * accumulationFund : 1
         * creditRecord : null
         * creditLimit : 2
         * education : null
         * monthlyWages : null
         * debtState : null
         * householdAssets : null
         * pledgeType : 0
         * accpetType : null
         * state : 1
         * userId : 130
         * productId : -1
         * productName : 大额贷款
         * createTime : 1499770221000
         * amount : 20000.0
         * creditPeriod : 3
         * remarks :
         * schoolname : null
         * educational : null
         * classes : null
         * isSchool : null
         * schoolAccount : null
         * used : null
         * jlId : null
         * jAmount : 10
         * jfAmount : 10
         * taoFlag : 0
         */

        private int id;
        private String name;
        private String age;
        private String sex;
        private String phoneNumber;
        private String idCode;
        private String workType;
        private String workAddress;
        private String city;
        private String workYear;
        private String socialSecurity;
        private String accumulationFund;
        private String creditRecord;
        private String creditLimit;
        private String education;
        private String monthlyWages;
        private String debtState;
        private String householdAssets;
        private String pledgeType;
        private String accpetType;
        private String state;
        private int userId;
        private int productId;
        private String productName;
        private long createTime;
        private Integer amount;
        private int creditPeriod;
        private String remarks;
        private String schoolname;
        private String educational;
        private String classes;
        private String isSchool;
        private String schoolAccount;
        private String used;
        private String jlId;
        private int jAmount;
        private int jfAmount;
        private String taoFlag;

        public static OrderBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), OrderBean.class);
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

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getIdCode() {
            return idCode;
        }

        public void setIdCode(String idCode) {
            this.idCode = idCode;
        }

        public String getWorkType() {
            return workType;
        }

        public void setWorkType(String workType) {
            this.workType = workType;
        }

        public String getWorkAddress() {
            return workAddress;
        }

        public void setWorkAddress(String workAddress) {
            this.workAddress = workAddress;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getWorkYear() {
            return workYear;
        }

        public void setWorkYear(String workYear) {
            this.workYear = workYear;
        }

        public String getSocialSecurity() {
            return socialSecurity;
        }

        public void setSocialSecurity(String socialSecurity) {
            this.socialSecurity = socialSecurity;
        }

        public String getAccumulationFund() {
            return accumulationFund;
        }

        public void setAccumulationFund(String accumulationFund) {
            this.accumulationFund = accumulationFund;
        }

        public String getCreditRecord() {
            return creditRecord;
        }

        public void setCreditRecord(String creditRecord) {
            this.creditRecord = creditRecord;
        }

        public String getCreditLimit() {
            return creditLimit;
        }

        public void setCreditLimit(String creditLimit) {
            this.creditLimit = creditLimit;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getMonthlyWages() {
            return monthlyWages;
        }

        public void setMonthlyWages(String monthlyWages) {
            this.monthlyWages = monthlyWages;
        }

        public String getDebtState() {
            return debtState;
        }

        public void setDebtState(String debtState) {
            this.debtState = debtState;
        }

        public String getHouseholdAssets() {
            return householdAssets;
        }

        public void setHouseholdAssets(String householdAssets) {
            this.householdAssets = householdAssets;
        }

        public String getPledgeType() {
            return pledgeType;
        }

        public void setPledgeType(String pledgeType) {
            this.pledgeType = pledgeType;
        }

        public String getAccpetType() {
            return accpetType;
        }

        public void setAccpetType(String accpetType) {
            this.accpetType = accpetType;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public int getCreditPeriod() {
            return creditPeriod;
        }

        public void setCreditPeriod(int creditPeriod) {
            this.creditPeriod = creditPeriod;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public Object getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(String schoolname) {
            this.schoolname = schoolname;
        }

        public Object getEducational() {
            return educational;
        }

        public void setEducational(String educational) {
            this.educational = educational;
        }

        public String getClasses() {
            return classes;
        }

        public void setClasses(String classes) {
            this.classes = classes;
        }

        public String getIsSchool() {
            return isSchool;
        }

        public void setIsSchool(String isSchool) {
            this.isSchool = isSchool;
        }

        public String getSchoolAccount() {
            return schoolAccount;
        }

        public void setSchoolAccount(String schoolAccount) {
            this.schoolAccount = schoolAccount;
        }

        public String getUsed() {
            return used;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public String getJlId() {
            return jlId;
        }

        public void setJlId(String jlId) {
            this.jlId = jlId;
        }

        public int getJAmount() {
            return jAmount;
        }

        public void setJAmount(int jAmount) {
            this.jAmount = jAmount;
        }

        public int getJfAmount() {
            return jfAmount;
        }

        public void setJfAmount(int jfAmount) {
            this.jfAmount = jfAmount;
        }

        public String getTaoFlag() {
            return taoFlag;
        }

        public void setTaoFlag(String taoFlag) {
            this.taoFlag = taoFlag;
        }
    }
}
