package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class UserDao extends BaseDao{
	
	public void addUser(User user){
		super.add(user);
	}
	
	public void delUser(Integer userId){
		super.del(User.class, userId);
	}
	
	public void delUser(String[] userIds){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <userIds.length; i++) {
			sBuilder.append(userIds[i]);
			if (i !=userIds.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM User d WHERE d.userId IN (" +sBuilder.toString()+")";

		Object[] params = null;
		
		super.executeUpdateHql(hql, params);
	}
	
	public void updateUser(User user){
		User _user = (User)super.get(User.class, user.getUserId());
		if (!StringUtil.isEmptyString(user.getUserName())) {
			_user.setUserName(user.getUserName());
		}
		if (!StringUtil.isEmptyString(user.getUserPass())) {
			_user.setUserPass(user.getUserPass());
		}
		if (!StringUtil.isEmptyString(user.getRealName())) {
			_user.setRealName(user.getRealName());
		}
		if (user.getUserSex()!=null) {
			_user.setUserSex(user.getUserSex());
		}
		if (!StringUtil.isEmptyString(user.getUserCompany())) {
			_user.setUserCompany(user.getUserCompany());
		}
		if (!StringUtil.isEmptyString(user.getRealName())) {
			_user.setRealName(user.getRealName());
		}
		if (!StringUtil.isEmptyString(user.getUserAddress())) {
			_user.setUserAddress(user.getUserAddress());
		}
		if (!StringUtil.isEmptyString(user.getUserMail())) {
			_user.setUserMail(user.getUserMail());
		}
		if (!StringUtil.isEmptyString(user.getUserPhone())) {
			_user.setUserPhone(user.getUserPhone());
		}
		if (user.getUserFlag()!=null) {
			_user.setUserFlag(user.getUserFlag());
		}
		
	    //super.update(user);
	}
	
	@SuppressWarnings("rawtypes")
	public User getUser(User user){
		User _user = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM User a WHERE 1=1");
		
		List<Object> paramsList = new ArrayList<Object>();
		if (user.getUserId()!=null && user.getUserId()!=0) {
			sBuilder.append(" and a.userId = ? ");
			paramsList.add(user.getUserId());
		}
		if (!StringUtil.isEmptyString(user.getUserName())) {
			sBuilder.append(" and a.userName = ? ");
			paramsList.add(user.getUserName());
		}
		if (!StringUtil.isEmptyString(user.getUserPass())) {
			sBuilder.append(" and a.userPass = ? ");
			paramsList.add(user.getUserPass());
		}
		if (!StringUtil.isEmptyString(user.getUserTypes())) {
			sBuilder.append(" and a.userType in ("+user.getUserTypes()+") ");
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
			_user = (User)list.get(0);
		}
		return _user;
	}
	
	@SuppressWarnings("rawtypes")
	public List<User>  listUsers(User user){
		List<User> users = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM User a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (user.getUserId()!=null && user.getUserId()!=0) {
			sBuilder.append(" and a.userId = ? ");
			paramsList.add(user.getUserId());
		}
		if (!StringUtil.isEmptyString(user.getUserName())) {
			sBuilder.append(" and a.userName like ? ");
			paramsList.add("%"+user.getUserName()+"%");
		}
		if (!StringUtil.isEmptyString(user.getRealName())) {
			sBuilder.append(" and a.realName like ? ");
			paramsList.add("%"+user.getRealName()+"%");
		}
		if (user.getUserSex()!=null) {
			sBuilder.append(" and a.userSex = ? ");
			paramsList.add(user.getUserSex());
		}
		if (user.getUserFlag()!=null) {
			sBuilder.append(" and a.userFlag = ? ");
			paramsList.add(user.getUserFlag());
		}
		if (user.getUserType()!=null) {
			sBuilder.append(" and a.userType = ? ");
			paramsList.add(user.getUserType());
		}
		if (!StringUtil.isEmptyString(user.getUserTypes())) {
			sBuilder.append(" and a.userType in ("+user.getUserTypes()+") ");
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		sBuilder.append(" order by a.userId asc");

		List list = null;
		if (user.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, user.getStart(), user.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			users = new ArrayList<User>();
			for (Object object : list) {
				users.add((User)object);
			}
		}
		return users;
	}
	
	
	public int listUsersCount(User user){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM User a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (user.getUserId()!=null && user.getUserId()!=0) {
			sBuilder.append(" and a.userId = ? ");
			paramsList.add(user.getUserId());
		}
		if (!StringUtil.isEmptyString(user.getUserName())) {
			sBuilder.append(" and a.userName like ? ");
			paramsList.add("%"+user.getUserName()+"%");
		}
		if (!StringUtil.isEmptyString(user.getRealName())) {
			sBuilder.append(" and a.realName like ? ");
			paramsList.add("%"+user.getRealName()+"%");
		}
		if (user.getUserSex()!=null) {
			sBuilder.append(" and a.userSex = ? ");
			paramsList.add(user.getUserSex());
		}
		if (user.getUserFlag()!=null) {
			sBuilder.append(" and a.userFlag = ? ");
			paramsList.add(user.getUserFlag());
		}
		if (user.getUserType()!=null) {
			sBuilder.append(" and a.userType = ? ");
			paramsList.add(user.getUserType());
		}
		if (!StringUtil.isEmptyString(user.getUserTypes())) {
			sBuilder.append(" and a.userType in ("+user.getUserTypes()+") ");
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
