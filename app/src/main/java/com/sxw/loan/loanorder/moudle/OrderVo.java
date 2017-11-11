package com.sxw.loan.loanorder.moudle;

import java.io.Serializable;

public class OrderVo implements Serializable {
	
	
	private static final long serialVersionUID = 7407510778191640433L;
	
	//抢单标志  0 抢单中  1已抢单
	private String qdFlag;
	//优质单 1，普通单 0
	private String productType;
	//城市 定位城市
	private String city;
	//贷款金额最小
	private Double minAmount;
	//贷款金额最大
	private Double maxAmount;
	//贷款期限
	private Integer minTime;
	private Integer maxTime;
	//企业主0，上班族 1，个体户2，学生3，公务员/事业编制4，自由职业 5
	private String workType; 
	//公积金 0 有 1 无
	private String fund;
	//社保 0 有 1无
	private String security;
	//有无抵押 0 有 1无
	private String  pledgeType;
	private Integer pageNum;
	private Integer userId;


	public String getQdFlag() {
		return qdFlag;
	}
	public void setQdFlag(String qdFlag) {
		this.qdFlag = qdFlag;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}
	public Double getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}
	public Integer getMinTime() {
		return minTime;
	}
	public void setMinTime(Integer minTime) {
		this.minTime = minTime;
	}
	public Integer getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(Integer maxTime) {
		this.maxTime = maxTime;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getPledgeType() {
		return pledgeType;
	}
	public void setPledgeType(String pledgeType) {
		this.pledgeType = pledgeType;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
