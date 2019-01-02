<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html>
<html lang="en">
<head>
 <%@ include file="./frame/head.jsp"%>

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>

<!--dynamic table-->
  <link href="tjs/advanced-datatable/css/demo_page.css" rel="stylesheet" />
  <link href="tjs/advanced-datatable/css/demo_table.css" rel="stylesheet" />
  <link rel="stylesheet" href="tjs/data-tables/DT_bootstrap.css" />

<script type="text/javascript">


var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listUsers.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的用户！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delUsers.action?paramsUser.ids="+ids;
       document.info.submit();
    }
    
}
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUsers.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUsers.action";
  document.info.submit();
}

</script>
</head>

<body class="sticky-header" >

<section>
     <%@ include file="./frame/menu.jsp"%>

    
	<!-- 主体导航栏-->
    <div class="main-content" >

        <%@ include file="./frame/header.jsp"%>
        <!-- 内容导航栏-->
        <div class="page-heading">
            <h3>
                 	用户查询
            </h3>
            
        </div>
        
        <div class="wrapper">
        <div class="row">
        <div class="col-sm-12">
        <section class="panel">
        <header class="panel-heading">
           		<input type="button" value="增加" onclick="window.location='Admin_addUserShow.action';" class="btn btn-primary"/>&nbsp;
      			<input type="button" value="删除" onclick="del();" class="btn btn-danger"/>
      			<input type="button" value="查询用户" onclick="serch();" class="btn btn-default"/>&nbsp;
            <span class="tools pull-right">
                <a href="javascript:;" class="fa fa-chevron-down"></a>
                
             </span>
        </header>
        <div class="panel-body">
        <div class="adv-table">
        
        <form name="info" id="info" action="Admin_listUsers.action" method="post">


<table width="95%" align="center" cellpadding="0" cellspacing="0">
  
  <tr>
    
    <td width="" align="left">
     
      
      
      
    </td>
  </tr>
 
</table>



</form> 
        
        
        
        
        <table  class="display table table-bordered table-striped" id="dynamic-table">
        
        
        
        
        <thead>
        <tr class="gradeX">
     <th ><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <th>序号</th>
     <th >用户名</th>
     <th  >姓名</th>
     <th  >性别</th>
     <th>公司</th>
     <th >地址</th>
     <th >邮箱</th>
     <th >电话</th>
     <th>添加时间</th>
     <th >操作</th>
     
   </tr>
        </thead>
        <tbody>
        <s:if test="#attr.users!=null && #attr.users.size()>0">
   <s:iterator value="#attr.users" id="user" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#user.userId}" cssStyle="vertical-align:text-bottom;"/></td>
     <td width="" align="center"><s:property value="#status.index+#attr.paramsUser.start+1"/></td>
     <td width="" align="center"><s:property value="#user.userName"/></td>
     <td width="" align="center"><s:property value="#user.realName"/></td>
     <td width="" align="center"><s:property value="#user.userSexDesc"/></td>
     <td width="" align="center"><s:property value="#user.userCompany"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#user.userAddress"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#user.userMail"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#user.userPhone"/></td>
     <td width="" align="center"><s:property value="#user.regDateDesc"/></td>  
     <td width="" align="center">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_editUser.action?paramsUser.userId=%{#user.userId}">编辑</s:a>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="11" align="center"><b></b></td>
   </tr>
   </s:else>
      
        </tbody>
        
        </table>
        
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
<script src="tjs/jquery-1.10.2.min.js"></script>
<script src="tjs/jquery-ui-1.9.2.custom.min.js"></script>
<script src="tjs/jquery-migrate-1.2.1.min.js"></script>
<script src="tjs/bootstrap.min.js"></script>
<script src="tjs/modernizr.min.js"></script>
<script src="tjs/jquery.nicescroll.js"></script>

<!--dynamic table-->
<script type="text/javascript" language="javascript" src="tjs/advanced-datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="tjs/data-tables/DT_bootstrap.js"></script>
<!--dynamic table initialization -->
<script src="tjs/dynamic_table_init.js"></script>

<!--common scripts for all pages-->
<script src="tjs/scripts.js"></script>

</body>
</html>

