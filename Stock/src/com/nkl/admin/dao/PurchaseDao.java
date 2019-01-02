package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Purchase;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class PurchaseDao extends BaseDao{
	
	public void addPurchase(Purchase purchase){
		super.add(purchase);
	}
	
	public void delPurchase(Integer purchaseId){
		super.del(Purchase.class, purchaseId);
	}
	
	public void delPurchase(String[] purchaseIds){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <purchaseIds.length; i++) {
			sBuilder.append(purchaseIds[i]);
			if (i !=purchaseIds.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Purchase d WHERE d.purchaseId IN (" +sBuilder.toString()+")";

		Object[] params = null;
		
		super.executeUpdateHql(hql, params);
	}
	
	public void updatePurchase(Purchase purchase){
		Purchase _purchase = (Purchase)super.get(Purchase.class, purchase.getPurchaseId());
		if (!StringUtil.isEmptyString(purchase.getPurchaseAdmin())) {
			_purchase.setPurchaseAdmin(purchase.getPurchaseAdmin());
		}
		if (purchase.getPurchaseDate()!=null) {
			_purchase.setPurchaseDate(purchase.getPurchaseDate());
		}
		
	    //super.update(purchase);
	}
	
	@SuppressWarnings("rawtypes")
	public Purchase getPurchase(Purchase purchase){
		Purchase _purchase = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Purchase a LEFT JOIN FETCH a.goods WHERE 1=1");
		
		List<Object> paramsList = new ArrayList<Object>();
		if (purchase.getPurchaseId()!=null && purchase.getPurchaseId()!=0) {
			sBuilder.append(" and a.purchaseId = ? ");
			paramsList.add(purchase.getPurchaseId());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		List list = super.executeQueryHql(sBuilder.toString(), params);
		if (list != null && list.size() > 0) {
			_purchase = (Purchase)list.get(0);
		}
		return _purchase;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Purchase>  listPurchases(Purchase purchase){
		List<Purchase> purchases = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Purchase a LEFT JOIN FETCH a.goods WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (purchase.getPurchaseId()!=null && purchase.getPurchaseId()!=0) {
			sBuilder.append(" and a.purchaseId = ? ");
			paramsList.add(purchase.getPurchaseId());
		}
		if (purchase.getGoods()!=null && !StringUtil.isEmptyString(purchase.getGoods().getGoodsName())) {
			sBuilder.append(" and a.goods.goodsName like ? ");
			paramsList.add("%"+purchase.getGoods().getGoodsName()+"%");
		}
		if (purchase.getPurchaseDate()!=null) {
			sBuilder.append(" and a.purchaseDate = ? ");
			paramsList.add(purchase.getPurchaseDate());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		sBuilder.append(" order by a.purchaseId asc");

		List list = null;
		if (purchase.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, purchase.getStart(), purchase.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			purchases = new ArrayList<Purchase>();
			for (Object object : list) {
				purchases.add((Purchase)object);
			}
		}
		return purchases;
	}
	
	
	public int listPurchasesCount(Purchase purchase){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Purchase a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (purchase.getPurchaseId()!=null && purchase.getPurchaseId()!=0) {
			sBuilder.append(" and a.purchaseId = ? ");
			paramsList.add(purchase.getPurchaseId());
		}
		if (purchase.getGoods()!=null && !StringUtil.isEmptyString(purchase.getGoods().getGoodsName())) {
			sBuilder.append(" and a.goods.goodsName like ? ");
			paramsList.add("%"+purchase.getGoods().getGoodsName()+"%");
		}
		if (purchase.getPurchaseDate()!=null) {
			sBuilder.append(" and a.purchaseDate = ? ");
			paramsList.add(purchase.getPurchaseDate());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), params);
		sum = (int)count;
		return sum;
	}
	
	
	
	
}
