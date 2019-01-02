<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
 <!-- 左侧导航栏-->
    <div class="left-side sticky-left-side">

        <!--logo and iconic logo start-->
        <div class="logo">
            <a href="index.jsp"><img src="images/logo.png" alt=""></a>
        </div>

        <div class="logo-icon text-center">
            <a href="index.html"><img src="images/logo_icon.png" alt=""></a>
        </div>
        <!--logo and iconic logo end-->

        <div class="left-side-inner">

            <!-- visible to small devices only -->
            
            <!--sidebar nav start-->
            <ul class="nav nav-pills nav-stacked custom-nav">
                <li class="menu-list"><a href="index.html"><i class="fa fa-home"></i> <span>个人中心</span></a>
							<ul class="sub-menu-list">
								<li><a href="modifyInfo.jsp" target="_self"> 个人信息</a></li>
								<li><a href="modifyPwd.jsp"> 修改密码</a></li>
							</ul>
				</li>
				<c:if test="${admin.userType==3}">
                <li class="menu-list"><a href=""><i class="fa fa-user"></i> <span>用户管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="Admin_listUsers.action"  > 用户查询</a></li>
						<li><a href="userEdit.jsp"> 添加用户</a></li>
                        
                    </ul>
                </li>
                 </c:if> 
                 <c:if test="${admin.userType==3}"> 
                <li class="menu-list"><a href=""><i class="fa fa-male"></i> <span>派送员管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="Admin_listSenders.action"> 派送员查询</a></li>
                        <li><a href="senderEdit.jsp" > 新增派送员</a></li>
                        
                    </ul>
                </li>
                 </c:if> 
                 <c:if test="${admin.userType==3}"> 
                <li class="menu-list"><a href=""><i class="fa fa-th-list"></i> <span>货物管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="Admin_listGoodss.action"> 货物查询</a></li>
                        <li><a href="goodsEdit.jsp"> 添加货物</a></li>
                        

                    </ul>
                </li>
                </c:if>
                  <c:if test="${admin.userType==1}"> 
                <li class="menu-list"><a href=""><i class="fa fa-th-list"></i> <span>货物管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="Admin_listGoodss.action"> 货物查询</a></li>
                        
                        

                    </ul>
                </li>
                </c:if>
				<c:if test="${admin.userType==3}">
                <li class="menu-list"><a href=""><i class="fa fa-truck"></i> <span>车辆管理</span></a>
				<ul class="sub-menu-list">
                        <li><a href="Admin_listCars.action"> 车辆查询</a></li>
                        <li><a href="carEdit.jsp"> 添加车辆</a></li>
                        

                    </ul>
				</li>
				</c:if>
				<c:if test="${admin.userType==3}">
                <li class="menu-list"><a href=""><i class="fa fa-table"></i> <span>入库管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="Admin_listPurchases.action">入库查询</a></li>
                        <li><a href="purchaseEdit.jsp">添加入库</a></li>
                        
                    </ul>
                </li>
                </c:if>
				
                <c:if test="${admin.userType==1}">
                <li class="menu-list"><a href=""><i class="fa fa-shopping-cart"></i> <span>订单管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="Admin_listOrderss.action">我的订单
                	
                        
                        
                        
                        </a></li>
                        
                    </ul>
                </li>
                </c:if>
                <c:if test="${admin.userType==3}">
                <li class="menu-list"><a href=""><i class="fa fa-shopping-cart"></i> <span>订单管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="Admin_listOrderss.action">查询订单
                	
                        
                        
                        
                        </a></li>
                        
                    </ul>
                </li>
                </c:if>
                <c:if test="${admin.userType==2}">
                <li class="menu-list"><a href=""><i class="fa fa-shopping-cart"></i> <span>订单管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="Admin_listOrderss.action">配送订单
                	
                        
                        
                        
                        </a></li>
                        
                    </ul>
                </li>
                </c:if>
               
                <li class="menu-list"><a href=""><i class="fa fa-map-marker"></i> <span>网点查询</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="pointinf.jsp">网点信息</a></li>
                        <li><a href="pointlocation.jsp">网点分布</a></li>
                        
                    </ul>
                </li>
                
                
                
                

            </ul>
            <!--sidebar nav end-->

        </div>
    </div>