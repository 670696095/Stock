package com.nkl.admin.domain;

import java.util.Date;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders extends BaseDomain {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5377028702009783267L;
	private Integer ordersId;
	private String ordersNo;
	private Integer userId;
	private String realName;
	private String goodsNo;
	private String goodsName;
	private Integer goodsCount;
	private Date ordersDate;
	private String ordersAddress;
	private String carInfo;
	private Integer sendId;
	private String sendName;
	private Integer ordersFlag;//1-待确认 2-待发货 3-已发货 4-已收货

	private String ids;
	private int delType;//1-撤销 2-取消 3-删除
	
	public String getOrdersFlagDesc(){
		switch (ordersFlag) {
		case 1:
			return "待确认";
		case 2:
			return "待发货";
		case 3:
			return "已发货";
		case 4:
			return "已收货";
		default:
			return "";
		}
	}
	
	public String getOrdersDateDesc(){
		if (ordersDate!=null) {
			return DateUtil.dateToDateString(ordersDate);
		}
		return null;
	}
	
	public String getDelTypeDesc(){
		switch (delType) {
		case 1:
			return "撤销";
		case 2:
			return "取消";
		case 3:
			return "删除";
		case 4:
			return "记录删除";	
		default:
			return "";
		}
	}
	
	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** full constructor */
	public Orders(String ordersNo, Integer userId, String realName,
			String goodsNo, String goodsName,Integer goodsCount, Date ordersDate,
			String ordersAddress, String carInfo, Integer sendId,
			String sendName, Integer ordersFlag) {
		this.ordersNo = ordersNo;
		this.userId = userId;
		this.realName = realName;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.goodsCount = goodsCount;
		this.ordersDate = ordersDate;
		this.ordersAddress = ordersAddress;
		this.carInfo = carInfo;
		this.sendId = sendId;
		this.sendName = sendName;
		this.ordersFlag = ordersFlag;
	}

	// Property accessors

	public Integer getOrdersId() {
		return this.ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	public String getOrdersNo() {
		return this.ordersNo;
	}

	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getGoodsCount() {
		return this.goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Date getOrdersDate() {
		return this.ordersDate;
	}

	public void setOrdersDate(Date ordersDate) {
		this.ordersDate = ordersDate;
	}

	public String getOrdersAddress() {
		return this.ordersAddress;
	}

	public void setOrdersAddress(String ordersAddress) {
		this.ordersAddress = ordersAddress;
	}

	public String getCarInfo() {
		return this.carInfo;
	}

	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}

	public Integer getSendId() {
		return this.sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}

	public String getSendName() {
		return this.sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public Integer getOrdersFlag() {
		return this.ordersFlag;
	}

	public void setOrdersFlag(Integer ordersFlag) {
		this.ordersFlag = ordersFlag;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getDelType() {
		return delType;
	}

	public void setDelType(int delType) {
		this.delType = delType;
	}

}