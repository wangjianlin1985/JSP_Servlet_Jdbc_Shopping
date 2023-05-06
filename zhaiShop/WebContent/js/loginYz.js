nameisyz = false; //用户名验证
yzmisyz = false; //验证码验证
mmyz = false; //密码验证
window.onload=function(){
	yhmzz = /^[a-zA-z][a-zA-Z0-9_]{3,15}$/; //用户名正则
	inputusername.onblur=function(){
		var username=inputusername.value; //昵称
		if(!yhmzz.exec(username)){
			usernameYz.innerHTML="<font color='red' size='-1'>格式有误</font>";
			inputusername.style.border="1px solid red";
			nameisyz = false;
			return;
		}
		$.ajax({
		type:'post',
		url:'http://localhost:8080/zhaiShop/rege.user', //发送数据的地址
		 data:{
			name:username
		 },
		 dataType: "json",
		success:function(result){
			if(result.success=="false"){
					usernameYz.innerHTML="";
					inputusername.style.border="1px solid #EEE";
					nameisyz = true;
				}else{
					usernameYz.innerHTML="<font color='red' size='-1'>用户名不存在</font>";
					inputusername.style.border="1px solid red";
					nameisyz = false;
				}
		}
		});
	}
	yzm.onblur=function(){
		yz = yzm.value;
		$.ajax({
			type:'post',
			url:'http://localhost:8080/zhaiShop/yzm.user', //发送数据的地址
			 data:{jyzm:yz},
			 dataType: "json",
			success:function(result){
				if(result.yzm=="false"){
					tyzm.innerHTML="<font color='red' size='-1'>验证码输入有误</font>";
					yzm.style.border="1px solid red";
					changeImage();//验证码输入错误刷新图片
					yzmisyz = false;
				}else{
					tyzm.innerHTML="<font color='green' size='-1'>ok</font>";
					yzm.style.border="1px solid #EEE";
					yzmisyz = true;
				}
			}});
	}
	inputpassword.onblur=function(){
		if(inputpassword.value!=""){
			mm.innerHTML="";
			inputpassword.style.border="1px solid #EEE";
			mmyz = true;
		}else{
			mm.innerHTML="<font color='red' size='-1'>请输入密码</font>";
			inputpassword.style.border="1px solid red";
			mmyz = false;
		}
	}
}
	
function bdtj(){
	if(nameisyz && yzmisyz && mmyz){
		frm.submit(); //验证通过  提交
	}else{
		alert("请填写完整");
	}
}