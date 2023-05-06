<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
.control-group input{
    width:260px;
    height:25px;
}
</style>
<div class="center_right" id="MemberCenterCtrl" style="display: block;">
	<h3 class="cr_h3 cr_h3_gray">修改密码</h3>
	<form id="fro" class="form-horizontal" role="form" action="/index.php?s=/Home/user/profile.html" method="post" style="width: 60%; margin: 0 auto;">
          <div class="control-group">
            <label class="control-label" >原密码</label>
            <div class="controls">
              <input type="password"  class="span3" placeholder="请输入原密码"  name="old">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" >新密码</label>
            <div class="controls">
              <input type="password"  class="span3" placeholder="请输入密码"  name="password">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" >确认密码</label>
            <div class="controls">
              <input type="password"  class="span3" placeholder="请再次输入密码">
            </div>
            <div class="controls Validform_checktip text-warning"></div>
          </div>
          <div class="control-group">
            <div class="controls">
              <button id="bu" type="button" class="v_dark_btn">提 交</button>
            </div>
          </div>
        </form>
        <script type="text/javascript">
        	$('#bu').click(
        		function (){
        			var p1 = $(':password:eq(1)').val();
        			var p2 = $(':password:eq(2)').val();
        			if(p1.length>20||p1.length<6){
        				layer.alert('请输入6位以上20位以下新密码！',{icon:0});
        				return;
        			}
        			if($(':password:eq(1)').val()!=$(':password:eq(2)').val()){
        				layer.alert('两次密码输入不一致',{icon:2});
        				return;
        			}
        			 $.ajax({
        				type:'post', //传送的方式,get/post
        				url:'changePassword.user', //发送数据的地址
        				data:$('#fro').serialize(),
        				 dataType: "json",
        				success:function(data){
        					//alert()
        					if(data.success){
        						layer.alert('修改成功,下次登录生效！',{icon:1});
        					}else{
        						layer.alert('修改失败！',{icon:2});
        					}
        					
        				}}); 
        		}		
        	);
        </script>
</div>