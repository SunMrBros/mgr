
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
<form id="pagerForm" method="post" action="<%=path%>/column/getColumnPage.action">
	<input type="hidden" name="pageNum" value="1"/>
	<input type="hidden"name="numPerPage" value="${columnPage.pageSize }"/>
	<input type="hidden" name="columnName" value="${webValues.columnName}"/>
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/column/getColumnPage.action"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>栏目名称：<input type="text" name="columnName" value="${webValues.columnName }"/>
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
			<li><a class="add" href="<%=path %>/column/toAddColumn.action" target="dialog" rel="add_column"><span>添加栏目信息</span></a></li>
			<li><a class="delete" href="<%=path %>/column/delColumn.action?columnId={column_id}" target="ajaxTodo" title="确定要删除吗?"><span>删除栏目</span></a></li>
			<li><a class="edit" href="<%=path %>/column/toEditColumn.action?columnId={column_id}" target="navTab" rel="edit_column"><span>修改栏目</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80" align="center">序号</th>
				<th width="50" align="center">栏目名称</th>
				<th width="100" align="center">栏目图片</th>
				<th width="50" align="center">排序</th>
				<th width="50" align="center">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${columnPage.results }" var="column">

				<tr target="column_id" rel="${column.id }">
					<td align="center">${column.id }</td>
					<td align="center">${column.columnName }</td>
					<td align="center"><img src="${column.columnPicUrl }" height="80" width="150"/></td>
					<td align="center" width="50">${column.sortNum }</td>
					<td align="center" width="60"><c:if test="${column.status==1 }">正常</c:if><c:if test="${column.status==0}">禁用</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"<c:if test="${columnPage.pageSize==10 }">selected</c:if>>10</option>
				<option value="20"<c:if test="${columnPage.pageSize==20 }">selected</c:if>>20</option>
				<option value="30"<c:if test="${columnPage.pageSize==30 }">selected</c:if>>30</option>
				<option value="40"<c:if test="${columnPage.pageSize==40 }">selected</c:if>>40</option>
			</select> <span>条，共${columnPage.totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${columnPage.totalCount}" numPerPage="${columnPage.pageSize}" pageNumShown="5" currentPage="${columnPage.pageNum}"></div>

	</div>
</div>