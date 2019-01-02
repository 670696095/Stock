<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台登录</title>
<meta name="author" content="czw" />
<link rel="stylesheet" type="text/css" href="css/logincss/style.css" tppabs="css/logincss/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="js/loginjs/jquery.js"></script>
<script src="js/loginjs/verificationNumbers.js" tppabs="js/verificationNumbers.js"></script>
<script src="js/loginjs/Particleground.js" tppabs="js/Particleground.js"></script>


<script language="javascript" type="text/javascript">

$(document).ready(function(){

	
	 //粒子背景特效
	  $('body').particleground({
	    dotColor: '#5cbdaa',
	    lineColor: '#5cbdaa'
	  });
	  //验证码
	  createCode();
	 
	 
	  var loginInBtn = $("#loginInBtn");
		var user_name = $("#params\\.userName");
		var user_pass = $("#params\\.userPass");
		var random = $("#J_codetext");
		var loginTip = $("#loginTip");
		
		loginInBtn.bind('click',function(){
			if(user_name.val()==''||user_pass.val()==''||random.val()==''){
				loginTip.html("用户名、密码和验证码不能为空！")
				//alert("用户名、密码和验证码不能为空！");
				return;
			}
			else if(!validate()){
				loginTip.html("验证码错误");
				return;
				}
			else{
			$("#info").submit();
			}
			 
		});
		
		var regInBtn = $("#regInBtn");
		regInBtn.bind('click',function(){
			window.location.href="reg.jsp";
			 
		});
		function validate () {
		    var inputCode = document.getElementById("J_codetext").value.toUpperCase();
		    var codeToUp=code.toUpperCase();
		    if(inputCode.length <=0) {
		      document.getElementById("J_codetext").setAttribute("placeholder","输入验证码");
		      createCode();
		      return false;
		    }
		    else if(inputCode != codeToUp ){
		      document.getElementById("J_codetext").value="";
		      document.getElementById("J_codetext").setAttribute("placeholder","验证码错误");
		      createCode();
		      return false;
		    }
		    else {
		     
		      //createCode();
		      return true;
		    }


		}



 
 
});
</script>
</head>
<body>
<FORM id="info" name="info"  method="post" action="LoginInSystem.action">
<dl class="admin_login">
 <dt>
  <strong>物流仓储信息管理系统</strong>
  <em>Management System</em>
 </dt>
 <dd class="user_icon">
  <input type="text" placeholder="账号" class="login_txtbx" id="params.userName" name="params.userName" name=txtName/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" class="login_txtbx"id="params.userPass" name="params.userPass"/>
 </dd>
 <dd class="val_icon">
  <div class="checkcode">
    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">
    <canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
  </div>
  <input type="button" value="验证码核验" class="ver_btn" onClick="validate();">
 </dd>


 <dd>
  <input type="button"  id="loginInBtn" value="立即登陆" class="submit_btn"/>
  <input type="button"  id="regInBtn"  value="用户注册" class="submit_btn"/>
 </dd>

 <dd>
 <strong>&copy; 2018-2020 <a href="http://www.xaut.edu.cn//" target="_blank">西安理工大学</a>.
				</strong>崔智文版权所有.
  
  
 </dd>
 
							
							<div colspan="1" id="loginTip" style="HEIGHT:33px; color:orange" >${tip}</div>
	
</dl>
</form>
</body>
</html>
