package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Orders;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class OrdersDao extends BaseDao{
	
	public void addOrders(Orders orders){
		super.add(orders);
	}
	
	public void delOrders(Integer ordersId){
		super.del(Orders.class, ordersId);
	}
	
	public void delOrders(String[] ordersIds){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <ordersIds.length; i++) {
			sBuilder.append(ordersIds[i]);
			if (i !=ordersIds.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Orders d WHERE d.ordersId IN (" +sBuilder.toString()+")";

		Object[] params = null;
		
		super.executeUpdateHql(hql, params);
	}
	
	public void updateOrders(Orders orders){
		Orders _orders = (Orders)super.get(Orders.class, orders.getOrdersId());
		if (!StringUtil.isEmptyString(orders.getCarInfo())) {
			_orders.setCarInfo(orders.getCarInfo());
		}
		if (orders.getSendId()!=null) {
			_orders.setSendId(orders.getSendId());
		}
		if (!StringUtil.isEmptyString(orders.getSendName())) {
			_orders.setSendName(orders.getSendName());
		}
		if (orders.getOrdersFlag()!=null) {
			_orders.setOrdersFlag(orders.getOrdersFlag());
		}
		
	    //super.update(orders);
	}
	
	@SuppressWarnings("rawtypes")
	public Orders getOrders(Orders orders){
		Orders _orders = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Orders a WHERE 1=1");
		
		List<Object> paramsList = new ArrayList<Object>();
		if (orders.getOrdersId()!=null && orders.getOrdersId()!=0) {
			sBuilder.append(" and a.ordersId = ? ");
			paramsList.add(orders.getOrdersId());
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
			_orders = (Orders)list.get(0);
		}
		return _orders;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Orders>  listOrderss(Orders orders){
		List<Orders> orderss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Orders a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (orders.getOrdersId()!=null && orders.getOrdersId()!=0) {
			sBuilder.append(" and a.ordersId = ? ");
			paramsList.add(orders.getOrdersId());
		}
		if (!StringUtil.isEmptyString(orders.getOrdersNo())) {
			sBuilder.append(" and a.ordersNo like ? ");
			paramsList.add("%"+orders.getOrdersNo()+"%");
		}
		if (orders.getUserId()!=null) {
			sBuilder.append(" and a.userId = ? ");
			paramsList.add(orders.getUserId());
		}
		if (!StringUtil.isEmptyString(orders.getRealName())) {
			sBuilder.append(" and a.realName like ? ");
			paramsList.add("%"+orders.getRealName()+"%");
		}
		if (!StringUtil.isEmptyString(orders.getGoodsNo())) {
			sBuilder.append(" and a.goodsNo like ? ");
			paramsList.add("%"+orders.getGoodsNo()+"%");
		}
		if (!StringUtil.isEmptyString(orders.getGoodsName())) {
			sBuilder.append(" and a.goodsName like ? ");
			paramsList.add("%"+orders.getGoodsName()+"%");
		}
		if (orders.getOrdersDate()!=null) {
			sBuilder.append(" and a.ordersDate = ? ");
			paramsList.add(orders.getOrdersDate());
		}
		if (!StringUtil.isEmptyString(orders.getCarInfo())) {
			sBuilder.append(" and a.carInfo like ? ");
			paramsList.add("%"+orders.getCarInfo()+"%");
		}
		if (orders.getSendId()!=null) {
			sBuilder.append(" and a.sendId = ? ");
			paramsList.add(orders.getSendId());
		}
		if (!StringUtil.isEmptyString(orders.getSendName())) {
			sBuilder.append(" and a.sendName like ? ");
			paramsList.add("%"+orders.getSendName()+"%");
		}
		if (orders.getOrdersFlag()!=null) {
			sBuilder.append(" and a.ordersFlag = ? ");
			paramsList.add(orders.getOrdersFlag());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		sBuilder.append(" order by a.ordersId asc");

		List list = null;
		if (orders.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, orders.getStart(), orders.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			orderss = new ArrayList<Orders>();
			for (Object object : list) {
				orderss.add((Orders)object);
			}
		}
		return orderss;
	}
	
	
	public int listOrderssCount(Orders orders){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Orders a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (orders.getOrdersId()!=null && orders.getOrdersId()!=0) {
			sBuilder.append(" and a.ordersId = ? ");
			paramsList.add(orders.getOrdersId());
		}
		if (!StringUtil.isEmptyString(orders.getOrdersNo())) {
			sBuilder.append(" and a.ordersNo like ? ");
			paramsList.add("%"+orders.getOrdersNo()+"%");
		}
		if (orders.getUserId()!=null) {
			sBuilder.append(" and a.userId = ? ");
			paramsList.add(orders.getUserId());
		}
		if (!StringUtil.isEmptyString(orders.getRealName())) {
			sBuilder.append(" and a.realName like ? ");
			paramsList.add("%"+orders.getRealName()+"%");
		}
		if (!StringUtil.isEmptyString(orders.getGoodsNo())) {
			sBuilder.append(" and a.goodsNo like ? ");
			paramsList.add("%"+orders.getGoodsNo()+"%");
		}
		if (!StringUtil.isEmptyString(orders.getGoodsName())) {
			sBuilder.append(" and a.goodsName like ? ");
			paramsList.add("%"+orders.getGoodsName()+"%");
		}
		if (orders.getOrdersDate()!=null) {
			sBuilder.append(" and a.ordersDate = ? ");
			paramsList.add(orders.getOrdersDate());
		}
		if (!StringUtil.isEmptyString(orders.getCarInfo())) {
			sBuilder.append(" and a.carInfo like ? ");
			paramsList.add("%"+orders.getCarInfo()+"%");
		}
		if (orders.getSendId()!=null) {
			sBuilder.append(" and a.sendId = ? ");
			paramsList.add(orders.getSendId());
		}
		if (!StringUtil.isEmptyString(orders.getSendName())) {
			sBuilder.append(" and a.sendName like ? ");
			paramsList.add("%"+orders.getSendName()+"%");
		}
		if (orders.getOrdersFlag()!=null) {
			sBuilder.append(" and a.ordersFlag = ? ");
			paramsList.add(orders.getOrdersFlag());
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
