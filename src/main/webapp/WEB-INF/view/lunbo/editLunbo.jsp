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
		action="<%=path%>/lunbo/updateLunbo.action"
		class="pageForm required-validate"
		onsubmit="return iframeCallback(this,dialogAjaxDone);" >
		<div class="pageFormContent" layoutH="56">
			<p>
				<input type="hidden" name="lunboId" value="${lunbo.id }"/>
				<label>标题：</label> <input name="title" type="text" size="30"
					class="required" value="${lunbo.title }"/>
			</p>
			<p>
				<label>图片：</label>
				<img height="80" width="150" src="${lunbo.picurl }"/>
			</p>
			<p></p>
			<p>
				<label>修改图片：</label>
				<div class="upload-wrap">
					<input id="testFileInput" type="file" name="file" />
				</div>
			</p>
			<p>
				<label>轮播图序号：</label> <input name="sortNum" class="required"
					type="text" size="30" value="${lunbo.sortNum }" />
			</p>
			<p>
				<label>状态：</label> <select name="status" class="required combox">
					<option value="">请选择</option>
					<option value="1" <c:if test="${lunbo.status==1 }"> selected="selected"</c:if>>正常</option>
					<option value="0" <c:if test="${lunbo.status==0 }"> selected="selected"</c:if>>禁用</option>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">更新</button>
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
