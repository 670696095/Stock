<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<!DOCTYPE html>
<html lang="en">
<head>

 <%@ include file="./frame/head.jsp"%>
 
 <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>

<script type="text/javascript" src="ext/ext-base.js"></script>
<script type="text/javascript" src="ext/ext-all.js"></script>
<script type="text/javascript" src="ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=HAD4CdxTXkoAzZCIcyA1Bjkxd3geN5mK">

</script>
<script type="text/javascript"> 
var map = new BMap.Map("container");
// 创建地图实例  
var point = new BMap.Point(116.404, 39.915);
// 创建点坐标  
map.centerAndZoom(point, 15);
// 初始化地图，设置中心点坐标和地图级别  
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
               网点分布信息
            </h3>
            
        </div>
		 
        <div class="wrapper">
        			<div class="row">
            			
        			</div>
				<div class="row">
					<div class="col-md-12">
						<section class="panel">
							<div class="panel-body">
							<div id="container"></div> 
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

