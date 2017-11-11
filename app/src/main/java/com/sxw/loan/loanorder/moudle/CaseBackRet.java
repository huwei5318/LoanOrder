package com.sxw.loan.loanorder.moudle;

/**
 * Created by Sxw on 2017-06-23.
 */

public class CaseBackRet {

    /**
     * id : 64
     * userid : 60
     * amountNumber : djdhdbfbdnnf
     * integralNum : 464
     * state : 0
     * remark : null
     * handler : null
     * createTime : 1498269415000
     * auditTime : null
     */

    private String amountNumber;
    private int integralNum;

    public String getAmountNumber() {
        return amountNumber;
    }

    public void setAmountNumber(String amountNumber) {
        this.amountNumber = amountNumber;
    }

    public int getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(int integralNum) {
        this.integralNum = integralNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private String state;

    private String createTime;


}
