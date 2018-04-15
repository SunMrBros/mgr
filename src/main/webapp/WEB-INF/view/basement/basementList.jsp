
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
<form id="pagerForm" method="post" action="<%=path%>/basement/getBaseList.action">
	<input type="hidden" name="pageNum" value="1"/>
	<input type="hidden"name="numPerPage"value="${basePage.pageSize }"/>
	<input type="hidden" name="title" value="${webVo.title}"/>
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path%>/basement/getBasePage.action"
		method="post" onreset="$(this).find('select.combox').comboxReset()">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>标题：<input type="text" name="title"
						value="${webVo.title }" />
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
			<li><a class="add" href="<%=path %>/basement/toAddBase.action?province=${webVo.province}" target="dialog" width="650" height="680"  rel="add_base"><span>添加基地信息</span></a></li>
			<li><a class="delete" href="<%=path %>/basement/delBase.action?basementId={base_id}" target="ajaxTodo" title="确定要删除吗?"><span>删除基地信息</span></a></li>
			<li><a class="edit" href="<%=path %>/basement/toEditBasement.action?basementId={base_id}" width="650" height="680"  target="dialog" rel="edit_base"><span>修改基地信息</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="30" align="center">序号</th>
				<th width="50" align="center">名称</th>
				<th width="50" align="center">管理员</th>
				<th width="80" align="center">电话</th>
				<th width="50" align="center">城市</th>
				<th width="50" align="center">地区</th>
				<th width="30" align="center">排序</th>
				<th width="50" align="center">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${basePage.results }" var="base" varStatus="index">
				<tr target="base_id" rel="${base.id }">
					<td>${(basePage.pageNum-1)*basePage.pageSize+index.index+1 }</td>
					<td>${base.title }</td>
					<td>${base.admin.realName }</td>
					<td>${base.telephone }</td>
					<td>${base.city }</td>
					<td>${base.area }</td>
					<td>${base.sortNum }</td>
					<td><c:if test="${base.status==1 }">有效</c:if><c:if test="${base.status==0 }">无效</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10"<c:if test="${basePage.pageSize==10 }">selected</c:if>>10</option>
				<option value="20"<c:if test="${basePage.pageSize==20 }">selected</c:if>>20</option>
				<option value="30"<c:if test="${basePage.pageSize==30 }">selected</c:if>>30</option>
				<option value="40"<c:if test="${basePage.pageSize==40 }">selected</c:if>>40</option>
			</select> <span>条，共${basePage.totalCount}条
		</div>

		<div class="pagination" targetType="navTab" totalCount="${basePage.totalCount}"
			numPerPage="${basePage.pageSize}" pageNumShown="5" currentPage="${basePage.pageNum}"></div>

	</div>
</div>