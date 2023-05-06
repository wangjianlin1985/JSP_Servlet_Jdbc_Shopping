<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/select2Address/js/area.js"></script>
<script type="text/javascript" src="js/select2Address/js/location.js"></script>
<script type="text/javascript" src="js/select2Address/js/select2.js"></script>
<script type="text/javascript" src="js/select2Address/js/select2_locale_zh-CN.js"></script>
<link href="js/select2Address/css/select2.css" rel="stylesheet"/>
<div class="center_right" style="display: block;">
	<div class="red" style="clear:both; overflow:hidden; padding:10px;">收货地址管理（选中表示为默认）</div>
	<form method="post" class="person-add" name="form">
		<div class="form-item">
			<select id="loc_province" style="width:120px;"></select>
		    <select id="loc_city" style="width:120px; margin-left: 10px"></select>
		    <select id="loc_town" style="width:120px;margin-left: 10px"></select>
		</div>
		<div class="form-item">
			详细地址:<input id="address" name="address" type="text">
		</div>
		<div class="form-item">
			联系人：<input name="realname" id="realname" type="text">
		</div>
		<div class="form-item">
			手机号：<input id="phone" name="cellphone" type="text">
		</div>
		<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="isdefault" checked="checked" name="default" type="checkbox">设为默认收货地址
		<div class="form-item">
			<input class=" btn-xiugai redd" id="btn-save" value="保存"
				type="button">
		</div>
	</form>
	<script type="text/javascript">
		$('#btn-save').click(
			function (){
				var province = $('#loc_province').select2('data').text; //省
				var city = $('#loc_city').select2('data').text; //市
				var area = $('#loc_town').select2('data').text; //县
				var posi = $('#address').val();
				var pho = $('#phone').val();
				var rel = $('#realname').val();
				
				var msg = 0;
				
				if(province=='省份'||city=='地级市'||area=='市、县、区'){
					layer.alert("地址请填写完整",{icon:0});
				}else{
					if($("#isdefault").is(':checked')){
						msg = 1;
					}	
				$.ajax({
					type : 'post', //传送的方式,get/post
					url : 'add.address', //发送数据的地址
					data : {
						province : province,
						city : city,
						area : area,
						posi : posi,
						pho : pho,
						rel : rel,
						msg : msg
					},
					dataType : "json",
					success : function(data) {
						if(data.success){
							layer.alert("添加成功！",{icon:1},function(index){
							    //do something
							    layer.close(index);
								location.href='userAddress.page';
							});
						}else{
							layer.alert("添加失败！",{icon:2});
						}
					},
					error : function(event, XMLHttpRequest, ajaxOptions,
							thrownError) {
						alert(XMLHttpRequest + thrownError);
				}});
				}
				
				
				//alert(province+city+area);
			}		
		);
	</script>
</div>
