
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
<form id="pagerForm" method="post" action="<%=path%>/getAdmins.action">
	<input type="hidden" name="pageNum" value="1"/>
	<input type="hidden"name="numPerPage"value="${adminPage.pageSize }"/>
	<input type="hidden" name="username" value="${webValues.realName}"/>
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path %>/getAdmins.action" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					管理员姓名：<input type="text" name="realName" value="${webValues.realName }"/>
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
			<li><a class="add" href="<%=path %>/toAddAdmin.action" target="dialog" rel="add_user"><span>添加</span></a></li>
			<li><a class="delete" href="<%=path %>/deleteAdmin.action?id={sid_admin}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="<%=path %>/toEditAdmin.action?id={sid_admin}" target="navTab" rel="edit_user"><span>修改</span></a></li>
			<li><a class="edit" href="<%=path %>/tochangepass.action?id={sid_admin}" target="dialog" rel="edit_userpass"><span>修改密码</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80" align="center">ID</th>
				<th width="120" align="center">账号</th>
				<th width="100" align="center">真实姓名</th>
				<th width="100" align="center">手机</th>
				<th width="80" align="center">管理员状态</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adminPage.results }" var="admin">
					<tr target="sid_admin" rel="${admin.id }" align="center">
					<td align="center">${admin.id }</td>
					<td align="center">${admin.loginname }</td>
					<td align="center">${admin.realName }</td>
					<td align="center">${admin.telephone }</td>
					<td align="center"><c:if test="${admin.status==0 }">禁用</c:if><c:if test="${admin.status==1 }">正常</c:if><c:if test="${admin.status==2 }">删除</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"<c:if test="${adminPage.pageSize==10 }">selected</c:if>>10</option>
				<option value="20"<c:if test="${adminPage.pageSize==20 }">selected</c:if>>20</option>
				<option value="30"<c:if test="${adminPage.pageSize==30 }">selected</c:if>>30</option>
				<option value="40"<c:if test="${adminPage.pageSize==40 }">selected</c:if>>40</option>
			</select> <span>条，共${adminPage.totalCount}条
		</div>

		<div class="pagination" targetType="navTab" totalCount="${adminPage.totalCount}"
			numPerPage="${adminPage.pageSize}" pageNumShown="5" currentPage="${adminPage.pageNum}"></div>

	</div>
</div>