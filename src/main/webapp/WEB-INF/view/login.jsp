<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理平台</title>
<link href="<%=path%>/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<script type="text/javascript">
function changeImg(){
    var img = document.getElementById("img"); 
    img.src ="<%=path%>/getCodeImg.action?date=" + new Date();
}
function enmd5(v){
	var en=hex_md5(v.value);
	v.value=en;
}
</script>
<script src="<%=path %>/js/md5.js" type="text/javascript"></script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a><img src="<%=path%>/dwz/themes/default/images/login_logo.gif" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
					</ul>
				</div>
				<h2 class="login_title"><img src="<%=path%>/dwz/themes/default/images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="<%=path %>/login.action" method="post">
					<p>
						<label>用户名：</label>
						<input type="text" name="loginname" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="password" size="20" class="login_input" onblur="enmd5(this)"/>
					</p>
					<p>
						<label>验证码：</label>
						<input class="code" name="code" type="text" size="5" />
						<span><img id="img" src="<%=path%>/getCodeImg.action" onclick="javascript:changeImg()" width="75" height="28" /></span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="<%=path%>/dwz/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#">${msg }</a></li>
				</ul>
				<div class="login_inner">
					<p>使用说明类文字</p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2009 www.dwzjs.com Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>