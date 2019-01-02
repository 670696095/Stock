<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<!DOCTYPE html>
<html lang="en">
<head>
 <%@ include file="./frame/head.jsp"%>
<title><s:if test="#attr.user!=null && #attr.user.userId!=0">编辑</s:if><s:else>添加</s:else>用户信息</title>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var userSex = "${userSex}";

	 if(userSex!=''){
		 $("#sex"+userSex).attr('checked','checked');
	 }else{
		 $("#sex1").attr('checked','checked');
	 }
	 
	 var num = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsUser\\.userName").val()==''){
			alert('用户名为空');
			return;
		}
		if($("#paramsUser\\.userPass").val()==''){
			alert('密码不能为空');
			return;
		}
		if($("#paramsUser\\.realName").val()==''){
			alert('用户姓名不能为空');
			return;
		}
		if($("#paramsUser\\.userPhone").val()==''){
			alert('用户电话不能为空');
			return;
		}
		$("#paramsUser\\.userId").val(0);
		$("#info").attr('action','Admin_addUser.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		if($("#paramsUser\\.userName").val()==''){
			alert('用户名为空');
			return;
		}
		if($("#paramsUser\\.realName").val()==''){
			alert('用户姓名不能为空');
			return;
		}
		if($("#paramsUser\\.userPhone").val()==''){
			alert('用户电话不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveUser.action').submit();
			 
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
                 添加用户
            </h3>
            
        </div>
		 
        <div class="wrapper">
        			 <div class="row">
            			<div class="col-md-12">
                			<!--breadcrumbs start -->
                			<ul class="breadcrumb panel">
                    		<li><a href="index.jsp"><i class="fa fa-home"></i>主页</a></li>
                    		<li class="active">用户管理</li>
                    		<li><a href="userEdit.jsp">添加用户</a></li>
                    		
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
													<form id="info" name="info" action="Admin_addUser.action" method="post">   
<s:hidden id="paramsUser.userId" name="paramsUser.userId" value="%{#attr.user.userId}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" class="table table-bordered"style="margin-top:10px;margin-bottom:10px;">
   <!--  <tr> 
      <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
       <!-- 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.user!=null && #attr.user.userId!=0">编辑</s:if><s:else>添加</s:else>用户</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
           
        </TABLE>
     </td>
   </tr> -->
   
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font> 用户名：</td>
          <td width="35%" >
          	<s:if test="#attr.user!=null && #attr.user.userId!=0"><s:property value="#attr.user.userName" /></s:if>
          	<s:else><input  name="paramsUser.userName" id="paramsUser.userName" class="form-control"/> </s:else>
          </td>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 密码：</td>
          <td>
            <s:if test="#attr.user!=null && #attr.user.userId!=0">
          	<input  name="paramsUser.userPass" id="paramsUser.userPass" value="" class="form-control" />
          	</s:if>
          	<s:else>
          	
          	<input  name="paramsUser.userPass" id="paramsUser.userPass" value="" class="form-control" />
          	
          	</s:else>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 姓名：</td>
          <td>
            <!--<s:textfield name="paramsUser.realName" class="form-control" id="paramsUser.realName" value="%{#attr.user.realName}"/>-->
            <input  name="paramsUser.realName" class="form-control" id="paramsUser.realName" value="${attr.user.realName}"/>
          </td>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 性别：</td>
          <td>
            <input type="radio" id="sex1" name="paramsUser.userSex" value="1"/>男&nbsp;&nbsp;
            <input type="radio" id="sex2" name="paramsUser.userSex" value="2"/>女
          </td>
        </tr>   
        <tr>
          <td align="right" style="padding-right:5px">公司：</td>
          <td>
            <input name="paramsUser.userCompany" id="paramsUser.userCompany"class="form-control"value="${attr.user.userCompany}" />
          </td> 
          <td align="right" style="padding-right:5px">地址：</td>
          <td>
          	<input name="paramsUser.userAddress" id="paramsUser.userAddress" class="form-control" value="${attr.user.userAddress}"/>
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px">邮箱：</td>
          <td>
            <input name="paramsUser.userMail" id="paramsUser.userMail" class="form-control"value="${attr.user.userMail}"/>
          </td> 
          <td align="right" style="padding-right:5px">电话：</td>
          <td>
          	<input name="paramsUser.userPhone" id="paramsUser.userPhone" class="form-control"value="${attr.user.userPhone}"/>
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
          	<s:if test="#attr.user!=null && #attr.user.userId!=0">
          	<input type="button" id="editBtn" Class="btn btn-info" value="保存"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btn btn-info" value="添 加" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label>
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

