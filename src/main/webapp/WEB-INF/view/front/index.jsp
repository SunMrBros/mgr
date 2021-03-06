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
		<title>京津冀科普旅游</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<META NAME="MobileOptimized" CONTENT="yes">
		<!--移动优化-->
		<meta name="apple-touch-fullscreen" content="yes" />
		<!--全屏-->
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<!--网站开启对web app程序的支持。-->
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/swiper.css" />
		<script src="<%=path %>/js/jquery.min.js"></script>
		<script src="<%=path %>/js/js.js"></script>
		<script src="<%=path %>/js/main.js"></script>
		<script src="<%=path %>/js/swiper.js"></script>
		

	</head>
	<body>
		<div id="nav_bottom">
			<ul>
				<li onclick="index()" style="background: #eee;color: #3399ff">
					<img src="<%=path %>/img/shouye.png" alt="">
					<p>首页</p>
				</li><li onclick = 'jidi()'>
					<img src="<%=path %>/img/jidi.png" alt="">
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

		<div id="nav_top">
			<div id="nav_top_cont">
				<img src="<%=path %>/img/logo.png" alt="">
				<input type="text" placeholder="请输入基地名称" class="input">
				<p class="p">搜索</p>
				<img src="<%=path %>/img/delete.png" alt="" class="del">
			</div>
		</div>

		<div class="swiper-container">
			<div class="swiper-wrapper">
				<c:forEach items="${lunbos }" var="lunbo">
					<div class="swiper-slide" onclick='lunbo()'>
						<img src="${lunbo.picurl }" />
					</div>
				</c:forEach>
				<div class="swiper-slide" onclick='lunbo()'>
					<img src="<%=path %>/img/lunbo02.jpg" />
				</div>
				<div class="swiper-slide" onclick='lunbo()'>
					<img src="<%=path %>/img/lunbo03.jpg" />
				</div>
				<div class="swiper-slide" onclick='lunbo()'>
					<img src="<%=path %>/img/lunbo04.jpg" />
				</div>
				<div class="swiper-slide" onclick='lunbo()'>
					<img src="<%=path %>/img/lunbo05.jpg" />
				</div>
			</div>
			<!-- Add Pagination -->
			<div class="swiper-pagination"></div>
		</div>
		<!-- Initialize Swiper -->
		<script>
			var swiper = new Swiper('.swiper-container', {
				pagination: '.swiper-pagination',
				nextButton: '.swiper-button-next',
				prevButton: '.swiper-button-prev',
				paginationClickable: true,
				spaceBetween: 30,
				centeredSlides: true,
				autoplay: 2500,
				loop: true,
				autoplayDisableOnInteraction: false
			});
		</script>

		<div id="top_list">
			<ul>
				<li onclick="jidi()">
					<div class="top_list_img"><img src="<%=path %>/img/top_list_img01.png" alt=""></div>
					<div class="top_list_cont"><p>科普基地</p><span>汇集京津冀三地166家经典特色科普基地</span></div>
					<div class="top_list_right"><p>更多</p><img src="<%=path %>/img/right.png" alt=""></div>
				</li><li onclick = 'xianlu()'>
					<div class="top_list_img"><img src="<%=path %>/img/top_list_img02.png" alt=""></div>
					<div class="top_list_cont"><p>旅游线路</p><span>覆盖70条一日游、多日游及精品线路</span></div>
					<div class="top_list_right"><p>更多</p><img src="<%=path %>/img/right.png" alt=""></div>
				</li>
			</ul>
		</div>

		<div id="tuijian_list">
			<div class="title">
				<div class="tuijian_list_img"><img src="<%=path %>/img/xiaoshou.png" alt=""></div>
				<div class="tuijian_list_cont"><p>推荐科普基地</p></div>			
			</div>
			<div id="tuijian_list_content">
				<ul>
					<c:forEach items="${recBases }" var="base">
						<li onclick = 'jidi_xiangqing("<%=path%>/app/toBaseDetail.action?baseId=${base.id }")'>
							<div class="tuijian_list_content_img">
								<img src="${base.picUrl }" alt="${base.title }">
							</div>
							<div class="tuijian_list_content_p">
								<p>${base.title }</p>
								<div class="tuijian_list_content_cont">
									<img src="<%=path %>/img/dibiao.png" alt="">
									<span>${base.address }</span>
								</div>
							</div>
						</li>
					</c:forEach>
					<li onclick = 'jidi_xiangqing()'>
						<div class="tuijian_list_content_img">
							<img src="<%=path %>/img/tuijian02.jpg" alt="">
						</div>
						<div class="tuijian_list_content_p">
							<p>延庆世界地质公园博物馆</p>
							<div class="tuijian_list_content_cont">
								<img src="<%=path %>/img/dibiao.png" alt="">
								<span>北京市延庆县妫水北街72号</span>
							</div>
						</div>
					</li>
					<li onclick = 'jidi_xiangqing()'>
						<div class="tuijian_list_content_img">
							<img src="<%=path %>/img/tuijian03.jpg" alt="">
						</div>
						<div class="tuijian_list_content_p">
							<p>天葡庄园</p>
							<div class="tuijian_list_content_cont">
								<img src="<%=path %>/img/dibiao.png" alt="">
								<span>北京市密云县巨各庄镇各庄村村南200米</span>
							</div>
						</div>
					</li>
					<li onclick = 'jidi_xiangqing()'>
						<div class="tuijian_list_content_img">
							<img src="<%=path %>/img/tuijian04.jpg" alt="">
						</div>
						<div class="tuijian_list_content_p">
							<p>北京密云蔡家洼休闲观光工业园</p>
							<div class="tuijian_list_content_cont">
								<img src="<%=path %>/img/dibiao.png" alt="">
								<span>北京市密云县巨各庄镇蔡家洼存甲一号</span>
							</div>
						</div>
					</li>
					<li onclick = 'jidi_xiangqing()'>
						<div class="tuijian_list_content_img">
							<img src="<%=path %>/img/tuijian05.jpg" alt="">
						</div>
						<div class="tuijian_list_content_p">
							<p>北京首云矿山公园</p>
							<div class="tuijian_list_content_cont">
								<img src="<%=path %>/img/dibiao.png" alt="">
								<span>北京市密云县巨各庄镇豆各庄村北首云矿业股份有限公司</span>
							</div>
						</div>
					</li>
					<li onclick = 'jidi_xiangqing()'>
						<div class="tuijian_list_content_img">
							<img src="<%=path %>/img/tuijian06.jpg" alt="">
						</div>
						<div class="tuijian_list_content_p">
							<p>北京张裕爱斐堡国际酒庄</p>
							<div class="tuijian_list_content_cont">
								<img src="<%=path %>/img/dibiao.png" alt="">
								<span>北京市密云县巨各庄镇东白岩村</span>
							</div>
						</div>
					</li>
					<li onclick = 'jidi_xiangqing()'>
						<div class="tuijian_list_content_img">
							<img src="<%=path %>/img/tuijian07.jpg" alt="">
						</div>
						<div class="tuijian_list_content_p">
							<p>北京老爷车博物馆</p>
							<div class="tuijian_list_content_cont">
								<img src="<%=path %>/img/dibiao.png" alt="">
								<span>北京市怀柔区杨宋镇凤翔一园19号</span>
							</div>
						</div>
					</li>
					<li onclick = 'jidi_xiangqing()'>
						<div class="tuijian_list_content_img">
							<img src="<%=path %>/img/tuijian08.jpg" alt="">
						</div>
						<div class="tuijian_list_content_p">
							<p>国家中影数字制作基地</p>
							<div class="tuijian_list_content_cont">
								<img src="<%=path %>/img/dibiao.png" alt="">
								<span>北京市怀柔区杨宋镇凤和一园8号</span>
							</div>
						</div>
					</li>
					<li onclick = 'jidi_xiangqing()'>
						<div class="tuijian_list_content_img">
							<img src="<%=path %>/img/tuijian09.jpg" alt="">
						</div>
						<div class="tuijian_list_content_p">
							<p>中国坦克博物馆</p>
							<div class="tuijian_list_content_cont">
								<img src="<%=path %>/img/dibiao.png" alt="">
								<span>	北市市昌平区阳坊镇</span>
							</div>
						</div>
					</li>
					<li onclick = 'jidi_xiangqing()'>
						<div class="tuijian_list_content_img">
							<img src="<%=path %>/img/tuijian10.jpg" alt="">
						</div>
						<div class="tuijian_list_content_p">
							<p>中国航空博物馆</p>
							<div class="tuijian_list_content_cont">
								<img src="<%=path %>/img/dibiao.png" alt="">
								<span>北京市昌平区小汤山镇航空科技馆</span>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		
	</body>
</html>