package com.smt.pc.Interface.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 票据表
 * 
 * @author lijikai
 * @email 
 * @date 2018-04-07 20:24:56
 */
public class BillDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//承兑人
	private String acceptorName;
	//票据金额
	private BigDecimal billAmount;
	//1-银票 2-商票
	private Integer billType;
	//企业类型
	private Integer enterpriseType;
	//报价方式  1-一口价 2- 竞价
	private Integer quotationMethod;
	//有无回头背书  1-有 2- 无
	private Integer backRecite;
	//背书次数
	private Integer endorseNum;
	//票据正面
	private String positiveUrl;
	//票据反面
	private String oppositeUrl;
	//需求截止时间
	private Date closingDate;
	//到期日
	private Date expiringDate;
	//交易时效
	private Date timelinessOrder;
	//状态  1-待发布 2-审核中 3-已审核 4-已关闭
	private Integer status;
	//每十万元扣款
	private String debit10;
	//年化利率
	private String interestRate;
	//手续费
	private BigDecimal serviceCharge;
	//备注
	private String remarks;
	//user_id
	private Integer userId;
	//调整天数
	private Integer adjustmentDay;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//1- 启用  0- 禁用
	private Integer isactive;



	private int diffDay;
	private String offerAmount;


	public int getDiffDay() {
		return diffDay;
	}

	public void setDiffDay(int diffDay) {
		this.diffDay = diffDay;
	}

	public String getOfferAmount() {
		return offerAmount;
	}

	public void setOfferAmount(String offerAmount) {
		this.offerAmount = offerAmount;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：承兑人
	 */
	public void setAcceptorName(String acceptorName) {
		this.acceptorName = acceptorName;
	}
	/**
	 * 获取：承兑人
	 */
	public String getAcceptorName() {
		return acceptorName;
	}
	/**
	 * 设置：票据金额
	 */
	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}
	/**
	 * 获取：票据金额
	 */
	public BigDecimal getBillAmount() {
		return billAmount;
	}
	/**
	 * 设置：1-银票 2-商票
	 */
	public void setBillType(Integer billType) {
		this.billType = billType;
	}
	/**
	 * 获取：1-银票 2-商票
	 */
	public Integer getBillType() {
		return billType;
	}
	/**
	 * 设置：企业类型
	 */
	public void setEnterpriseType(Integer enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	/**
	 * 获取：企业类型
	 */
	public Integer getEnterpriseType() {
		return enterpriseType;
	}
	/**
	 * 设置：报价方式  1-一口价 2- 竞价
	 */
	public void setQuotationMethod(Integer quotationMethod) {
		this.quotationMethod = quotationMethod;
	}
	/**
	 * 获取：报价方式  1-一口价 2- 竞价
	 */
	public Integer getQuotationMethod() {
		return quotationMethod;
	}
	/**
	 * 设置：有无回头背书  1-有 2- 无
	 */
	public void setBackRecite(Integer backRecite) {
		this.backRecite = backRecite;
	}
	/**
	 * 获取：有无回头背书  1-有 2- 无
	 */
	public Integer getBackRecite() {
		return backRecite;
	}
	/**
	 * 设置：背书次数
	 */
	public void setEndorseNum(Integer endorseNum) {
		this.endorseNum = endorseNum;
	}
	/**
	 * 获取：背书次数
	 */
	public Integer getEndorseNum() {
		return endorseNum;
	}
	/**
	 * 设置：票据正面
	 */
	public void setPositiveUrl(String positiveUrl) {
		this.positiveUrl = positiveUrl;
	}
	/**
	 * 获取：票据正面
	 */
	public String getPositiveUrl() {
		return positiveUrl;
	}
	/**
	 * 设置：票据反面
	 */
	public void setOppositeUrl(String oppositeUrl) {
		this.oppositeUrl = oppositeUrl;
	}
	/**
	 * 获取：票据反面
	 */
	public String getOppositeUrl() {
		return oppositeUrl;
	}
	/**
	 * 设置：需求截止时间
	 */
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	/**
	 * 获取：需求截止时间
	 */
	public Date getClosingDate() {
		return closingDate;
	}
	/**
	 * 设置：到期日
	 */
	public void setExpiringDate(Date expiringDate) {
		this.expiringDate = expiringDate;
	}
	/**
	 * 获取：到期日
	 */
	public Date getExpiringDate() {
		return expiringDate;
	}
	/**
	 * 设置：交易时效
	 */
	public void setTimelinessOrder(Date timelinessOrder) {
		this.timelinessOrder = timelinessOrder;
	}
	/**
	 * 获取：交易时效
	 */
	public Date getTimelinessOrder() {
		return timelinessOrder;
	}
	/**
	 * 设置：状态  1-待审核 2-已发布 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态  1-待审核 2-已发布 
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：每十万元扣款
	 */
	public void setDebit10(String debit10) {
		this.debit10 = debit10;
	}
	/**
	 * 获取：每十万元扣款
	 */
	public String getDebit10() {
		return debit10;
	}
	/**
	 * 设置：年化利率
	 */
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	/**
	 * 获取：年化利率
	 */
	public String getInterestRate() {
		return interestRate;
	}
	/**
	 * 设置：手续费
	 */
	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	/**
	 * 获取：手续费
	 */
	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：user_id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：user_id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：调整天数
	 */
	public void setAdjustmentDay(Integer adjustmentDay) {
		this.adjustmentDay = adjustmentDay;
	}
	/**
	 * 获取：调整天数
	 */
	public Integer getAdjustmentDay() {
		return adjustmentDay;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	/**
	 * 获取：
	 */
	public Integer getIsactive() {
		return isactive;
	}
}
