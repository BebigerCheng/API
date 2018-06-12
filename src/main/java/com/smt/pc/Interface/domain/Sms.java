package com.smt.pc.Interface.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信表
 *
 * @author chengliang
 * @email
 * @date 2018-03-27
 */
public class Sms implements Serializable {

    public Integer id;
    public Integer userId;//用户id
    public String phoneNum;//手机号
    public Integer isEffective;//是否过期
    public Date createTime;//创建时间
    public String checkCode;//验证码

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getIsEffective() {
        return isEffective;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setIsEffective(int isEffective) {
        this.isEffective = isEffective;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", userId=" + userId +
                ", phoneNum='" + phoneNum + '\'' +
                ", isEffective=" + isEffective +
                ", createTime=" + createTime +
                ", checkCode='" + checkCode + '\'' +
                '}';
    }
}
