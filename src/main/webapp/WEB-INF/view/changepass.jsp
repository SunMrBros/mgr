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
<div class="pageContent">
	<form method="post" action="<%=path%>/changepass.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${webValues.id }">
			<p>
				<label>旧密码：</label>
				<input name="oldpassword" type="text" size="30" class="required" remote="<%=path%>/isOldpasswordright.action?id=${webValues.id}"/>
			</p>
			<p>
				<label>新密码：</label>
				<input id="w_validation_pwd" name="password" type="password" size="30" minlength="5" maxlength="20" class="required alphanumeric textInput" alt="字母、数字、下划线 5-20位" />
			</p>
			<p>
				<label>确认新密码：</label>
				<input type="password" size="30" class="required textInput" equalto="#w_validation_pwd" minlength="5" maxlength="20"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">修改</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
