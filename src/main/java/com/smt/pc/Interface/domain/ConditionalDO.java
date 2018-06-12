package com.smt.pc.Interface.domain;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConditionalDO implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private Integer id;
    //票据金额
    private BigDecimal billAmount;
    //最大金额
    private BigDecimal maxAmount;
    //最小金额
    private BigDecimal minAmount;
    //1-银票 2-商票
    private Integer billType;
    //企业类型 1-国企 2-央企 3-上市 4-其他
    private Integer enterpriseType;
    //到期日
    private Char[] expiringDate;
    // 交易状态    1-待交易  2-交易中 3-交易完成
    private Integer isactive;

    public Integer getId() {
        return id;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public Integer getBillType() {
        return billType;
    }

    public Integer getEnterpriseType() {
        return enterpriseType;
    }

    public Char[] getExpiringDate() {
        return expiringDate;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public void setEnterpriseType(Integer enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public void setExpiringDate(Char[] expiringDate) {
        this.expiringDate = expiringDate;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }
}
