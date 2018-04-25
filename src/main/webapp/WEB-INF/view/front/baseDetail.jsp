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
<html>
	<head>
		<meta charset="utf-8" />
		<title>基地详情页</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<META NAME="MobileOptimized" CONTENT="yes">
		<!--移动优化-->
		<meta name="apple-touch-fullscreen" content="yes" />
		<!--全屏-->
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<!--网站开启对web app程序的支持。-->
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/jidi_xiangqing.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/swiper.css" />
		<script src="<%=path %>/js/js.js"></script>
		<script src="<%=path %>/js/main.js"></script>
		<script src="<%=path %>/js/swiper.js"></script>
		 <script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=erX4rnXqpHS3yMqfC0RrWowanRdnmwop"></script>
		
	</head>
	<body>
		<div id="nav_bottom">
			<ul>
				<li  onclick="index()">
					<img src="<%=path %>/img/shouye02.png" alt="">
					<p>首页</p>
				</li><li onclick = 'jidi()' style="background: #eee;color: #3399ff">
					<img src="<%=path %>/img/jidi02.png" alt="">
					<p>基地</p>
				</li><li onclick = 'xianlu()'>
					<img src="<%=path %>/img/xianlu.png" alt="">
					<p>线路</p>
				</li><li onclick = 'fujin()'>
					<img src="<%=path %>/img/fujin.png" alt="">
					<p>附近</p>
				</li>
			</ul>
		</div>
		<div id="jidi_xiangqing_top">
			<div class="jidi_xiangqing_top_img">
				<img src="${base.picUrl }" alt="">
			</div>
			<div class="jidi_xiangqing_top_list">
				<p>${base.title }</p>
				<div class="jidi_xiangqing_top_list_p">
					<img src="<%=path %>/img/people.png" alt="">
					<span>联系人：${base.telephone }</span>
				</div>
				<div class="jidi_xiangqing_top_list_p">
					<img src="<%=path %>/img/dibiao.png" alt="">
					<span>${base.address }</span>
				</div>
				<div class="jidi_xiangqing_top_list_p">
					<img src="<%=path %>/img/time.png" alt="">
					<span>开放时间：${base.openTime } -- ${base.closeTime }</span>
				</div>
				<div class="jidi_xiangqing_top_list_p">
					<img src="<%=path %>/img/menpiao.png" alt="">
					<span>门票：${base.ticketPrice }</span>
				</div>
			</div>
		</div>
		<div id="jidi_xiangqing_center">
			<div class="jidi_xiangqing_center_cont">
				<img src="<%=path %>/img/xiangqing_dianhua.png" alt="">
				<p>电话</p>
			</div>
			<div class="jidi_xiangqing_center_cont">
				<img src="<%=path %>/img/jidi_go.png" alt="">
				<p>路线</p>
			</div>
		</div>
		<div id="media">
			<video controls>
				<source src="${base.videoUrl }" type="video/mp4">
			</video>
		</div>
		<div id="media_02">
			<audio controls>
  				<source src="${base.audioUrl }" type="audio/mp3">
			</audio>
		</div>
		<div id="jidi_xiangqing_list">
			<span>${base.title }</span>
			<p>${base.content }</p>
		</div>
	</body> 
	</html>