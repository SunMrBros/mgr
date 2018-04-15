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
<!-- <script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=erX4rnXqpHS3yMqfC0RrWowanRdnmwop"></script>

<script type="text/javascript">
	var map = new BMap.Map("container");
	map.enableScrollWheelZoom(true); 
	map.addControl(new BMap.NavigationControl());
	// 创建地图实例  
	var point = new BMap.Point(116.404, 39.915);
	// 创建点坐标  
	map.centerAndZoom(point, 15);
	// 初始化地图，设置中心点坐标和地图级别  
	window.setTimeout(function(){  
	    map.panTo(new BMap.Point(116.409, 39.918));    
	}, 2000);
	map.addEventListener("click", function(e){    
	    alert(e.point.lng + ", " + e.point.lat); 
	})
	function openmap(){
		$("#container").css('display','block'); 
	}

</script> -->
	<form enctype="multipart/form-data" method="post"
		action="<%=path%>/basement/addBasement.action"
		class="pageForm required-validate"
		onsubmit="return iframeCallback(this,dialogAjaxDone);" >
		<div class="pageFormContent" layoutH="56">
			<div style="height:85px">
				<p>
					<input type="hidden" name="province" value="${webVo.province }"/>
					<label>基地图片：</label>
					<img height="80" width="150" src="${base.columnPicUrl }"/>
				</p>
			</div>
			<div>
				<label>上传图片：</label>
				<div>
					<input id="testFileInput" type="file" name="pic" />
				</div>
			</div>
			<p>
				<label>基地名称：</label> <input name="title" type="text" size="30"
					class="required" />
			</p>
			<p>
				<label>管理员：</label> <input name="base.admin.id" class="required"
					type="text" size="30" value="${admin.realName }"/>
			</p>
			<p>
				<label>联系方式：</label> <input name="telephone" class="required"
					type="text" size="30"/>
			</p>
			<p>
				<label>城市：</label> <select name="cityId" id="city" class="required combox" onchange="getTowns();">
					<option value="">请选择</option>
					<c:forEach items="${citys}" var="city">
						<option value="${city.id }">${city.cityName }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>地区：</label> <select name="townId" id="town" >
					<option value="">请选择</option>
				</select>
			</p>
			<p>
				<label>详细地址：</label> <input name="address" class="required"
					type="text" size="30"/>
			</p>
			<p>
				<div id="BMapcontainer" style="height:200px;width:500px"></div>
			</p>
			<p>
				<label>经纬度：</label> <input name="location" id="bdmap" class="required"
					type="text" size="30"/>
			</p>
			<p>
			<label>内容：</label>
			<div class="unit">
				<textarea name="content" class="editor" rows="20" cols="85"></textarea>
			</div>
			<p>
				<label>音频：</label>
				<audio src="<%=path %>/audio/1.mp3" controls="controls">
				</audio>
				
				<div>
					<input id="testFileInput" type="file" name="audio" />
				</div>
			<p>
				<label>视频：</label>
				<div>
					<video src="<%=path %>/video/movie.ogg" controls="controls">
					</video>
				</div>
				
				<div>
					<input id="testFileInput" type="file" name="video" />
				</div>	
			<p>
				<label>开放时间：</label> 
				<div class="unit">
					<input type="text" name="openTime" class="date" dateFmt="HH:mm" mmStep="15" readonly="true"/>
					<a class="inputDateButton" href="javascript:;">开始时间</a>
				</div><br>
				<div class="unit">
					<input type="text" name="closeTime" class="date" dateFmt="HH:mm" mmStep="15" readonly="true"/>
					<a class="inputDateButton" href="javascript:;">结束时间</a>
				</div>
			<p>
				<label>门票：</label> <input name="ticketPrice" class="required"
					type="text" size="30" />
			</p>
			<p>
				<label>链接地址：</label> <input name="url" class="required"
					type="text" size="30" />
			</p>
			<p>
				<label>排序：</label> <input name="sortNum" class="required"
					type="text" size="30" />
			</p>
			<p>
				<label>是否推荐：</label>
				<label><input type="radio" value="1" name="recommend" />是</label>
				<label><input type="radio" value="0" name="recommend" />不是</label>
			</p>
			<p>
				<label>状态：</label> <select name="status" class="required combox">
					<option value="">请选择</option>
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
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
	<script type="text/javascript">
		function getTowns(){
			var cityId=$("#city").val();
			$.ajax({
				 type: "POST",
	             url: "<%=path%>/basement/getTowns.action",
	             data: "cityId="+cityId,
	           //  dataType: "json",
	             success: function(data){
	            	 $("#town").empty();
	            	 for(var i=0;i<data.length;i++){
	            		 $("#town").append("<option value='"+data[i].id+"'>"+data[i].townName+"</option>"); 
	            	 }
	            	
	             }
			})
		}
	</script>
	<script type="text/javascript">
	var map = new BMap.Map("BMapcontainer");
	map.enableScrollWheelZoom(true); 
	map.addControl(new BMap.NavigationControl());
	// 创建地图实例  
	var point = new BMap.Point(116.404, 39.915);
	// 创建点坐标  
	map.centerAndZoom(point, 15);
	// 初始化地图，设置中心点坐标和地图级别  
	window.setTimeout(function(){  
	    map.panTo(new BMap.Point(116.409, 39.918));    
	}, 2000);
	map.addEventListener("click", function(e){    
	    $('#bdmap').val(e.point.lng + ", " + e.point.lat);
	})
	

</script>
</div>
