package com.smt.pc.Interface.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户表
 * 
 * @author lijikai
 * @email 
 * @date 2018-03-23 13:50:12
 */
public class UserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户昵称
	private String userName;
	//真实姓名
	private String realName;
	//密码
	private String passWord;
	//手机号码
	private String phoneNumber;
	//推荐人
	private String refereeIe;
	//1-买卖房 2-经纪人
	private Integer userType;
	//0-未认证 1-已认证
	private Integer authentication;
	//邮箱
	private String eamil;
	//学历
	private String education;
	//婚姻状况 1-保密 2-已婚 3-未婚
	private Integer maritalStatus;
	//邀请码
	private String invitationCode;
	//创建时间
	private Date createTime;
	//
	private Date updateTime;
	//是否禁用   0-禁用 1-活跃
	private String isactive;

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
	 * 设置：用户昵称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户昵称
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * 设置：密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * 获取：密码
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * 设置：手机号码
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * 获取：手机号码
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * 设置：推荐人
	 */
	public void setRefereeIe(String refereeIe) {
		this.refereeIe = refereeIe;
	}
	/**
	 * 获取：推荐人
	 */
	public String getRefereeIe() {
		return refereeIe;
	}
	/**
	 * 设置：1-买卖房 2-经纪人
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	/**
	 * 获取：1-买卖房 2-经纪人
	 */
	public Integer getUserType() {
		return userType;
	}
	/**
	 * 设置：0-未认证 1-已认证
	 */
	public void setAuthentication(Integer authentication) {
		this.authentication = authentication;
	}
	/**
	 * 获取：0-未认证 1-已认证
	 */
	public Integer getAuthentication() {
		return authentication;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEamil(String eamil) {
		this.eamil = eamil;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEamil() {
		return eamil;
	}
	/**
	 * 设置：学历
	 */
	public void setEducation(String education) {
		this.education = education;
	}
	/**
	 * 获取：学历
	 */
	public String getEducation() {
		return education;
	}
	/**
	 * 设置：婚姻状况 1-保密 2-已婚 3-未婚
	 */
	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * 获取：婚姻状况 1-保密 2-已婚 3-未婚
	 */
	public Integer getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * 设置：邀请码
	 */
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	/**
	 * 获取：邀请码
	 */
	public String getInvitationCode() {
		return invitationCode;
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
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：是否禁用   0-禁用 1-活跃
	 */
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	/**
	 * 获取：是否禁用   0-禁用 1-活跃
	 */
	public String getIsactive() {
		return isactive;
	}
}
