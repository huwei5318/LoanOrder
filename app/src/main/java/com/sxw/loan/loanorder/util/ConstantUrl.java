package com.sxw.loan.loanorder.util;

/**
 * url管理
 * Created by Sxw on 2017-07-01.
 */

public class ConstantUrl {
    //是否注册
    public static final String isphone = "http://112.74.208.156/yxsm_api/jl/login/isPhoneOne";
    //发送验证码
    public static final String code = "http://112.74.208.156/yxsm_api/jl/login/getValidMM";
    //注册
    public static final String reg = "http://112.74.208.156/yxsm_api/jl/login/register";
    //登录
    public static final String login = "http://112.74.208.156/yxsm_api/jl/login";
    //ciugaimia
    public static final String forgotpassurl = "http://112.74.208.156/yxsm_api/jl/login/updatePwd";
    //ciugaimia
    public static final String qiniuurl = "http://112.74.208.156/yxsm_api/qiniu/getQiNiuToken";
    //实名
    public static final String realnameurl = "http://112.74.208.156/yxsm_api/jd/user/insert";
    //实名查询
    public static final String realnamesearch = "http://112.74.208.156/yxsm_api/jd/user/getUserDetailsOne";
    //充值金额接口
    public static final String jburl = "http://112.74.208.156/yxsm_api/price/jd";
    //充值接口
    public static final String aliurl = "http://112.74.208.156/yxsm_api/payment1/sign";
    //充值接口
    public static final String firsturl = "http://112.74.208.156/yxsm_api/jd/order/list";
    //订单首页详情
    public static final String Firsturlorderdetails = "http://112.74.208.156/yxsm_api/jd/order/details";
    //订单首页抢单
    public static final String Firstqdurl = "http://112.74.208.156/yxsm_api/jd/order/grabOrder";
    //订单首页抢单处理
    public static final String Firstqddetailsurl = "http://112.74.208.156/yxsm_api/orderProcess/update";
    //白条订单
    public static final String btorder = "http://112.74.208.156/yxsm_api/jd/order/listBt";
    //白条订单详情
    public static final String bttxurl = "http://112.74.208.156/yxsm_api/jd/order/detailsBt";
    //白条抢单处理
    public static final String bttxqdupurl = "http://112.74.208.156/yxsm_api/orderProcess/updateBt";
    //白条抢单
    public static final String bttxqdurl = "http://112.74.208.156/yxsm_api/jd/order/grabOrderBt";
    //淘单列表
    public static final String tdqdurl = "http://112.74.208.156/yxsm_api/jd/order/listTyt";
    //淘单详情
    public static final String tdqddetailsurl = "http://112.74.208.156/yxsm_api/jd/order/detailsTyt";
    //淘单抢单接口
    public static final String tdqdqdurl = "http://112.74.208.156/yxsm_api/jd/order/grabOrderBt";
    //淘单处理
    public static final String tdqdqdupdataurl = "http://112.74.208.156/yxsm_api/orderProcess/updateBt";
    //个人中心我得订单
    public static final String mineorderurl = "http://112.74.208.156/yxsm_api/jd/order/listGrabOrder";
    //个人中心我得订单详情
    public static final String mineorderdetailsurl = "http://112.74.208.156/yxsm_api/jd/order/grabOrderDetail";
    //个人中心订单处理
    public static final String mineorderqdurl = "http://112.74.208.156/yxsm_api/orderProcess/updateGr";
    //个人中心我得白条
    public static final String minewhiteurl = "http://112.74.208.156/yxsm_api/jd/order/listGrabOrderBt";
    //个人中心我得白条详情
    public static final String minewhiteqdurl = "http://112.74.208.156/yxsm_api/jd/order/grabOrderDetailBt";
    //查询推送
    public static final String pushurl = "http://112.74.208.156/yxsm_api/jd/push/getPush";
    //跟新推送
    public static final String updatapushurl = "http://112.74.208.156/yxsm_api/jd/push";
    //banner
    public static final String bannerurl = "http://112.74.208.156/yxsm_api/roll/listJd";
    //share
    public static final String shareurl = "http://112.74.208.156/yxsm_api/jd/share/url";
    //    updataurl
    public static final String updataurl = "http://112.74.208.156/yxsm_api/jd/version";

}
