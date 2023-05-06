<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/laydate/laydate.js"></script>
<div class="center_right" id="MemberCenterCtrl" style="display: block;">
	<h3 class="cr_h3 cr_h3_gray">个人资料</h3>
	<form method="post" id="fro" class="person-info" name="form" action="userDataUpdate.user">		
		<ul style="margin-left:40px;">
			<li><span>真实姓名:</span><input name="trueName" value="${userBean.trueName }" type="text"> </li>
			<li><span>性别：</span><select name="sex"><option <c:if test="${userBean.sex=='男' }">selected = "selected"</c:if>>男</option><option <c:if test="${userBean.sex=='女' }">selected = "selected"</c:if>>女</option></select></li>
			<li><span>账号:</span>${userBean.userName }</li>
			<li><span>生日：</span><input type="text" name="birthday" value="${userBean.birthday }" placeholder="请输入日期" class="laydate-icon" onclick="laydate()"></li>
			<li><span>身份证号:</span><input name="statusID" value="${userBean.statusID }" type="text"></li>
			<li><span>手机号:</span><input name="phone" value="${userBean.phone }" type="text"></li>
			<li><span>邮箱:</span><input name="email" value="${userBean.email }" type="text"></li>
			<li><input name="uid" value="${userBean.id }" type="hidden"> </li><br>
			<li><input class="btn-xiugai orange" value="修改" type="button" onclick="tijiao()"> </li><br>
		</ul> 
	</form>
	<script type="text/javascript">
		function tijiao(){
			$.ajax({
				type:'post', //传送的方式,get/post
				url:'userDataUpdate.user', //发送数据的地址
				data:$('#fro').serialize(),
				 dataType: "json",
				success:function(data){
					//alert()
					if(data.success){
						layer.alert('修改成功',{icon:1,shift:4});
					}else{
						layer.alert('修改失败',{icon:2,shift:4});
					}
					
				}});
		}
	</script>
	<style>
		.layui-layer-btn .layui-layer-btn0{
			border-color: #f60;
		    background-color: #f40;
		    color: #fff;
		}
	</style>
</div>