package com.smt.pc.Interface.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @author lijikai
 * @email
 * @date 2018-04-17 13:24:38
 */
public class BannerDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//banner标题
	private String bannerName;
	//url
	private String bannerUrl;
	//
	private String bannerBackground;
	//模块
	private String bannerModel;
	//点击跳转Url
	private String jumpUrl;
	//
	private Date creatTime;
	//
	private Date updateTime;
	//排序
	private Integer bannerSort;
	//是否启用
	private String isActive;

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
	 * 设置：banner标题
	 */
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	/**
	 * 获取：banner标题
	 */
	public String getBannerName() {
		return bannerName;
	}
	/**
	 * 设置：url
	 */
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	/**
	 * 获取：url
	 */
	public String getBannerUrl() {
		return bannerUrl;
	}
	/**
	 * 设置：
	 */
	public void setBannerBackground(String bannerBackground) {
		this.bannerBackground = bannerBackground;
	}
	/**
	 * 获取：
	 */
	public String getBannerBackground() {
		return bannerBackground;
	}
	/**
	 * 设置：模块
	 */
	public void setBannerModel(String bannerModel) {
		this.bannerModel = bannerModel;
	}
	/**
	 * 获取：模块
	 */
	public String getBannerModel() {
		return bannerModel;
	}
	/**
	 * 设置：点击跳转Url
	 */
	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
	/**
	 * 获取：点击跳转Url
	 */
	public String getJumpUrl() {
		return jumpUrl;
	}
	/**
	 * 设置：
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatTime() {
		return creatTime;
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
	 * 设置：排序
	 */
	public void setBannerSort(Integer bannerSort) {
		this.bannerSort = bannerSort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getBannerSort() {
		return bannerSort;
	}
	/**
	 * 设置：是否启用
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/**
	 * 获取：是否启用
	 */
	public String getIsActive() {
		return isActive;
	}
}
