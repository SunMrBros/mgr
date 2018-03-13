
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
<form id="pagerForm" method="post" action="<%=path%>/customer/getCustomers.action">
	<input type="hidden" name="pageNum" value="1"/>
	<input type="hidden"name="numPerPage"value="${customerPage.pageSize }"/>
	<input type="hidden" name="username" value="${queryValue.username}"/>
	<input type="hidden" name="startDate" value="<fmt:formatDate value="${queryValue.startDate }" pattern="yyyy-MM-dd"/>" />
	<input type="hidden" name="endDate" value="<fmt:formatDate value="${queryValue.endDate }" pattern="yyyy-MM-dd"/>" />
	<input type="hidden" name="telephone" value="${queryValue.telephone }"/>
	<input type="hidden" name="identifyid" value="${queryValue.identifyid }"/>
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path %>/customer/getCustomers.action" method="post" onreset="$(this).find('select.combox').comboxReset()">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户姓名：<input type="text" name="username" value="${queryValue.username }"/>
				</td>
				<td>
					手机号：<input type="text" name="telephone" value="${queryValue.telephone }"/>
				</td>
				<td>
					身份证号码：<input type="text" name="identifyid"  value="${queryValue.identifyid }"/>
				</td>
				<td class="dateRange">
					用户建档时间:
					<input name="startDate"
						class="date readonly" readonly="readonly" type="text"
						value="<fmt:formatDate value="${queryValue.startDate }" pattern="yyyy-MM-dd"/>">
						<span class="limit">-</span> <input name="endDate"
						class="date readonly" readonly="readonly" type="text"
						value="<fmt:formatDate value="${queryValue.endDate }" pattern="yyyy-MM-dd"/>">
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
		<li><a class="delete" href="<%=path %>/customer/delCustomer.action?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">序号</th>
				<th width="120">用户姓名</th>
				<th width="100">手机号码</th>
				<th width="100">身份证号码</th>
				<th width="150">创建时间</th>
				<th width="80" align="center">用户认证状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${customerPage.results }" var="customer">
			
			<tr target="sid_user" rel="${customer.id }">
				<td>${customer.id }</td>
				<td>${customer.username }</td>
				<td>${customer.telephone }</td>
				<td>${customer.identifyid }</td>
				<td>${customer.createtime }</td>
				<td><c:if test="${customer.status ==1}">已经认证</c:if><c:if test="${customer.status ==2}">等待认证</c:if><c:if test="${customer.status ==3}">用户已经被删除</c:if></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"<c:if test="${customerPage.pageSize==10 }">selected</c:if>>10</option>
				<option value="20"<c:if test="${customerPage.pageSize==20 }">selected</c:if>>20</option>
				<option value="30"<c:if test="${customerPage.pageSize==30 }">selected</c:if>>30</option>
				<option value="40"<c:if test="${customerPage.pageSize==40 }">selected</c:if>>40</option>
			</select> <span>条，共${customerPage.totalCount}条
		</div>

		<div class="pagination" targetType="navTab" totalCount="${customerPage.totalCount}"
			numPerPage="${customerPage.pageSize}" pageNumShown="5" currentPage="${customerPage.pageNum}"></div>

	</div>
</div>