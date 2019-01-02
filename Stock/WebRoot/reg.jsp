<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>用户注册</title>

        <!-- CSS -->
        <link href="css/main.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>

        <!-- Content -->
        <div class="top-content">
        	<div class="pageTitle">
					&nbsp;&nbsp;&nbsp;<span id="MainTitle" style="color:white">仓储物流管理系统&gt;&gt;用户注册</span>
			</div>
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                    
                        <div class="col-sm-8 col-sm-offset-2 text">
						<h1>用户注册须知</h1>
                        
                            <h1><strong>Warning</strong> User registration notice</h1>
                            <div class="description">
                            	<h3>
	                            	请填写您的真实姓名，并留下真实电话，以便在您忘记密码时找回密码，如果您忘记密码请联系029-888888感谢您的使用。
                            	</h3>
                            </div>
                            <div class="top-big-link">
                            	<a class="btn btn-link-1 launch-modal" href="#" data-modal-id="modal-register">立 即 注 册</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        
        <!-- MODAL -->
        <div class="modal fade" id="modal-register" tabindex="-1" role="dialog" aria-labelledby="modal-register-label" aria-hidden="true">
        	<div class="modal-dialog">
        		<div class="modal-content">
        			<!-- title -->
        			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal">
        					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
        				</button>
        				<h3 class="modal-title" id="modal-register-label">用户注册</h3>
        				
        			</div>
        			<!-- 内容 -->
        			<div class="modal-body">
        				
	                    <form id="info" name="info" action="LoginRegSystem.action" method="post">  
	                    	<div class="form-group">
										<tr>
											<td width="35%" class="label"><font color="red">*</font> 用户名&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td width="65%" class="field">
											<input type="text" name="params.userName" id="params.user_name" placeholder="用户名..." style="width:350px;"/>
											&nbsp;<label id="user_nameTip" style="color:red"></label>
											</td>
        								</tr>
	                        </div>
	                        <div class="form-group">
	                        	<tr>
									<td class="label"><font color="red">*</font> 密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp</td>
									<td class="field">
									<input type="text" name="params.userPass" id="params.user_pass" placeholder="密码..."style="width:350px;"/>
									&nbsp;<label id="user_passTip" style="color:red"></label>
									</td>
							</tr> 
	                        </div>
	                        <div class="form-group">
	                        	<tr>
									<td class="label"><font color="red">*</font> 确认密码</td>
									<td class="field">
									<input type="text" name="user_rpass" id="user_rpass" placeholder="确认密码..."style="width:350px;"/>
									&nbsp;<label id="user_rpassTip" style="color:red"></label>
									</td>
        						</tr> 
	                        </div>
	                        <div class="form-group">
	                        	<tr>
									<td class="label"><font color="red">*</font> 姓名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp</td>
									<td class="field">
									<input type="text" name="params.realName" id="params.real_name"placeholder="姓名..." style="width:350px;"/>
									&nbsp;<label id="real_nameTip" style="color:red"></label>
									</td>
        						</tr> 
	                        </div>
							<div class="form-group">
	                        	 <tr>
									<td class="label"><font color="red">*</font> 性别&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp</td>
									<td class="field">
									<input type="radio" id="sex1" name="params.userSex" checked="checked" value="1"/>男&nbsp;&nbsp;
									<input type="radio" id="sex2" name="params.userSex" value="2"/>女
									</td>
        						</tr>  
	                        </div>
							<div class="form-group">
	                        	 <tr>
									<td class="label"><font color="red">*</font> 电话&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp</td>
									<td class="field">
									<input type="text"  name="params.userPhone" id="paramsUser.user_phone" style="width:350px;"/>
									&nbsp;<label id="user_phoneTip" style="color:red"></label>
									</td>
        						</tr>
	                        </div>
							<div class="form-group">
	                        	<tr>
								<td class="label"><font color="red">*</font> 验证码&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td class="field">
								<input type="text" id="params.random" name="params.random" style="width:350px;" class="inputstyle"/>
								&nbsp;<img src="Random.jsp" width="100" height="50" style="cursor:pointer;padding-top:5px" title="换一张" id="safecode" border="0" onClick="reloadcode()"/>
								&nbsp;<label id="randomTip" style="color:red"></label>
								</td>
								</tr>
	                        </div>

        					
          					
          						<button type="button" class="btn" id="addBtn">立即注册</button>
          						
            					&nbsp;<label style="color:red">${tip}</label>
          						

	                    </form>
	                    
        			</div>
        			
        		</div>
        	</div>
        </div>


        <!-- Javascript -->
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
		<script language="javascript" type="text/javascript">
//实现验证码点击刷新
function reloadcode(){
	var verify=document.getElementById('safecode');
	verify.setAttribute('src','Random.jsp?'+Math.random());
}
$(document).ready(function(){
	var addBtn = $("#addBtn");
	var user_name = $("#params\\.user_name");
	var user_pass = $("#params\\.user_pass");
	var user_rpass = $("#user_rpass");
	var real_name = $("#params\\.real_name");
	var user_phone = $("#params\\.user_phone");
	var random = $("#params\\.random");
	
	var name=/^[a-zA-Z][a-zA-Z0-9_]{5,14}$/;
    var pass=/^[a-zA-Z0-9]{7,14}$/;
    var age=/^\d+$/;
    var mail=/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
    
    user_name.blur(function(){
    	if(user_name.val()==""){
			$("#user_nameTip").html("登录名不能为空");
			return;
		}else{
			$("#user_nameTip").html('');
		}
    });
    user_pass.blur(function(){
    	if(user_pass.val()==""){
    		$("#user_passTip").html("密码不能为空");
			return;
		}else{
			$("#user_passTip").html('');
		}
    });
    user_rpass.blur(function(){
    	if(user_rpass.val()==''){
    		$("#user_rpassTip").html("请再次填写密码");
			return;
		}
    	if(user_pass.val()!=user_rpass.val()){
    		$("#user_rpassTip").html("两次输入密码不一致");
			return;
		}else{
			$("#user_rpassTip").html('');
		}
    });
    real_name.blur(function(){
    	if(real_name.val()==''){
			$("#real_nameTip").html("姓名不能为空");
			return;
		}else{
			$("#real_nameTip").html('');
		}
    });
    user_phone.blur(function(){
    	if(real_name.val()==''){
			$("#user_phoneTip").html("电话不能为空");
			return;
		}else{
			$("user_phoneTip").html('');
		}
    });
	random.blur(function(){
    	if(random.val()==''){
			$("#randomTip").html("验证码不能为空");
			return;
		}else{
			$("#randomTip").html('');
		}
    });
    
	addBtn.bind("click",function(){
		
		var tips = $("#user_nameTip").html()
				  +$("#user_passTip").html()
				  +$("#user_rpassTip").html()
				  +$("#real_nameTip").html()
				  +$("#user_phoneTip").html()
				  +$("#randomTip").html();
		
		if(tips!=""){
			alert('请按照提示输入注册信息!');
			return;
		}
		if(user_rpass.val()==''||user_pass.val()==""||real_name.val()==''){
			alert('请填写必要信息');
			return;
			}
		
		
		var aQuery = $("#info").serialize();  
		$.post('LoginRegSystem.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('注册成功！即将进入系统……');
						 window.location.href="index.jsp";
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
});
</script>
    </body>

</html>