package com.nkl.admin.manager;

import java.util.Date;
import java.util.List;

import com.nkl.admin.dao.CarDao;
import com.nkl.admin.dao.GoodsDao;
import com.nkl.admin.dao.OrdersDao;
import com.nkl.admin.dao.PurchaseDao;
import com.nkl.admin.dao.UserDao;
import com.nkl.admin.domain.Car;
import com.nkl.admin.domain.Goods;
import com.nkl.admin.domain.Orders;
import com.nkl.admin.domain.Purchase;
import com.nkl.admin.domain.User;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;

public class AdminManager {
	
	//所有待注入Dao类
	CarDao carDao;
	GoodsDao goodsDao;
	OrdersDao ordersDao;
	PurchaseDao purchaseDao;
	UserDao userDao;

	/**
	 * @Title: listUsers
	 * @Description: 查询用户集合
	 * @param user
	 * @return List<User>
	 */
	public List<User> listUsers(User user, int[] sum) {
		if (sum != null) {
			sum[0] = userDao.listUsersCount(user);
		}
		List<User> users = userDao.listUsers(user);
		return users;
	}

	/**
	 * @Title: queryUser
	 * @Description: 用户单个查询
	 * @param user
	 * @return User
	 */
	public User queryUser(User user) {
		User _user = userDao.getUser(user);
		return _user;
	}

	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void addUser(User user) {
		//密码MD5加密
		if (!StringUtil.isEmptyString(user.getUserPass())) {
			user.setUserPass(Md5.makeMd5(user.getUserPass()));
		}
		//添加用户
		userDao.addUser(user);
	}

	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void updateUser(User user) {
		//密码MD5加密
		if (!StringUtil.isEmptyString(user.getUserPass())) {
			user.setUserPass(Md5.makeMd5(user.getUserPass()));
		}
		userDao.updateUser(user);
	}

	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void delUsers(User user) {
		userDao.delUser(user.getIds().split(","));
	}
	
	/**
	 * @Title: listCars
	 * @Description: 查询车辆集合
	 * @param car
	 * @return List<Car>
	 */
	public List<Car> listCars(Car car, int[] sum) {
		if (sum != null) {
			sum[0] = carDao.listCarsCount(car);
		}
		List<Car> cars = carDao.listCars(car);
		return cars;
	}

	/**
	 * @Title: queryCar
	 * @Description: 车辆单个查询
	 * @param car
	 * @return Car
	 */
	public Car queryCar(Car car) {
		Car _car = carDao.getCar(car);
		return _car;
	}

	/**
	 * @Title: addCar
	 * @Description: 添加车辆
	 * @param car
	 * @return void
	 */
	public void addCar(Car car) {
		//添加车辆
		carDao.addCar(car);
	}

	/**
	 * @Title: updateCar
	 * @Description: 更新车辆信息
	 * @param car
	 * @return void
	 */
	public void updateCar(Car car) {
		carDao.updateCar(car);
	}

