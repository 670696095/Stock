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
	 $("#addBtn").bind('click',function(){
		if($("#goodsId").val()=='0'){
			alert('货物名称不能为空');
			return;
		}
		if(!num.exec($("#paramsOrders\\.goodsCount").val())){
			alert('货物数量不能为空');
			return;
		}
		if($("#paramsOrders\\.orders_address").val()==''){
			alert('送货地点不能为空');
			return;
		}
		$("#paramsOrders\\.ordersId").val(0);
		$("#info").attr('action','Admin_addOrders.action').submit();
		 
	 });
	 
	 function decodeEntities(s){ 
		    var str, temp= document.createElement('p'); 
		    temp.innerHTML= s; 
		    str= temp.textContent || temp.innerText; 
		    temp=null; 
		    return str; 
	}
	 
	 var goodss = {};
	 var goodss2 = {};
	 <s:if test="#attr.goodss!=null&&#attr.goodss.size()>0">
	 <s:iterator value="#attr.goodss" id="goods">
	 	var goodsId = "<s:property value='#goods.goodsId'/>";
	 	var goodsNo = decodeEntities("<s:property value='#goods.goodsNo'/>");
	 	var goodsName = decodeEntities("<s:property value='#goods.goodsName'/>");
	 	goodss[goodsId] = goodsNo;
	 	goodss2[goodsId] = goodsName;
	 </s:iterator>
	 </s:if>
	 
	$("#goodsId").change(function(){
		var goodsId = $(this).val();
		if(goodsId!='0'){
			$("#goodsNo").val(goodss[goodsId]);
			$("#goodsName").val(goodss2[goodsId]);
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
               增添订单
            </h3>
            
        </div>
		 
        <div class="wrapper">
        			<div class="row">
            			<div class="col-md-12">
                			<!--breadcrumbs start -->
                			<ul class="breadcrumb panel">
                    		<li><a href="index.jsp"><i class="fa fa-home"></i>主页</a></li>
                    		<li class="active">订单查询</li>
                    		<li><a href="ordersEdit.jsp">增加新订单</a></li>
                    	
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
<form id="info" name="info" action="Admin_addOrders.action" method="post">   
<s:hidden id="paramsOrders.ordersId" name="paramsOrders.ordersId" value="%{#attr.orders.ordersId}" /> 
<s:hidden id="paramsOrders.userId" name="paramsOrders.userId" value="%{#attr.admin.userId}" /> 
<s:hidden id="paramsOrders.realName" name="paramsOrders.realName" value="%{#attr.admin.realName}" /> 
<s:hidden id="goodsNo" name="paramsOrders.goodsNo" value="" /> 
<s:hidden id="goodsName" name="paramsOrders.goodsName" value="" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" class="table table-bordered" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="left" width="30%"> 
             <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">添加订单</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
  
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 货物名称：</td>
          <td>
           
                       <div class="col-lg-10">
                         <s:select id="goodsId" name="paramsOrders.goodsId" 
          			list="#attr.goodss" listKey="goodsId" listValue="goodsName" 
          			headerKey="0" headerValue="请选择" cssStyle="width:155px">
          	</s:select>
           
            <span id="op" style="display:none;width:50px"><img src="xxximages/loading001.gif"/></span>
                         </div>
         
          	
          </td>
       </tr> 
      <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 货物数量：</td>
          <td width="65%" >
          <div class="col-md-7">
          	<input  class="form-control" name="paramsOrders.goodsCount" id="paramsOrders.goodsCount"  />
          	</div>
          </td>
       </tr> 
      <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 送货地点：</td>
          <td width="65%" >
          <div class="col-md-7">
          	<input  class="form-control" name="paramsOrders.ordersAddress" id="paramsOrders.ordersAddress" />
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
          	<input type="button" id="addBtn"  Class="btn btn-primary"value="添 加" />
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

