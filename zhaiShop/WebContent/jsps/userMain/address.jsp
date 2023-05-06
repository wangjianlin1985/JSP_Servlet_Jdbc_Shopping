<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="center_right" style="display: block;">

	<div class="red" style="clear:both; overflow:hidden; padding:10px;">收货地址管理（选中表示为默认）</div>
	<form method="post" class="person-add" name="form"
		action="/index.php?s=/Home/Address/update.html">
		<ul style="margin-left:40px;" class="list">
			<c:forEach items="${addressList }" var="address">
				<li id="d${address.id }">
					<span><input type="radio" name="id" value="${address.id }" <c:if test="${address.msg==1 }">checked="checked"</c:if>></span>
					${address.province } ${address.city } ${address.area }${address.address } 收货人：${address.username }  手机号：${address.phone }
				</li>
			</c:forEach>
			<li>
				<input type="button" class="btn-xiugai redd" value="设置默认" onclick="shezhi();">&nbsp;
				<input type="button" class="btn-xiugai blue" value="删除" onclick="shanchu();">&nbsp;
				<a href="addAddress.page" class="btn-xiugai orange">增加</a>
			</li>
			<br>
		</ul>
	</form>
	<script type="text/javascript">
		//设置默认
		function shezhi() {
			var val = $('input:radio[name="id"]:checked').val();
			//判断新地址是否选中,获取地址id
			if (val == null) {
				alert("请选择一个地址!");return false;
			}else{
				$.ajax({
					type : 'post', //传送的方式,get/post
					url : 'setDefault.address', //发送数据的地址
					data : {
						id : val
					},
					dataType : "json",
					success : function(data) {
						if(data.success){
							layer.alert("设置成功！",{icon:1});
						}else{
							layer.alert("设置失败！",{icon:2});
						}
					},
					error : function(event, XMLHttpRequest, ajaxOptions,
							thrownError) {
						alert(XMLHttpRequest + thrownError);
				}});}}
		//删除地址
		function shanchu() {

			var val = $('input:radio[name="id"]:checked').val();
			//判断新地址是否选中,获取地址id
			if (val == null) {

				alert("请选择一个地址!");return false;
		}else{
			$.ajax({
					type : 'post', //传送的方式,get/post
					url : 'delete.address', //发送数据的地址
					data : {
						id : val
					},
					dataType : "json",
					success : function(data) {
						if(data.success)
						{
						$('#d'+val).remove();
						layer.alert("已删除该条地址！",{icon:1},function(index){
						    //do something
						    layer.close(index);
							window.location.href="userAddress.page";
						});
							
						}
					}
				})
			}
		}
	</script>
	<!-- 个人中心 初始状态 end -->
</div>