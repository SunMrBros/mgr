
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
<form id="pagerForm" method="post" action="<%=path%>/order/getApplyOrderList.action">
	<input type="hidden" name="pageNum" value="1"/>
	<input type="hidden"name="numPerPage"value="${orderPage.pageSize }"/>
	<input type="hidden" name="username" value="${queryValue.username}"/>
	<input type="hidden" name="startDate" value="<fmt:formatDate value="${queryValue.startDate }" pattern="yyyy-MM-dd"/>" />
	<input type="hidden" name="endDate" value="<fmt:formatDate value="${queryValue.endDate }" pattern="yyyy-MM-dd"/>" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/order/getApplyOrderList.action"
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
					<td class="dateRange">申请日期: <input name="startDate"
						class="date readonly" readonly="readonly" type="text" value="<fmt:formatDate value="${queryValue.startDate }" pattern="yyyy-MM-dd"/>">
						<span class="limit">-</span> <input name="endDate"
						class="date readonly" readonly="readonly" type="text" value="<fmt:formatDate value="${queryValue.endDate }" pattern="yyyy-MM-dd"/>">
						
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
			<li><a class="add" href="<%=path %>/order/getOrderDetail.action?orderId={order_id}" target="dialog" rel="applyorder_detail" height="400"><span>详情</span></a></li>
			<li><a class="delete"
				href="<%=path %>/order/delOrder.action?orderId={order_id}" target="ajaxTodo"
				title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">订单编号</th>
				<th width="50" align="center">客户姓名</th>
				<th width="100">联系电话</th>
				<th width="150">证件号码</th>
				<th width="50" align="center">贷款金额</th>
				<th width="50" align="center">分期数</th>
				<th width="80">申请日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orderPage.results }" var="order">

				<tr target="order_id" rel="${order.id }">
					<td>${order.id }</td>
					<td>${order.customer.username }</td>
					<td>${order.customer.telephone }</td>
					<td>${order.customer.identifyid }</td>
					<td>${order.money }</td>
					<td>${order.expect }</td>
					<td>${order.ordertime }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"<c:if test="${orderPage.pageSize==10 }">selected</c:if>>10</option>
				<option value="20"<c:if test="${orderPage.pageSize==20 }">selected</c:if>>20</option>
				<option value="30"<c:if test="${orderPage.pageSize==30 }">selected</c:if>>30</option>
				<option value="40"<c:if test="${orderPage.pageSize==40 }">selected</c:if>>40</option>
			</select> <span>条，共${orderPage.totalCount}条
		</div>

		<div class="pagination" targetType="navTab" totalCount="${orderPage.totalCount}"
			numPerPage="${orderPage.pageSize}" pageNumShown="5" currentPage="${orderPage.pageNum}"></div>

	</div>
</div>