	/**
	 * @Title: delCars
	 * @Description: 删除车辆信息
	 * @param car
	 * @return void
	 */
	public void delCars(Car car) {
		carDao.delCar(car.getIds().split(","));
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 查询货物集合
	 * @param goods
	 * @return List<Goods>
	 */
	public List<Goods> listGoodss(Goods goods, int[] sum) {
		if (sum != null) {
			sum[0] = goodsDao.listGoodssCount(goods);
		}
		List<Goods> goodss = goodsDao.listGoodss(goods);
		return goodss;
	}

	/**
	 * @Title: queryGoods
	 * @Description: 货物单个查询
	 * @param goods
	 * @return Goods
	 */
	public Goods queryGoods(Goods goods) {
		Goods _goods = goodsDao.getGoods(goods);
		return _goods;
	}

	/**
	 * @Title: addGoods
	 * @Description: 添加货物
	 * @param goods
	 * @return void
	 */
	public void addGoods(Goods goods) {
		//添加货物
		goodsDao.addGoods(goods);
	}

	/**
	 * @Title: updateGoods
	 * @Description: 更新货物信息
	 * @param goods
	 * @return void
	 */
	public void updateGoods(Goods goods) {
		goodsDao.updateGoods(goods);
	}
	
	/**
	 * @Title: updateGoodsCount
	 * @Description: 更新货物库存信息
	 * @param goods
	 * @return void
	 */
	public void updateGoodsCount(Goods goods) {
		goodsDao.updateGoodsCount(goods);
	}

	/**
	 * @Title: delGoodss
	 * @Description: 删除货物信息
	 * @param goods
	 * @return void
	 */
	public void delGoodss(Goods goods) {
		goodsDao.delGoods(goods.getIds().split(","));
	}
	
	/**
	 * @Title: listPurchases
	 * @Description: 查询入库集合
	 * @param purchase
	 * @return List<Purchase>
	 */
	public List<Purchase> listPurchases(Purchase purchase, int[] sum) {
		if (sum != null) {
			sum[0] = purchaseDao.listPurchasesCount(purchase);
		}
		List<Purchase> purchases = purchaseDao.listPurchases(purchase);
		return purchases;
	}

	/**
	 * @Title: addPurchase
	 * @Description: 添加入库
	 * @param purchase
	 * @return void
	 */
	public void addPurchase(Purchase purchase) {
		//添加入库
		purchaseDao.addPurchase(purchase);
		
		//更新库存数量
		Goods goods = purchase.getGoods();
		goods.setGoodsCount(purchase.getPurchaseCount());
		goodsDao.updateGoodsCount(goods);
	}

	/**
	 * @Title: delPurchases
	 * @Description: 删除入库信息
	 * @param purchase
	 * @return void
	 */
	public void delPurchases(Purchase purchase) {
		purchaseDao.delPurchase(purchase.getIds().split(","));
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 查询订单集合
	 * @param orders
	 * @return List<Orders>
	 */
	public List<Orders> listOrderss(Orders orders, int[] sum) {
		if (sum != null) {
			sum[0] = ordersDao.listOrderssCount(orders);
		}
		List<Orders> orderss = ordersDao.listOrderss(orders);
		return orderss;
	}

	/**
	 * @Title: queryOrders
	 * @Description: 订单单个查询
	 * @param orders
	 * @return Orders
	 */
	public Orders queryOrders(Orders orders) {
		Orders _orders = ordersDao.getOrders(orders);
		return _orders;
	}

	/**
	 * @Title: addOrders
	 * @Description: 添加订单
	 * @param orders
	 * @return void
	 */
	public void addOrders(Orders orders) {
		//订单编号
		orders.setOrdersNo(DateUtil.dateToDateString(new Date(),"yyyyMMddHHmmss")+orders.getUserId());
		//订单日期
		orders.setOrdersDate(DateUtil.getDate(DateUtil.getCurDateTime()));
		//订单状态1-待确认
		orders.setOrdersFlag(1);
		//添加订单
		ordersDao.addOrders(orders);
	}

	/**
	 * @Title: confirmOrders
	 * @Description: 确认订单信息
	 * @param orders
	 * @return void
	 */
	public void confirmOrders(Orders orders) {
		//确认订单信息
		orders.setOrdersFlag(2);
		ordersDao.updateOrders(orders);
		
		//更新车辆和人员状态
		Car car = new Car();
		car.setCarNumber(orders.getCarInfo().split(" ")[1]);
		car = carDao.getCar(car);
		car.setCarFlag(2);
		//carDao.updateCar(car);
		
		User user = new User();
		user.setUserId(orders.getSendId());
		user = userDao.getUser(user);
		user.setUserFlag(1);
		//userDao.updateUser(user);
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @param orders
	 * @return void
	 */
	public void sendOrders(Orders orders) {
		//确认订单信息
		orders.setOrdersFlag(3);
		ordersDao.updateOrders(orders);
		
		//更新库存数量
		orders = ordersDao.getOrders(orders);
		Goods goods = new Goods();
		goods.setGoodsNo(orders.getGoodsNo());
		goods = goodsDao.getGoods(goods);
		goods.setGoodsCount(goods.getGoodsCount() - orders.getGoodsCount());
		//goodsDao.updateGoodsCount(goods);
	}
	
	/**
	 * @Title: receiveOrders
	 * @Description: 更新订单为已收货
	 * @param orders
	 * @return void
	 */
	public void receiveOrders(Orders orders) {
		//更新订单为已收货
		orders.setOrdersFlag(4);
		ordersDao.updateOrders(orders);
		
		//更新车辆和人员状态
		orders = ordersDao.getOrders(orders);
		Car car = new Car();
		car.setCarNumber(orders.getCarInfo().split(" ")[1]);
		car = carDao.getCar(car);
		car.setCarFlag(1);
		//carDao.updateCar(car);
		
		User user = new User();
		user.setUserId(orders.getSendId());
		user = userDao.getUser(user);
		user.setUserFlag(2);
		//userDao.updateUser(user);
	}

	/**
	 * @Title: delOrderss
	 * @Description: 删除订单信息
	 * @param orders
	 * @return void
	 */
	public void delOrderss(Orders orders) {
		ordersDao.delOrders(orders.getIds().split(","));
	}

	public CarDao getCarDao() {
		return carDao;
	}

	public void setCarDao(CarDao carDao) {
		this.carDao = carDao;
	}

	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public OrdersDao getOrdersDao() {
		return ordersDao;
	}

	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	public PurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	 
	
}
