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
	 
	 $("#saveBtn").bind('click',function(){
		if($("#paramsUser\\.userPass").val()=='' || $("#user_rpass").val()==''){
			alert('新密码和确认密码不能为空');
			return;
		}
		if($("#paramsUser\\.userPass").val() != $("#user_rpass").val()){
			alert('两次输入密码不一致');
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
                 修改密码
            </h3>
            
        </div>
		 
        <div class="wrapper">
        			<div class="row">
            			<div class="col-md-12">
                			<!--breadcrumbs start -->
                			<ul class="breadcrumb panel">
                    		<li><a href="index.jsp"><i class="fa fa-home"></i>主页</a></li>
                    		<li class="active">个人中心</li>
                    		<li><a href="modifyPwd.jsp">修改密码</a></li>
                    	
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
<form id="info" name="info" action="Admin_saveAdminPass.action" method="post">    
<input type="hidden" name="paramsUser.userId" value="${admin.userId}"/>
<table width="800" align="center" cellpadding="0" class="table table-bordered" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
 
   
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 输入新密码：</td>
          <td>
            <input type="password" id="paramsUser.userPass"class="form-control" name="paramsUser.userPass"  />
          </td>
          <td align="right" style="padding-right:5px"></td>
          <td>
             
          </td> 
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 确认新密码：</td>
          <td>
            <input type="password" id="user_rpass" class="form-control"name="user_rpass"  />
          </td>
          <td align="right" style="padding-right:5px"></td>
          <td>
             
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
            <input type="button" id="saveBtn" Class="btn btn-primary" value="修 改"/> 
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

