
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>404 找不到了</title>
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
	<style type="text/css">
		body{
			background:url("<%=path%>/images/b.png");
			min-width: 1350px;
			background-repeat: no-repeat;
			background-size: cover;
			-webkit-background-size: cover;
			-o-background-size: cover;
			background-attachment:fixed;
			zoom:1;
		}
		ul{
			position: absolute;
			left: 47%;
			top: 48%;
			margin-bottom: 2em;
		}
		ul>li{
			line-height: 3em;
			font-size: 14px;
		}
		ul>li:nth-child(3){
			margin-bottom: 1.5em;
		}
		a{
			margin-right: 23%;
			font-size:  14px;
			text-decoration: none;
			color: #7ebefc;
		}
		a img{
			margin-top: 1%;
			margin-right: 2%;
			width:10px;
		}
	</style>
</head>
<body>
	<div>
		<ul>
			<li>你所查看的页面无法浏览或已不存在</li>
			<li>输入的地址不正确</li>
			<li>页面重定义或程序错误</li>
			<a href="<%=path %>/index.action"><img src="<%=path %>/images/home.png" alt="">首页</a><a href="javascript:history.go(-1)"><img src="<%=path %>/images/last.png" alt="">返回</a>
		</ul>	

	</div>
</body>
</html>
