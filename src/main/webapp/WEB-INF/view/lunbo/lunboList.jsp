
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
<form id="pagerForm" method="post" action="<%=path%>/lunbo/getLunboPage.action">
	<input type="hidden" name="pageNum" value="1"/>
	<input type="hidden"name="numPerPage" value="${lunboPage.pageSize }"/>
	<input type="hidden" name="lunboName" value="${webValues.lunboName}"/>
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/lunbo/getLunboPage.action"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>标题：<input type="text" name="title" value="${webValues.title }"/>
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
			<li><a class="add" href="<%=path %>/lunbo/toAddLunbo.action" target="dialog" rel="add_lunbo"><span>添加轮播图</span></a></li>
			<li><a class="delete" href="<%=path %>/lunbo/delLunbo.action?lunboId={lunbo_id}" target="ajaxTodo" title="确定要删除吗?"><span>删除轮播图</span></a></li>
			<li><a class="edit" href="<%=path %>/lunbo/toEditLunbo.action?lunboId={lunbo_id}" height="350" target="dialog" rel="edit_lunbo"><span>修改轮播图</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80" align="center">序号</th>
				<th width="50" align="center">图片</th>
				<th width="100" align="center">标题</th>
				<th width="50" align="center">链接地址</th>
				<th width="50" align="center">排序</th>
				<th width="50" align="center">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${lunboPage.results }" var="lunbo" varStatus="index">

				<tr target="lunbo_id" rel="${lunbo.id }">
					<td align="center">${(lunboPage.pageNum-1)*pageSize+index.index+1 }</td>
					<td align="center"><img src="${lunbo.picurl }" height="80" width="150"/></td>
					<td align="center">${lunbo.title }</td>
					<td align="center" width="50">${lunbo.url }</td>
					<td align="center" width="50">${lunbo.sortNum}</td>
					<td align="center" width="60"><c:if test="${lunbo.status==1 }">启用</c:if><c:if test="${lunbo.status==0}">禁用</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"<c:if test="${lunboPage.pageSize==10 }">selected</c:if>>10</option>
				<option value="20"<c:if test="${lunboPage.pageSize==20 }">selected</c:if>>20</option>
				<option value="30"<c:if test="${lunboPage.pageSize==30 }">selected</c:if>>30</option>
				<option value="40"<c:if test="${lunboPage.pageSize==40 }">selected</c:if>>40</option>
			</select> <span>条，共${lunboPage.totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${lunboPage.totalCount}" numPerPage="${lunboPage.pageSize}" pageNumShown="5" currentPage="${lunboPage.pageNum}"></div>

	</div>
</div>