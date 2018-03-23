
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
<form id="pagerForm" method="post" action="<%=path%>/route/getRoutesPage.action">
	<input type="hidden" name="pageNum" value="1"/>
	<input type="hidden"name="numPerPage"value="${routesPage.pageSize }"/>
	<input type="hidden" name="title" value="${webVo.title}"/>
	<input type="hidden" name="startDate" value="<fmt:formatDate value="${queryValue.startDate }" pattern="yyyy-MM-dd"/>" />
	<input type="hidden" name="endDate" value="<fmt:formatDate value="${queryValue.endDate }" pattern="yyyy-MM-dd"/>" />
	<input type="hidden" name="telephone" value="${queryValue.telephone }"/>
	<input type="hidden" name="identifyid" value="${queryValue.identifyid }"/>
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path %>/route/getRoutesPage.action" method="post" onreset="$(this).find('select.combox').comboxReset()">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					名称：<input type="text" name="title" value="${webVo.title }"/>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="reset">重置</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<li><a class="add" href="<%=path %>/route/toAddRoute.action" target="dialog" width="620" height="620" rel="add_route"><span>添加线路</span></a></li>
			<li><a class="delete" href="<%=path %>/route/delRoute.action?routeId={route_id}" target="ajaxTodo" title="确定要删除吗?"><span>删除线路</span></a></li>
			<li><a class="edit" href="<%=path %>/route/toEditRoute.action?routeId={route_id}" width="620" height="620" target="dialog" rel="edit_route"><span>线路编辑</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="20" align="center">序号</th>
				<th width="80" align="center">名称</th>
				<th width="150" align="center">图片</th>
				<th width="60" align="center">所属栏目</th>
				<th width="50" align="center">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${routesPage.results }" var="route">
			
			<tr target="route_id" rel="${route.id }">
				<td align="center">${route.id }</td>
				<td align="center">${route.title }</td>
				<td align="center"><img src="<c:if test="${!empty route.basePic1 }">${route.basePic1 }</c:if>" height="80" width="150"/></td>
				<td align="center">${route.column.columnName }</td>
				<td align="center"><c:if test="${route.status==1 }">正常</c:if><c:if test="${route.status==0 }">禁用</c:if></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"<c:if test="${routesPage.pageSize==10 }">selected</c:if>>10</option>
				<option value="20"<c:if test="${routesPage.pageSize==20 }">selected</c:if>>20</option>
				<option value="30"<c:if test="${routesPage.pageSize==30 }">selected</c:if>>30</option>
				<option value="40"<c:if test="${routesPage.pageSize==40 }">selected</c:if>>40</option>
			</select> <span>条，共${routesPage.totalCount}条
		</div>

		<div class="pagination" targetType="navTab" totalCount="${routesPage.totalCount}"
			numPerPage="${routesPage.pageSize}" pageNumShown="5" currentPage="${routesPage.pageNum}"></div>

	</div>
</div>