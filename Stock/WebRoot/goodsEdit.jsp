<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<!DOCTYPE html>
<html lang="en">
<head>
 <%@ include file="./frame/head.jsp"%>

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num = /^\d+?$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsGoods\\.goodsNo").val()==''){
			alert('货物编号不能为空');
			return;
		}
		if($("#paramsGoods\\.goodsName").val()==''){
			alert('货物名称不能为空');
			return;
		}
		if(!num.exec($("#paramsGoods\\.goodsCount").val())){
			alert('货物数量必须为数字');
			return;
		}
		$("#paramsGoods\\.goodsId").val(0);
		$("#info").attr('action','Admin_addGoods.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		if($("#paramsGoods\\.goodsName").val()==''){
			alert('货物名称不能为空');
			return;
		}
		if(!num.exec($("#paramsGoods\\.goodsCount").val())){
			alert('货物数量必须为数字');
			return;
		}
		$("#info").attr('action','Admin_saveGoods.action').submit();
			 
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
                 添加货物
            </h3>
            
        </div>
		 
        <div class="wrapper">
        			 <div class="row">
            			<div class="col-md-12">
                			<!--breadcrumbs start -->
                			<ul class="breadcrumb panel">
                    		<li><a href="index.jsp"><i class="fa fa-home"></i>主页</a></li>
                    		<li class="active">货物管理</li>
                    		<li><a href="goodsEdit.jsp">添加货物</a></li>
                    		
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
													<form id="info" name="info" action="Admin_addGoods.action" method="post">   
<s:hidden id="paramsUser.userId" name="paramsUser.userId" value="%{#attr.user.userId}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0"  class="table table-bordered"style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="left" width="30%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.goods!=null && #attr.goods.goodsId!=0">编辑</s:if><s:else>添加</s:else>货物</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
  
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
      	<tr>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font> 货物编号：</td>
          <td width="35%">
          	<s:if test="#attr.goods!=null && #attr.goods.goodsId!=0">
          	<s:property value="#attr.goods.goodsNo"/>
          	</s:if>
          	<s:else>
          	<div class="col-md-7">
          	<input class="form-control"name="paramsGoods.goodsNo" id="paramsGoods.goodsNo" />
          	</div>
          	</s:else>
          	
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 货物名称：</td>
          <td width="65%">
          <div class="col-md-7">
          	<input class="form-control" name="paramsGoods.goodsName" id="paramsGoods.goodsName" />
          </div>
          </td>
        </tr> 
        <tr>
       
          <td align="right" style="padding-right:5px"><font color="red">*</font> 货物数量：</td>
          <td>
           <div class="col-md-7">
          	<input class="form-control" name="paramsGoods.goodsCount" id="paramsGoods.goodsCount" />
          </div>
          </td>
          	
        </tr>
        <tr>
          <td align="right" style="padding-right:5px">货物说明：</td>
          <td>
          	<!--<s:textarea name="paramsGoods.goodsDesc" id="paramsGoods.goodsDesc" value="%{#attr.goods.goodsDesc}" cssStyle="width:300px;height:60px;">
          	</s:textarea>-->
          	 <div class="col-sm-10">
                            <textarea name="paramsGoods.goodsDesc" id="paramsGoods.goodsDesc"rows="6" class="form-control"></textarea>
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
          	<s:if test="#attr.goods!=null && #attr.goods.goodsId!=0">
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

