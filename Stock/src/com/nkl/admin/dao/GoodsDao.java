package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Goods;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class GoodsDao extends BaseDao{
	
	public void addGoods(Goods goods){
		super.add(goods);
	}
	
	public void delGoods(Integer goodsId){
		super.del(Goods.class, goodsId);
	}
	
	public void delGoods(String[] goodsIds){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <goodsIds.length; i++) {
			sBuilder.append(goodsIds[i]);
			if (i !=goodsIds.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Goods d WHERE d.goodsId IN (" +sBuilder.toString()+")";

		Object[] params = null;
		
		super.executeUpdateHql(hql, params);
	}
	
	public void updateGoods(Goods goods){
		Goods _goods = (Goods)super.get(Goods.class, goods.getGoodsId());
		if (!StringUtil.isEmptyString(goods.getGoodsName())) {
			_goods.setGoodsName(goods.getGoodsName());
		}
		if (goods.getGoodsCount()!=null) {
			_goods.setGoodsCount(goods.getGoodsCount());
		}
		if (!StringUtil.isEmptyString(goods.getGoodsDesc())) {
			_goods.setGoodsDesc(goods.getGoodsDesc());
		}
		
	    //super.update(goods);
	}
	
	public void updateGoodsCount(Goods goods){
		Goods _goods = (Goods)super.get(Goods.class, goods.getGoodsId());
		if (goods.getGoodsCount()!=null) {
			_goods.setGoodsCount(_goods.getGoodsCount() + goods.getGoodsCount());
		}
	    //super.update(goods);
	}
	
	@SuppressWarnings("rawtypes")
	public Goods getGoods(Goods goods){
		Goods _goods = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Goods a WHERE 1=1");
		
		List<Object> paramsList = new ArrayList<Object>();
		if (goods.getGoodsId()!=null && goods.getGoodsId()!=0) {
			sBuilder.append(" and a.goodsId = ? ");
			paramsList.add(goods.getGoodsId());
		}
		if (!StringUtil.isEmptyString(goods.getGoodsNo())) {
			sBuilder.append(" and a.goodsNo = ? ");
			paramsList.add(goods.getGoodsNo());
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
			_goods = (Goods)list.get(0);
		}
		return _goods;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Goods>  listGoodss(Goods goods){
		List<Goods> goodss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Goods a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (goods.getGoodsId()!=null && goods.getGoodsId()!=0) {
			sBuilder.append(" and a.goodsId = ? ");
			paramsList.add(goods.getGoodsId());
		}
		if (!StringUtil.isEmptyString(goods.getGoodsNo())) {
			sBuilder.append(" and a.goodsNo like ? ");
			paramsList.add("%"+goods.getGoodsNo()+"%");
		}
		if (!StringUtil.isEmptyString(goods.getGoodsName())) {
			sBuilder.append(" and a.goodsName like ? ");
			paramsList.add("%"+goods.getGoodsName()+"%");
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		sBuilder.append(" order by a.goodsId asc");

		List list = null;
		if (goods.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, goods.getStart(), goods.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			goodss = new ArrayList<Goods>();
			for (Object object : list) {
				goodss.add((Goods)object);
			}
		}
		return goodss;
	}
	
	
	public int listGoodssCount(Goods goods){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Goods a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (goods.getGoodsId()!=null && goods.getGoodsId()!=0) {
			sBuilder.append(" and a.goodsId = ? ");
			paramsList.add(goods.getGoodsId());
		}
		if (!StringUtil.isEmptyString(goods.getGoodsNo())) {
			sBuilder.append(" and a.goodsNo like ? ");
			paramsList.add("%"+goods.getGoodsNo()+"%");
		}
		if (!StringUtil.isEmptyString(goods.getGoodsName())) {
			sBuilder.append(" and a.goodsName like ? ");
			paramsList.add("%"+goods.getGoodsName()+"%");
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
