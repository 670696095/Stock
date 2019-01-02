<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<!DOCTYPE html>
<html lang="en">
<head>
 <%@ include file="./frame/head.jsp"%>
 <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
	 $("#addBtn").bind('click',function(){
		if($("#paramsCar\\.carModel").val()==''){
			alert('车辆型号不能为空');
			return;
		}
		if($("#paramsCar\\.carNumber").val()==''){
			alert('车牌号不能为空');
			return;
		}
		if($("#paramsCar\\.carFlag").val()=='0'){
			alert('车辆状态不能为空');
			return;
		}
		$("#paramsCar\\.carId").val(0);
		$("#info").attr('action','Admin_addCar.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		if($("#paramsCar\\.carModel").val()==''){
			alert('车辆型号不能为空');
			return;
		}
		if($("#paramsCar\\.carFlag").val()=='0'){
			alert('车辆状态不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveCar.action').submit();
			 
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
                新增运输车辆
            </h3>
            
        </div>
		 
        <div class="wrapper">
        			<div class="row">
            			<div class="col-md-12">
                			<!--breadcrumbs start -->
                			<ul class="breadcrumb panel">
                    		<li><a href="index.jsp"><i class="fa fa-home"></i>主页</a></li>
                    		<li class="active">个人中心</li>
                    		<li><a href="carEdit.jsp">新增运输车辆</a></li>
                    	
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
<form id="info" name="info" action="Admin_addCar.action" method="post">    
<s:hidden id="paramsCar.carId" name="paramsCar.carId" value="%{#attr.car.carId}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" class="table table-bordered" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="left" width="30%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.car!=null && #attr.car.carId!=0">编辑</s:if><s:else>添加</s:else>车辆</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
  
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
     	<tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 车辆型号：</td>
          <td width="65%">
           <div class="col-md-7">
          	<input  class="form-control" name="paramsCar.carModel" id="paramsCar.carModel" />
          	 </div>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 车牌号：</td>
          <td width="65%">
          	<s:if test="#attr.car!=null && #attr.car.carId!=0">
          	<s:property value="#attr.car.carNumber"/>
          	</s:if>
          	<s:else>
          	 <div class="col-md-7">
          	<input  class="form-control" name="paramsCar.carNumber" id="paramsCar.carNumber" />
          	</div>
          	</s:else>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px">车辆状态：</td>
          <td width="65%">
          <div class="col-lg-10">
          	<s:select list="#{'1':'闲置',2:'在用' }" id="paramsCar.carFlag" name="paramsCar.carFlag" value="%{#attr.car.carFlag}"
          			listKey="key" listValue="value" headerKey="0" headerValue="请选择" cssStyle="width:300px;">
          	</s:select>
          	</div>
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
          	<s:if test="#attr.car!=null && #attr.car.carId!=0">
          	<input type="button" id="editBtn" Class="btn btn-primary" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btn btn-primary" value="添 加" />
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

