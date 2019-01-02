package com.nkl.admin.domain;

import java.util.Date;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User extends BaseDomain {

	// Fields
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -9170993521237939978L;
	private Integer userId;
	private String userName;
	private String userPass;
	private String realName;
	private Integer userSex;
	private String userCompany;
	private String userAddress;
	private String userMail;
	private String userPhone;
	private Integer userFlag;//1：配送中 2：无任务
	private Date regDate;
	private Integer userType;//1：公司用户 2：配送人员 3：系统管理员 

	private String userTypes; 
	private String ids;
	private String random;
	
	public String getUserFlagDesc(){
		switch (userFlag) {
		case 1:
			return "配送中";
		case 2:
			return "无任务";
		default:
			return "";
		}
	}
	public String getUserTypeDesc() {
		switch (userType) {
		case 1:
			return "公司用户";
		case 2:
			return "配送人员";
		case 3:
			return "系统管理员";
		default:
			return "";
		}
	}
	
	public String getUserSexDesc(){
		switch (userSex) {
		case 1:
			return "男";
		case 2:
			return "女";
		default:
			return "";
		}
	}
	
	public String getRegDateDesc(){
		if (regDate!=null) {
			return DateUtil.dateToDateString(regDate);
		}
		return null;
	}
	
	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userName, String userPass) {
		this.userName = userName;
		this.userPass = userPass;
	}

	/** full constructor */
	public User(String userName, String userPass, String realName,
			Integer userSex, String userCompany, String userAddress,
			String userMail, String userPhone, Integer userFlag,
			Date regDate, Integer userType) {
		this.userName = userName;
		this.userPass = userPass;
		this.realName = realName;
		this.userSex = userSex;
		this.userCompany = userCompany;
		this.userAddress = userAddress;
		this.userMail = userMail;
		this.userPhone = userPhone;
		this.userFlag = userFlag;
		this.regDate = regDate;
		this.userType = userType;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUserSex() {
		return this.userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getUserCompany() {
		return this.userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserMail() {
		return this.userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getUserFlag() {
		return this.userFlag;
	}

	public void setUserFlag(Integer userFlag) {
		this.userFlag = userFlag;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getUserTypes() {
		return userTypes;
	}
	public void setUserTypes(String userTypes) {
		this.userTypes = userTypes;
	}

}