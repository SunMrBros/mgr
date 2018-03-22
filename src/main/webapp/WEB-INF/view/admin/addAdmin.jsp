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
	<form method="post" action="<%=path%>/addAdmin.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>子管理员账号：</label>
				<input name="loginname" type="text" size="30" class="required" alt="登录账号" remote="<%=path%>/isExist.action"/>
			</p>
			<p>
				<label>子管理员密码：</label>
				<input id="w_validation_pwd" name="password" type="password" size="30" minlength="6" maxlength="20" class="required alphanumeric textInput" alt="字母、数字、下划线 6-20位" />
			</p>
			<p>
				<label>确认密码：</label>
				<input type="password" size="30" class="required textInput" equalto="#w_validation_pwd" minlength="6" maxlength="20"/>
			</p>
			<p>
				<label>真实姓名：</label>
				<input name="realName" class="required" type="text" size="30"  alt="真实姓名"/>
			</p>
			<p>
				<label>手机号：</label>
				<input name="telephone" class="required" type="text" size="30"  alt="输入手机号"/>
			</p>
			<p>
				<label>管理员状态：</label>
				<select name="status" class="required combox">
					<option value="">请选择</option>
					<option value="1">正常</option>
					<option value="0">禁用</option>
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
