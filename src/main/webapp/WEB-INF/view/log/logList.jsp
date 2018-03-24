
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
<form id="pagerForm" method="post" action="<%=path%>/getLogPage.action">
	<input type="hidden" name="pageNum" value="1"/>
	<input type="hidden"name="numPerPage"value="${logPage.pageSize }"/>
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/getLogPage.action"
		method="post" onreset="$(this).find('select.combox').comboxReset()">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<%-- <td>订单编号：<input type="text" name="orderId"
						value="<c:if test="${queryValue.orderId!=0 }">${queryValue.orderId }</c:if>" />
					</td> --%>
					<td>客户姓名：<input type="text" name="username"
						value="${queryValue.username }" />
					</td>
					<td>
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
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
			<th width="30" align="center">序号</th>
				<th width="50" align="center">登录名</th>
				<th width="50" align="center">模块</th>
				<th width="150" align="center">操作内容</th>
				<th width="80" align="center">操作时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${logPage.results }" var="log" varStatus="index">

				<tr>	
					<td align="center">${(logPage.pageNum-1)*logPage.pageSize+index.index+1 }</td>
					<td>${log.admin.loginname }</td>
					<td>${log.model }</td>
					<td>${log.operator }</td>
					<td>${log.opdate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"<c:if test="${logPage.pageSize==10 }">selected</c:if>>10</option>
				<option value="20"<c:if test="${logPage.pageSize==20 }">selected</c:if>>20</option>
				<option value="30"<c:if test="${logPage.pageSize==30 }">selected</c:if>>30</option>
				<option value="40"<c:if test="${logPage.pageSize==40 }">selected</c:if>>40</option>
			</select> <span>条，共${logPage.totalCount}条
		</div>

		<div class="pagination" targetType="navTab" totalCount="${logPage.totalCount}"
			numPerPage="${logPage.pageSize}" pageNumShown="5" currentPage="${logPage.pageNum}"></div>

	</div>
</div>