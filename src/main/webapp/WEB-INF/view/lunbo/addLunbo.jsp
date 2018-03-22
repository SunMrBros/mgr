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
		action="<%=path%>/lunbo/addLunbo.action"
		class="pageForm required-validate"
		onsubmit="return iframeCallback(this,dialogAjaxDone);" >
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>标题：</label> <input name="title" type="text" size="30"
					class="required" />
			</p>
			<p>
				<label>上传图片：</label>
				<div class="upload-wrap">
					<input id="testFileInput" type="file" name="file" />
				</div>
			</p>
			<p>
				<label>图片链接地址：</label> <input name="url" class="required"
					type="text" size="30" />
			</p>
			<p>
				<label>轮播图序号：</label> <input name="sortNum" class="required"
					type="text" size="30" />
			</p>
			<p>
				<label>状态：</label> <select name="status" class="required combox">
					<option value="">请选择</option>
					<option value="1">正常</option>
					<option value="0">禁用</option>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
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
