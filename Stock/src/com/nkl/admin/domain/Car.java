package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

/**
 * Car entity. @author MyEclipse Persistence Tools
 */

public class Car extends BaseDomain  {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 443524298537401805L;
	private Integer carId;
	private String carModel;
	private String carNumber;
	private Integer carFlag;//1：闲置 2：在用
	
	private String ids;

	public String getCarFlagDesc(){
		switch (carFlag) {
		case 1:
			return "闲置";
		case 2:
			return "在用";
		default:
			return "";
		}
	}
	
	// Constructors

	/** default constructor */
	public Car() {
	}

	/** full constructor */
	public Car(String carModel, String carNumber, Integer carFlag) {
		this.carModel = carModel;
		this.carNumber = carNumber;
		this.carFlag = carFlag;
	}

	// Property accessors

	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarModel() {
		return this.carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarNumber() {
		return this.carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Integer getCarFlag() {
		return this.carFlag;
	}

	public void setCarFlag(Integer carFlag) {
		this.carFlag = carFlag;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}