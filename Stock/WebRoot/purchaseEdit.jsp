<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<!DOCTYPE html>
<html lang="en">
<head>
 <%@ include file="./frame/head.jsp"%>
 <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
 <script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="ext/ext-base.js"></script>
<script type="text/javascript" src="ext/ext-all.js"></script>
<script type="text/javascript" src="ext/ext-lang-zh_CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	var num = /^\d+?$/;
	jq=jQuery.noConflict(); 
	jq("#addBtn").bind('click',function(){
		if(jq("#goodsId").val()=='0'){
			alert('货物名称不能为空');
			return;
		}
		if(!num.exec(jq("#paramsPurchase\\.purchaseCount").val())){
			alert('货物数量不能为空');
			return;
		}
		if(jq("#paramsPurchase\\.purchaseAdmin").val()==''){
			alert('供货商不能为空');
			return;
		}
		if(jq("#paramsPurchase\\.purchaseDate").val()==''){
			alert('入库日期不能为空');
			return;
		}
		jq("#paramsPurchase\\.purchaseId").val(0);
		jq("#info").attr('action','Admin_addPurchase.action').submit();
		 
	 });
	 
});
Ext.onReady(function(){
	Ext.get("op").show();
	Ext.Ajax.request({
	   url: 'queryGoods.action',
	   params: {},
	   success: function(response){
		   var responseObj=Ext.util.JSON.decode(response.responseText);
		   if(responseObj.success) {	
				 var goodss = responseObj.data.goodss;
				 if(goodss!=null && goodss.length>0){
					for(var i=0;i<goodss.length;i++){
						Ext.DomHelper.useDom=true;  
						Ext.DomHelper.append(Ext.get('goodsId'), {tag:'option',value:goodss[i].goodsId,html:goodss[i].goodsName});  
					}
				 }
				 Ext.get("op").hide();
			}else  if(responseObj.err.msg){
				 alert('失败：'+responseObj.err.msg);
				 Ext.get("op").hide();
			}else{
				 alert('失败：服务器异常！');
				 Ext.get("op").hide();
			}	
	   },
	   failure: function(response) {
		   alert('失败','服务器异常！');
	   }	   
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
               添加入库信息
            </h3>
            
        </div>
		 
        <div class="wrapper">
        			<div class="row">
            			<div class="col-md-12">
                			<!--breadcrumbs start -->
                			<ul class="breadcrumb panel">
                    		<li><a href="index.jsp"><i class="fa fa-home"></i>主页</a></li>
                    		<li class="active">个人中心</li>
                    		<li><a href="purchaseEdit.jsp">添加入库信息</a></li>
                    	
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
<form id="info" name="info" action="Admin_addPurchase.action" method="post">    
<s:hidden id="paramsPurchase.purchaseId" name="paramsPurchase.purchaseId" value="%{#attr.purchase.purchaseId}" /> 
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 货物名称：</td>
          <td width="65%">
           
                       <div class="col-lg-10">
                            <select id="goodsId" name="paramsPurchase.goods.goodsId" style="width:310px">
          		<option value="0">请选择</option>
          	
            </select>
           
            <span id="op" style="display:none;width:50px"><img src="xxximages/loading001.gif"/></span>
                         </div>
         
          	
          </td>
       </tr> 
       <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 入库数量：</td>
          <td width="65%" >
          <div class="col-md-7">
          	<input  class="form-control" name="paramsPurchase.purchaseCount" id="paramsPurchase.purchaseCount" />
          	</div>
          </td>
       </tr> 
       <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 供货商：</td>
          <td width="65%" >
          <div class="col-md-7">
          	<input  class="form-control" name="paramsPurchase.purchaseAdmin" id="paramsPurchase.purchaseAdmin" />
          	</div>
          </td>
       </tr> 
       <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 入库日期：</td>
          <td width="65%" >
          <div class="col-md-7">
          	<input class="form-control" name="paramsPurchase.purchaseDate" id="paramsPurchase.purchaseDate" 
					 onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
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

