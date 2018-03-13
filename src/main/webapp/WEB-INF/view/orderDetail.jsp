<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
 function formsub(s){
	 
	 var form1=document.getElementById("form1");
	 if(s==1){
		 form1.action="<%=path%>/order/allow.action"; 
	 }else if(s==2){
		 form1.action="<%=path%>/order/deny.action";
	 }
	
	 form1.submit();
	 validateCallback(form1);
 }
</script>
<div class="pageContent">
	<form id="form1" method="post" action=""
		class="pageForm required-validate"
		>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户姓名：</label> <input name="username" type="text" size="30"
					value="${order.customer.username }" readonly="readonly" />
			</p>
			<p>
				<label>联系电话：</label> <input name="telephone" type="text" size="30"
					value="${order.customer.telephone }" readonly="readonly" />
			</p>
			<p>
				<label>贷款金额：</label> <input type="text" readonly="readonly"
					value="${order.money }" name="money" class="textInput">
			</p>
			<p>
				<label>分期数：</label> <input name="expect" type="text" size="30"
					value="${order.expect }" readonly="readonly" />
			</p>
			<p>
				<label>身份证号码：</label> <input name="identifyid" type="text" size="30"
					value="${order.customer.identifyid }" readonly="readonly" />
			</p>
			<p>
				<label>银行卡号：</label> <input name="bankcardid" type="text" size="30"
					value="${order.bankcardid }" readonly="readonly" />
			</p>
			<p>
				<label>所属银行：</label> <input name="bankname" type="text" size="30"
					value="${order.bankname }" readonly="readonly" />
			</p>
			<p>
				<label>担保公司：</label> <input name="guarantee" type="text" size="30"
					value="${order.guarantee }" readonly="readonly" />
			</p>
			<p>
				<label>审核结果：</label> <input name="status" type="text" size="30"
					value="<c:if test="${order.status==2 }">通过</c:if><c:if test="${order.status==3 }">拒绝</c:if><c:if test="${order.status==1 }">等待审核</c:if>"
					readonly="readonly" />
			</p>
		</div>
		<c:if test="${order.status==1 }">
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<a href="<%=path%>/order/allow.action?orderId=${order.id }" target="ajaxTodo" title="审核通过?"><button type="button">同意申请</button></a>
						</div>
					</div></li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<a href="<%=path%>/order/deny.action?orderId=${order.id }" target="ajaxTodo" title="审核拒绝?"><button type="button">拒绝申请</button></a>
						</div>
					</div>
				</li>
			</ul>
		</div>
		</c:if>
	</form>
</div>
