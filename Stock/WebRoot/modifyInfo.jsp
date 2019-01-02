<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html>
<html lang="en">
<head>
 <%@ include file="./frame/head.jsp"%>
 <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	 var user_sex = "${admin.userSex}";
	 $("#sex"+user_sex).attr('checked','checked');
	 
	 var num = /^\d+$/;
	 $("#saveBtn").bind('click',function(){
		if($("#paramsUser\\.realName").val()==''){
			alert('用户姓名不能为空');
			return;
		}
		if($("#paramsUser\\.userPhone").val()==''){
			alert('用户电话不能为空');
			return;
		}
		$("#info").submit();
	 });
	
});	 
	
</script>
</head>

<body class="sticky-header">

<section>
     <%@ include file="./frame/menu.jsp"%>

    
	<!-- 主体导航栏-->
    <div class="main-content" >

        <%@ include file="./frame/header.jsp"%>
        <!-- 内容导航栏-->
        <div class="page-heading">
            <h3>
                 修改个人信息
            </h3>
            
        </div>
		 
        <div class="wrapper">
        			<div class="row">
            			<div class="col-md-12">
                			<!--breadcrumbs start -->
                			<ul class="breadcrumb panel">
                    		<li><a href="index.jsp"><i class="fa fa-home"></i>主页</a></li>
                    		<li class="active">个人中心</li>
                    		<li><a href="modifyInfo.jsp">修改个人信息</a></li>
                    	
                			</ul>
               				<!--breadcrumbs end -->
            			</div>
        			</div>
				<div class="row">
					<div class="col-md-12">
						<section class="panel">
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-12">
										<section class="panel">
											<!-- <header class="panel-heading">
                                    DataTables hidden row details example
                                </header> -->
											<div class="panel-body">
												<div class="adv-table">
													
													<form id="info" name="info" action="Admin_saveAdmin.action" method="post">    
<input type="hidden" name="paramsUser.userId" value="${admin.userId}"/>
<table width="800" align="center" cellpadding="0" cellspacing="0"  class="table table-bordered" style="margin-top:10px;margin-bottom:10px;">
  
   
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
     	 <tr>
          <td width="15%" align="right" style="padding-right:5px">用户名：</td>
          <td width="35%">
          	<input type="text" id="paramsUser.userName" class="form-control" name="paramsUser.userName" value="${admin.userName}" disabled/>
          </td>
        </tr> 
        
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 用户姓名：</td>
          <td>
             <input type="text" id="paramsUser.realName" class="form-control" name="paramsUser.realName" value="${admin.realName}"/>
          </td>
            
          <td align="right" style="padding-right:5px"><font color="red">*</font> 用户性别：</td>
          
          <td>
        
                                
             <input type="radio" name="paramsUser.userSex" id="sex1" value="1"/>男&nbsp;&nbsp;
             
             <input type="radio" name="paramsUser.userSex" id="sex2" value="2"/>女
             
             
          </td>
          
        </tr>     
        <c:if test="${admin.userType==1}">
        <tr>
          <td width="15%" align="right" style="padding-right:5px">用户公司：</td>
          <td width="35%">
          	<input type="text" id="paramsUser.userCompany" class="form-control" name="paramsUser.userCompany" value="${admin.userCompany}"/>
          </td>
          <td width="15%" align="right" style="padding-right:5px">用户地址：</td>
          <td width="35%">
          	<input type="text" id="paramsUser.userAddress"class="form-control" name="paramsUser.userAddress" value="${admin.userAddress}"/>
          </td>
        </tr> 
        </c:if>  
        <tr>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font> 用户邮箱：</td>
          <td width="35%">
          	<input type="text" id="paramsUser.userMail"class="form-control" name="paramsUser.userMail" value="${admin.userMail}"/>
          </td>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font> 用户电话：</td>
          <td width="35%">
          	<input type="text" id="paramsUser.userPhone" class="form-control"name="paramsUser.userPhone" value="${admin.userPhone}"/>
          </td>
        </tr> 
         
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
             <input type="button" id="saveBtn" Class="btn btn-primary" value="保存"/> 
            
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
												</div>
											</div>
										</section>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
        
			
		 <%@ include file="./frame/footer.jsp"%>
        
    </div>
    <!-- main content end-->
</section>


<%@ include file="./frame/script.jsp"%>

</body>
</html>

