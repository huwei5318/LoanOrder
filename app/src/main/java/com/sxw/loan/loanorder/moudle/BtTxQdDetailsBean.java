package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-07-24.
 */

public class BtTxQdDetailsBean {

    /**
     * process : {"id":25,"orderId":113,"jlId":75,"qdMsg":"积分兑换订单成功","cancelReason":null,"dkFailReason":null,"dkSuccCredential":null,"orderStatus":0}
     * order : {"orderId":113,"name":"吴清华","sexFlag":"0","city":"荆门市","btName":"白条提现","time":1499953303000,"timeS":null,"phone":"13986978002","jfaMount":10,"marriage":"1","amount":0,"creditPeriod":0,"address":"荆门市象山一路金象小区3栋505室"}
     * code : 0
     */

    private ProcessBean process;
    private OrderBean order;
    private String code;

    public static BtTxQdDetailsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), BtTxQdDetailsBean.class);
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
         * id : 25
         * orderId : 113
         * jlId : 75
         * qdMsg : 积分兑换订单成功
         * cancelReason : null
         * dkFailReason : null
         * dkSuccCredential : null
         * orderStatus : 0
         */

        private int id;
        private int orderId;
        private int jlId;
        private String qdMsg;
        private Object cancelReason;
        private Object dkFailReason;
        private Object dkSuccCredential;
        private int orderStatus;

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

        public Object getCancelReason() {
            return cancelReason;
        }

        public void setCancelReason(Object cancelReason) {
            this.cancelReason = cancelReason;
        }

        public Object getDkFailReason() {
            return dkFailReason;
        }

        public void setDkFailReason(Object dkFailReason) {
            this.dkFailReason = dkFailReason;
        }

        public Object getDkSuccCredential() {
            return dkSuccCredential;
        }

        public void setDkSuccCredential(Object dkSuccCredential) {
            this.dkSuccCredential = dkSuccCredential;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }
    }

    public static class OrderBean {
        /**
         * orderId : 113
         * name : 吴清华
         * sexFlag : 0
         * city : 荆门市
         * btName : 白条提现
         * time : 1499953303000
         * timeS : null
         * phone : 13986978002
         * jfaMount : 10
         * marriage : 1
         * amount : 0.0
         * creditPeriod : 0
         * address : 荆门市象山一路金象小区3栋505室
         */

        private int orderId;
        private String name;
        private String sexFlag;
        private String city;
        private String btName;
        private long time;
        private Object timeS;
        private String phone;
        private int jfaMount;
        private String marriage;
        private Integer amount;
        private int creditPeriod;
        private String address;

        public static OrderBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), OrderBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSexFlag() {
            return sexFlag;
        }

        public void setSexFlag(String sexFlag) {
            this.sexFlag = sexFlag;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getBtName() {
            return btName;
        }

        public void setBtName(String btName) {
            this.btName = btName;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public Object getTimeS() {
            return timeS;
        }

        public void setTimeS(Object timeS) {
            this.timeS = timeS;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getJfaMount() {
            return jfaMount;
        }

        public void setJfaMount(int jfaMount) {
            this.jfaMount = jfaMount;
        }

        public String getMarriage() {
            return marriage;
        }

        public void setMarriage(String marriage) {
            this.marriage = marriage;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
