<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="pageContent">
	<form enctype="multipart/form-data" method="post"
		action="<%=path%>/route/updateRoute.action"
		class="pageForm required-validate"
		onsubmit="return iframeCallback(this,dialogAjaxDone);" >
		<div class="pageFormContent" layoutH="56">
			<%-- <input name="id" type="hidden" value="${route.id }"/> --%>
			<input name="routeId" type="hidden" value="${route.id }"/>
			<p>
				<label>线路标题：</label> <input name="title" type="text" size="30"
					class="required" value=${route.title } />
			</p>
				<p>
					<label>图片：</label>
					<img height="80" width="150" src="${route.basePic1 }"/>
				</p>
				<p>
				<label>基地图片：</label>
				<div class="upload-wrap">
					<input id="testFileInput" type="file" name="file1" />
				</div>
				<p>
					<label>图片：</label>
					<img height="80" width="150" src="${route.basePic2 }"/>
				</p>
				<p>
				<label>基地图片：</label>
				<div class="upload-wrap">
					<input id="testFileInput" type="file" name="file2" />
				</div>
			<div class="unit">
				<textarea name="routeDesc" class="editor" name="description" rows="20" cols="98">${route.routeDesc }</textarea>
			</div>
			<p>
				<label>栏目：</label> 
				<select name="columnId" class="required combox">
					<option value="">请选择</option>
						<c:forEach items="${columns }" var="column">
							<option value="${column.id }" <c:if test="${route.column.id==column.id }">selected="selected"</c:if>>${column.columnName }</option>
						</c:forEach>
				</select>
			</p>
			<p>
				<label>序号：</label> <input name="sortNum" class="required"
					type="text" size="30" value="${route.sortNum }" />
			</p>
			<p>
				<label>状态：</label> <select name="status" class="required combox">
					<option value="">请选择</option>
					<option value="1" <c:if test="${route.status==1}">selected="selected"</c:if>>正常</option>
					<option value="0" <c:if test="${route.status==0}">selected="selected"</c:if>>禁用</option>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">更新修改</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
