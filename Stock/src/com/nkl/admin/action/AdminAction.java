package com.nkl.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nkl.admin.domain.Car;
import com.nkl.admin.domain.Goods;
import com.nkl.admin.domain.Orders;
import com.nkl.admin.domain.Purchase;
import com.nkl.admin.domain.User;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.BeanLocator;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Param;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = (AdminManager)BeanLocator.getInstance().getBean("adminManager");
	
	//抓取页面参数
	User paramsUser;
	Car paramsCar;
	Goods paramsGoods;
	Orders paramsOrders;
	Purchase paramsPurchase;
	
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUserId(paramsUser.getUserId());
			admin = adminManager.queryUser(admin);
			Param.setSession("admin", admin);
			
			setSuccessTip("保存成功", "modifyInfo.jsp");
			
		} catch (Exception e) {
			setErrorTip("保存异常", "modifyInfo.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUserPass(paramsUser.getUserPass());
				Param.setSession("admin", admin);
			}
			
			setSuccessTip("修改成功", "modifyPwd.jsp");
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		
		return "infoTip";
	}
	
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查看用户信息
			paramsUser.setUserType(1);
			
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询用户列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	public String addUser(){
		try {
			//检查用户是否存在
			User user = new User();
			user.setUserName(paramsUser.getUserName());
			user = adminManager.queryUser(user);
			if (user!=null) {
				tip="失败，该用户名已经存在！";
				Param.setAttribute("user", paramsUser);
				return "userEdit";
			}
			
			 //添加用户
			paramsUser.setUserType(1);
			paramsUser.setRegDate(DateUtil.getDate(DateUtil.getCurDateTime()));
			paramsUser.setUserFlag(0);
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("保存成功", "Admin_listUsers.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除用户成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listSenders
	 * @Description: 查询配送人员
	 * @return String
	 */
	public String listSenders(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			paramsUser.setUserType(2);
			
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询配送人员列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询配送人员异常", "main.jsp");
			return "infoTip";
		}
		
		return "senderShow";
	}
	
	/**
	 * @Title: addSenderShow
	 * @Description: 显示添加配送人员页面
	 * @return String
	 */
	public String addSenderShow(){
		return "senderEdit";
	}
	
	/**
	 * @Title: addSender
	 * @Description: 添加配送人员
	 * @return String
	 */
	public String addSender(){
		try {
			//检查登录名是否存在
			User user = new User();
			user.setUserName(paramsUser.getUserName());
			user = adminManager.queryUser(user);
			if (user!=null) {
				tip="失败，该用户名已经存在！";
				Param.setAttribute("user", paramsUser);
				return "senderEdit";
			}
			 //添加配送人员
			paramsUser.setUserType(2);
			paramsUser.setUserPass("111111");//因为密码不能为空，这里随便写一个密码
			paramsUser.setRegDate(DateUtil.getDate(DateUtil.getCurDateTime()));
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listSenders.action");
		} catch (Exception e) {
			setErrorTip("添加配送人员异常", "Admin_listSenders.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editSender
	 * @Description: 编辑配送人员
	 * @return String
	 */
	public String editSender(){
		try {
			 //得到配送人员
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询配送人员异常", "Admin_listSenders.action");
			return "infoTip";
		}
		
		return "senderEdit";
	}
	
	/**
	 * @Title: saveSender
	 * @Description: 保存编辑配送人员
	 * @return String
	 */
	public String saveSender(){
		try {
			 //保存编辑配送人员
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("保存成功", "Admin_listSenders.action");
		} catch (Exception e) {
			tip="保存失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delSenders
	 * @Description: 删除配送人员
	 * @return String
	 */
	public String delSenders(){
		try {
			 //删除配送人员
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除配送人员成功", "Admin_listSenders.action");
		} catch (Exception e) {
			setErrorTip("删除配送人员异常", "Admin_listSenders.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listCars
	 * @Description: 查询车辆
	 * @return String
	 */
	public String listCars(){
		try {
			if (paramsCar==null) {
				paramsCar = new Car();
			}
			//设置分页信息
			setPagination(paramsCar);
			//总的条数
			int[] sum={0};
			//查询车辆列表
			List<Car> cars = adminManager.listCars(paramsCar,sum); 
			
			Param.setAttribute("cars", cars);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询车辆异常", "main.jsp");
			return "infoTip";
		}
		
		return "carShow";
	}
	
	/**
	 * @Title: addCarShow
	 * @Description: 显示添加车辆页面
	 * @return String
	 */
	public String addCarShow(){
		return "carEdit";
	}
	
	/**
	 * @Title: addCar
	 * @Description: 添加车辆
	 * @return String
	 */
	public String addCar(){
		try {
			//检查登录名是否存在
			Car car = new Car();
			car.setCarNumber(paramsCar.getCarNumber());
			car = adminManager.queryCar(car);
			if (car!=null) {
				tip="失败，该车牌号已经存在！";
				Param.setAttribute("car", paramsCar);
				return "carEdit";
			}
			 //添加车辆
			adminManager.addCar(paramsCar);
			
			setSuccessTip("添加成功", "Admin_listCars.action");
		} catch (Exception e) {
			setErrorTip("添加车辆异常", "Admin_listCars.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editCar
	 * @Description: 编辑车辆
	 * @return String
	 */
	public String editCar(){
		try {
			 //得到车辆
			Car car = adminManager.queryCar(paramsCar);
			Param.setAttribute("car", car);
			
		} catch (Exception e) {
			setErrorTip("查询车辆异常", "Admin_listCars.action");
			return "infoTip";
		}
		
		return "carEdit";
	}
	
	/**
	 * @Title: saveCar
	 * @Description: 保存编辑车辆
	 * @return String
	 */
	public String saveCar(){
		try {
			 //保存编辑车辆
			adminManager.updateCar(paramsCar);
			
			setSuccessTip("保存成功", "Admin_listCars.action");
		} catch (Exception e) {
			tip="保存失败";
			Param.setAttribute("car", paramsCar);
			return "carEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delCars
	 * @Description: 删除车辆
	 * @return String
	 */
	public String delCars(){
		try {
			 //删除车辆
			adminManager.delCars(paramsCar);
			
			setSuccessTip("删除车辆成功", "Admin_listCars.action");
		} catch (Exception e) {
			setErrorTip("删除车辆异常", "Admin_listCars.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: queryGoods
	 * @Description: 查询货物
	 * @return String
	 */
	public String queryGoods(){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			paramsGoods.setStart(-1);
			//查询货物列表
			List<Goods> goodss = adminManager.listGoodss(paramsGoods,null); 
			
			setResult("goodss", goodss);
			
		} catch (Exception e) {
			setErrorReason("查询货物信息失败，服务器异常！",e);
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 查询货物
	 * @return String
	 */
	public String listGoodss(){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//设置分页信息
			setPagination(paramsGoods);
			//总的条数
			int[] sum={0};
			//查询货物列表
			List<Goods> goodss = adminManager.listGoodss(paramsGoods,sum); 
			
			Param.setAttribute("goodss", goodss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询货物异常", "main.jsp");
			return "infoTip";
		}
		
		return "goodsShow";
	}
	
	/**
	 * @Title: addGoodsShow
	 * @Description: 显示添加货物页面
	 * @return String
	 */
	public String addGoodsShow(){
		return "goodsEdit";
	}
	
	/**
	 * @Title: addGoods
	 * @Description: 添加货物
	 * @return String
	 */
	public String addGoods(){
		try {
			//检查登录名是否存在
			Goods goods = new Goods();
			goods.setGoodsNo(paramsGoods.getGoodsNo());
			goods = adminManager.queryGoods(goods);
			if (goods!=null) {
				tip="失败，该货物编号已经存在！";
				Param.setAttribute("goods", paramsGoods);
				return "goodsEdit";
			}
			 //添加货物
			adminManager.addGoods(paramsGoods);
			
			setSuccessTip("添加成功", "Admin_listGoodss.action");
		} catch (Exception e) {
			setErrorTip("添加货物异常", "Admin_listGoodss.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editGoods
	 * @Description: 编辑货物
	 * @return String
	 */
	public String editGoods(){
		try {
			 //得到货物
			Goods goods = adminManager.queryGoods(paramsGoods);
			Param.setAttribute("goods", goods);
			
		} catch (Exception e) {
			setErrorTip("查询货物异常", "Admin_listGoodss.action");
			return "infoTip";
		}
		
		return "goodsEdit";
	}
	
	/**
	 * @Title: saveGoods
	 * @Description: 保存编辑货物
	 * @return String
	 */
	public String saveGoods(){
		try {
			 //保存编辑货物
			adminManager.updateGoods(paramsGoods);
			
			setSuccessTip("保存成功", "Admin_listGoodss.action");
		} catch (Exception e) {
			tip="保存失败";
			Param.setAttribute("goods", paramsGoods);
			return "goodsEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delGoodss
	 * @Description: 删除货物
	 * @return String
	 */
	public String delGoodss(){
		try {
			 //删除货物
			adminManager.delGoodss(paramsGoods);
			
			setSuccessTip("删除货物成功", "Admin_listGoodss.action");
		} catch (Exception e) {
			setErrorTip("删除货物异常", "Admin_listGoodss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listPurchases
	 * @Description: 查询入库
	 * @return String
	 */
	public String listPurchases(){
		try {
			if (paramsPurchase==null) {
				paramsPurchase = new Purchase();
			}
			//设置分页信息
			setPagination(paramsPurchase);
			//总的条数
			int[] sum={0};
			//查询入库列表
			List<Purchase> purchases = adminManager.listPurchases(paramsPurchase,sum); 
			
			Param.setAttribute("purchases", purchases);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询入库异常", "main.jsp");
			return "infoTip";
		}
		
		return "purchaseShow";
	}
	
	/**
	 * @Title: addPurchaseShow
	 * @Description: 显示添加入库页面
	 * @return String
	 */
	public String addPurchaseShow(){
		//查询货物信息
		Goods goods = new Goods();
		goods.setStart(-1);
		List<Goods> goodss = adminManager.listGoodss(goods, null);
		if (goodss!=null) {
			Param.setAttribute("goodss", goodss);
		}else {
			Param.setAttribute("goodss", new ArrayList<Goods>());
		}
		
		return "purchaseEdit";
	}
	
	/**
	 * @Title: addPurchase
	 * @Description: 添加入库
	 * @return String
	 */
	public String addPurchase(){
		try {
			 //添加入库
			adminManager.addPurchase(paramsPurchase);
			
			setSuccessTip("添加成功", "Admin_listPurchases.action");
		} catch (Exception e) {
			setErrorTip("添加入库异常", "Admin_listPurchases.action");
		}
		
		return "infoTip";
	}
	 
	/**
	 * @Title: delPurchases
	 * @Description: 删除入库
	 * @return String
	 */
	public String delPurchases(){
		try {
			 //删除入库
			adminManager.delPurchases(paramsPurchase);
			
			setSuccessTip("删除入库成功", "Admin_listPurchases.action");
		} catch (Exception e) {
			setErrorTip("删除入库异常", "Admin_listPurchases.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 查询订单
	 * @return String
	 */
	public String listOrderss(){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//设置分页信息
			setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//用户身份认证
			User admin = (User)Param.getSession("admin");
			if (admin.getUserType()==1) {
				paramsOrders.setUserId(admin.getUserId());
			}else if (admin.getUserType()==2) {
				paramsOrders.setSendId(admin.getUserId());
			}
			//查询订单列表
			List<Orders> orderss = adminManager.listOrderss(paramsOrders,sum); 
			
			Param.setAttribute("orderss", orderss);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询订单异常", "main.jsp");
			return "infoTip";
		}
		
		return "ordersShow";
	}
	
	/**
	 * @Title: addOrdersShow
	 * @Description: 显示添加订单页面
	 * @return String
	 */
	public String addOrdersShow(){
		//查询货物信息
		Goods goods = new Goods();
		goods.setStart(-1);
		List<Goods> goodss = adminManager.listGoodss(goods, null);
		if (goodss!=null) {
			Param.setAttribute("goodss", goodss);
		}else {
			Param.setAttribute("goodss", new ArrayList<Goods>());
		}
		
		return "ordersEdit";
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 添加订单
	 * @return String
	 */
	public String addOrders(){
		try {
			 //添加订单
			adminManager.addOrders(paramsOrders);
			
			setSuccessTip("下单成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("下单失败了。。。", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: confirmOrdersShow
	 * @Description: 确认订单界面
	 * @return String
	 */
	public String confirmOrdersShow(){
		try {
			 //得到订单
			Orders orders = adminManager.queryOrders(paramsOrders);
			Param.setAttribute("orders", orders);
			
			//查询配送人员
			User user = new User();
			user.setUserType(2);
			user.setUserFlag(2);
			user.setStart(-1);
			List<User> senders = adminManager.listUsers(user, null);
			if (senders==null) {
				senders = new ArrayList<User>();
			}
			Param.setAttribute("senders", senders);
			
			//查询车辆信息
			Car car = new Car();
			car.setCarFlag(1);
			car.setStart(-1);
			List<Car> cars = adminManager.listCars(car, null);
			if (cars==null) {
				cars = new ArrayList<Car>();
			}
			Param.setAttribute("cars", cars);
			
		} catch (Exception e) {
			setErrorTip("查询订单异常", "Admin_listOrderss.action");
			return "infoTip";
		}
		
		return "ordersConfirmEdit";
	}
	
	/**
	 * @Title: confirmOrders
	 * @Description: 确认订单
	 * @return String
	 */
	public String confirmOrders(){
		try {
			 //保存确认订单
			adminManager.confirmOrders(paramsOrders);
			
			setSuccessTip("确认订单成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("确认订单失败", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @return String
	 */
	public String sendOrders(){
		try {
			 //发货前检查库存数量是否充足
			 Orders orders = adminManager.queryOrders(paramsOrders);
			 Goods goods = new Goods();
			 goods.setGoodsNo(orders.getGoodsNo());
			 goods = adminManager.queryGoods(goods);
			 if (orders.getGoodsCount()>goods.getGoodsCount()) {
				 setErrorTip("发货失败,库存数量不足", "Admin_listOrderss.action");
				 return "infoTip";
			 }
			
			 //订单发货
			adminManager.sendOrders(paramsOrders);
			
			setSuccessTip("订单发货成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("订单发货失败", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: receiveOrders
	 * @Description: 订单收货
	 * @return String
	 */
	public String receiveOrders(){
		try {
			 //订单收货
			adminManager.receiveOrders(paramsOrders);
			
			setSuccessTip("订单收货成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("订单收货失败", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delOrderss
	 * @Description: 删除订单
	 * @return String
	 */
	public String delOrderss(){
		try {
			 //删除订单
			adminManager.delOrderss(paramsOrders);
			
			setSuccessTip("订单"+paramsOrders.getDelTypeDesc()+"成功", "Admin_listOrderss.action");
		} catch (Exception e) {
			setErrorTip("订单"+paramsOrders.getDelTypeDesc()+"异常", "Admin_listOrderss.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: validateAdmin
	 * @Description: admin登录验证
	 * @return boolean
	 */
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Car getParamsCar() {
		return paramsCar;
	}

	public void setParamsCar(Car paramsCar) {
		this.paramsCar = paramsCar;
	}

	public Goods getParamsGoods() {
		return paramsGoods;
	}

	public void setParamsGoods(Goods paramsGoods) {
		this.paramsGoods = paramsGoods;
	}

	public Orders getParamsOrders() {
		return paramsOrders;
	}

	public void setParamsOrders(Orders paramsOrders) {
		this.paramsOrders = paramsOrders;
	}

	public Purchase getParamsPurchase() {
		return paramsPurchase;
	}

	public void setParamsPurchase(Purchase paramsPurchase) {
		this.paramsPurchase = paramsPurchase;
	}
	 
	
}
