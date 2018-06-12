package com.smt.pc.Interface.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户操作日志表
 * 
 * @author lijikai
 * @email 
 * @date 2018-04-02 11:25:15
 */
public class OperationLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户id
	private Integer userId;
	//操作id
	private Integer operationId;
	//操作时间
	private Date operationTime;
	//操作类型 1-查看 2-点击
	private Integer operationType;
	//创建时间
	private Date creationTime;
	//
	private Date updateTime;
	//是否启用
	private Integer isactive;

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
	 * 设置：用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：操作id
	 */
	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}
	/**
	 * 获取：操作id
	 */
	public Integer getOperationId() {
		return operationId;
	}
	/**
	 * 设置：操作时间
	 */
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getOperationTime() {
		return operationTime;
	}
	/**
	 * 设置：操作类型 1-查看 2-点击
	 */
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	/**
	 * 获取：操作类型 1-查看 2-点击
	 */
	public Integer getOperationType() {
		return operationType;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreationTime() {
		return creationTime;
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
	 * 设置：是否启用
	 */
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	/**
	 * 获取：是否启用
	 */
	public Integer getIsactive() {
		return isactive;
	}
}
