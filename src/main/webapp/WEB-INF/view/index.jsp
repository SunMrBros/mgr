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
<title>河北科普后台</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

<link href="<%=path %>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path %>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path %>/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=path %>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="<%=path %>/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="<%=path %>/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="<%=path %>/dwz/chart/raphael.js"></script>
<script type="text/javascript" src="<%=path %>/dwz/chart/g.raphael.js"></script>
<script type="text/javascript" src="<%=path %>/dwz/chart/g.bar.js"></script>
<script type="text/javascript" src="<%=path %>/dwz/chart/g.line.js"></script>
<script type="text/javascript" src="<%=path %>/dwz/chart/g.pie.js"></script>
<script type="text/javascript" src="<%=path %>/dwz/chart/g.dot.js"></script>

<script src="<%=path %>/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="<%=path %>/dwz/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="<%=path %>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("<%=path %>/dwz/dwz.frag.xml", {
		loginUrl:"<%=path %>/dwz/login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"<%=path %>/dwz/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="<%=path%>/index.action">标志</a>
				<ul class="nav">
					<li><a>${admin.loginname }</a></li>
					<li><a>${user.role.roleName }</a></li>
					<li><a href="<%=path%>/loginout.action">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>子管理员管理</h2>
					</div>
					<div class="accordionContent">
							<ul class="tree treeFolder">
								<li><a href="<%=path%>/getAdmins.action"
									target="navTab" rel="admin" title="管理员列表">管理员</a></li>
							</ul>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2><span>Folder</span>基础数据配置</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<%=path %>/lunbo/getLunboPage.action" target="navTab" rel="lunbo" title="轮播图管理">轮播图管理</a></li>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2><span>Folder</span>基地配置</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<%=path %>//getApplyOrderList.action" target="navTab" rel="hebei" title="河北省基地信息管理">河北省基地信息管理</a></li>
							<li><a href="<%=path %>//getApplyOrderList.action" target="navTab" rel="beijing" title="北京市基地信息管理">北京市基地信息管理</a></li>
							<li><a href="<%=path %>//getApplyOrderList.action" target="navTab" rel="tianjin" title="天津市基地信息管理">天津市基地信息管理</a></li>
						</ul>
					</div> 	
					
					<div class="accordionHeader">
						<h2><span>Folder</span>旅游管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<%=path %>/column/getColumnPage.action" target="navTab" rel="column" title="栏目管理">栏目管理</a></li>
							<li><a href="<%=path %>/route/getRoutesPage.action" target="navTab" rel="routes" title="线路管理">线路管理</a></li>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2><span>Folder</span>日志管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<%=path %>//getApplyOrderList.action" target="navTab" rel="demo_page1" title="操作日志">操作日志</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2010 <a href="demo_page2.html" target="dialog">DWZ团队</a> 京ICP备05019125号-10</div>
</body>
</html>