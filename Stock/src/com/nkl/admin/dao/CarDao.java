package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Car;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class CarDao extends BaseDao{
	
	public void addCar(Car car){
		super.add(car);
	}
	
	public void delCar(Integer carId){
		super.del(Car.class, carId);
	}
	
	public void delCar(String[] carIds){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <carIds.length; i++) {
			sBuilder.append(carIds[i]);
			if (i !=carIds.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Car d WHERE d.carId IN (" +sBuilder.toString()+")";

		Object[] params = null;
		
		super.executeUpdateHql(hql, params);
	}
	
	public void updateCar(Car car){
		Car _car = (Car)super.get(Car.class, car.getCarId());
		if (!StringUtil.isEmptyString(car.getCarModel())) {
			_car.setCarModel(car.getCarModel());
		}
		if (!StringUtil.isEmptyString(car.getCarNumber())) {
			_car.setCarNumber(car.getCarNumber());
		}
		 
		if (car.getCarFlag()!=null) {
			_car.setCarFlag(car.getCarFlag());
		}
		
	    //super.update(car);
	}
	
	@SuppressWarnings("rawtypes")
	public Car getCar(Car car){
		Car _car = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Car a WHERE 1=1");
		
		List<Object> paramsList = new ArrayList<Object>();
		if (car.getCarId()!=null && car.getCarId()!=0) {
			sBuilder.append(" and a.carId = ? ");
			paramsList.add(car.getCarId());
		}
		if (!StringUtil.isEmptyString(car.getCarNumber())) {
			sBuilder.append(" and a.carNumber = ? ");
			paramsList.add(car.getCarNumber());
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
			_car = (Car)list.get(0);
		}
		return _car;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Car>  listCars(Car car){
		List<Car> cars = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Car a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (car.getCarId()!=null && car.getCarId()!=0) {
			sBuilder.append(" and a.carId = ? ");
			paramsList.add(car.getCarId());
		}
		if (!StringUtil.isEmptyString(car.getCarModel())) {
			sBuilder.append(" and a.carModel like ? ");
			paramsList.add("%"+car.getCarModel()+"%");
		}
		if (!StringUtil.isEmptyString(car.getCarNumber())) {
			sBuilder.append(" and a.carNumber like ? ");
			paramsList.add("%"+car.getCarNumber()+"%");
		}
		if (car.getCarFlag()!=null) {
			sBuilder.append(" and a.carFlag = ? ");
			paramsList.add(car.getCarFlag());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		sBuilder.append(" order by a.carId asc");

		List list = null;
		if (car.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, car.getStart(), car.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			cars = new ArrayList<Car>();
			for (Object object : list) {
				cars.add((Car)object);
			}
		}
		return cars;
	}
	
	
	public int listCarsCount(Car car){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Car a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (car.getCarId()!=null && car.getCarId()!=0) {
			sBuilder.append(" and a.carId = ? ");
			paramsList.add(car.getCarId());
		}
		if (!StringUtil.isEmptyString(car.getCarModel())) {
			sBuilder.append(" and a.carModel like ? ");
			paramsList.add("%"+car.getCarModel()+"%");
		}
		if (!StringUtil.isEmptyString(car.getCarNumber())) {
			sBuilder.append(" and a.carNumber like ? ");
			paramsList.add("%"+car.getCarNumber()+"%");
		}
		if (car.getCarFlag()!=null) {
			sBuilder.append(" and a.carFlag = ? ");
			paramsList.add(car.getCarFlag());
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
