package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods extends BaseDomain {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8483836015707005680L;
	private Integer goodsId;
	private String goodsNo;
	private String goodsName;
	private Integer goodsCount;
	private String goodsDesc;

	private String ids;
	
	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** full constructor */
	public Goods(String goodsNo, String goodsName, Integer goodsCount,
			String goodsDesc) {
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.goodsCount = goodsCount;
		this.goodsDesc = goodsDesc;
	}

	// Property accessors

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsCount() {
		return this.goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getGoodsDesc() {
		return this.goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}