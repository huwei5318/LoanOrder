package com.sxw.loan.loanorder.moudle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sxw on 2017-07-23.
 */

public class BtQdDetailsBean {
    /**
     * process : null
     * order : {"orderId":113,"name":"吴清华","sexFlag":"0","city":"荆门市","btName":"白条提现","time":1499953303000,"timeS":null,"phone":"13986978002","jfaMount":10,"marriage":"1","amount":0,"creditPeriod":0,"address":"荆门市象山一路金象小区3栋505室"}
     * code : 0
     */

    private Object process;
    private OrderBean order;
    private String code;

    public static BtQdDetailsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), BtQdDetailsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object getProcess() {
        return process;
    }

    public void setProcess(Object process) {
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

//    /**
//     * process : null
//     * order : {"orderId":104,"name":"宋耀林","sexFlag":"1","city":"成都市","btName":"白条提现","time":1499776021000,"timeS":null,"phone":"13699663805","jfaMount":null,"marriage":"0","address":"向荣桥街16号3栋1单元2楼3号"}
//     * code : 0
//     */
//
//    private Object process;
//    private OrderBean order;
//    private String code;
//
//    public static BtQdDetailsBean objectFromData(String str, String key) {
//
//        try {
//            JSONObject jsonObject = new JSONObject(str);
//
//            return new Gson().fromJson(jsonObject.getString(str), BtQdDetailsBean.class);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public Object getProcess() {
//        return process;
//    }
//
//    public void setProcess(Object process) {
//        this.process = process;
//    }
//
//    public OrderBean getOrder() {
//        return order;
//    }
//
//    public void setOrder(OrderBean order) {
//        this.order = order;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public static class OrderBean {
//        /**
//         * orderId : 104
//         * name : 宋耀林
//         * sexFlag : 1
//         * city : 成都市
//         * btName : 白条提现
//         * time : 1499776021000
//         * timeS : null
//         * phone : 13699663805
//         * jfaMount : null
//         * marriage : 0
//         * address : 向荣桥街16号3栋1单元2楼3号
//         */
//
//        private int orderId;
//        private String name;
//        private String sexFlag;
//        private String city;
//        private String btName;
//        private long time;
//        private String timeS;
//        private String phone;
//        private String jfaMount;
//        private String marriage;
//        private String address;
//
//        public static OrderBean objectFromData(String str, String key) {
//
//            try {
//                JSONObject jsonObject = new JSONObject(str);
//
//                return new Gson().fromJson(jsonObject.getString(str), OrderBean.class);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        public int getOrderId() {
//            return orderId;
//        }
//
//        public void setOrderId(int orderId) {
//            this.orderId = orderId;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getSexFlag() {
//            return sexFlag;
//        }
//
//        public void setSexFlag(String sexFlag) {
//            this.sexFlag = sexFlag;
//        }
//
//        public String getCity() {
//            return city;
//        }
//
//        public void setCity(String city) {
//            this.city = city;
//        }
//
//        public String getBtName() {
//            return btName;
//        }
//
//        public void setBtName(String btName) {
//            this.btName = btName;
//        }
//
//        public long getTime() {
//            return time;
//        }
//
//        public void setTime(long time) {
//            this.time = time;
//        }
//
//        public String getTimeS() {
//            return timeS;
//        }
//
//        public void setTimeS(String timeS) {
//            this.timeS = timeS;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public void setPhone(String phone) {
//            this.phone = phone;
//        }
//
//        public String getJfaMount() {
//            return jfaMount;
//        }
//
//        public void setJfaMount(String jfaMount) {
//            this.jfaMount = jfaMount;
//        }
//
//        public String getMarriage() {
//            return marriage;
//        }
//
//        public void setMarriage(String marriage) {
//            this.marriage = marriage;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//    }

}
