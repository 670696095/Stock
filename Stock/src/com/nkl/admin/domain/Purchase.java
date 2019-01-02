package com.nkl.admin.domain;

import java.util.Date;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

/**
 * Purchase entity. @author MyEclipse Persistence Tools
 */

public class Purchase extends BaseDomain {

	// Fields

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8036363698874320149L;
	private Integer purchaseId;
	private Goods goods;
	private Integer purchaseCount;
	private String purchaseAdmin;
	private Date purchaseDate;
	
	private String ids;
	
	public String getPurchaseDateDesc(){
		if (purchaseDate!=null) {
			return DateUtil.dateToDateString(purchaseDate);
		}
		return null;
	}

	// Constructors

	/** default constructor */
	public Purchase() {
	}

	/** full constructor */
	public Purchase(Goods goods, Integer purchaseCount, String purchaseAdmin,
			Date purchaseDate) {
		this.goods = goods;
		this.purchaseCount = purchaseCount;
		this.purchaseAdmin = purchaseAdmin;
		this.purchaseDate = purchaseDate;
	}

	// Property accessors

	public Integer getPurchaseId() {
		return this.purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getPurchaseCount() {
		return this.purchaseCount;
	}

	public void setPurchaseCount(Integer purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public String getPurchaseAdmin() {
		return this.purchaseAdmin;
	}

	public void setPurchaseAdmin(String purchaseAdmin) {
		this.purchaseAdmin = purchaseAdmin;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}