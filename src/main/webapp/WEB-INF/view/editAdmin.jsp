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
	<form method="post" action="<%=path%>/updateAdmin.action" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${admin.id }"/>
			<p>
				<label>管理员账号：</label>
				<input name="loginname" type="text" size="30" value="${admin.loginname }" readonly="readonly"/>
			</p>
			<p>
				<label>真实姓名：</label>
				<input name="realName" type="text" size="30" value="${admin.realName }"/>
			</p>
			<p>
				<label>手机号：</label>
				<input name="telephone" type="text" size="30" value="${admin.telephone }"/>
			</p>
			<!-- <p>
				<label>真实姓名：</label>
				<input id="w_validation_pwd" name="password" class="required" type="password" size="30"/>
				
			</p>
			<p>
				<label>确认密码：</label>
				<input equalto="#w_validation_pwd" name="repassword" class="required" type="password" size="30" />
			</p> -->
			<p>
				<label>管理员状态：</label>
				<select name="status" class="required combox">
					<option value="">请选择</option>
					<option value="1"<c:if test="${admin.status==1 }">selected</c:if>>正常</option>
					<option value="2"<c:if test="${admin.status==2 }">selected</c:if>>禁用</option>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
