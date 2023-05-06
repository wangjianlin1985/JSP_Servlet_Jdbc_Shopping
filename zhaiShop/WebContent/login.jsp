<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/common.css" rel="stylesheet"> 
  <link href="css/top.css" rel="stylesheet"> 
  <link href="css/footer.css" rel="stylesheet"> 
  <script type="text/javascript" src="js/jquery.js"></script> 
  <script type="text/javascript" src="js/public.js"></script>
  <script type="text/javascript" src="js/loginYz.js"></script>
  </head>
  <body>
  <script type="text/javascript">
  	var zt = "${regeState }";
  	if(zt){
  		alert("恭喜您注册成功，来登录吧");
  	}
  </script>
  <!-- 工具条 --> 
  <!-- 顶部工具条 --> 
  <div class="top"> 
   <div class="topbar"> 
    <span class="welcome" style="float:left">欢迎光临宅商城系统 ,请<a href="login.jsp" class="red"> 登录</a>&nbsp;<a href="rege.jsp" style="padding-left:0;padding-right:10px"> 免费注册 </a> </span> 
    <span class="operate_nav"> <span id="account" style="background-color: rgb(245, 245, 245);"><a rel="nofollow">我的账号&nbsp;</a><i id="icount" class="fa fa-angle-down"></i> </span> 
     <ul id="dbox" class="top_lg_info_down" style="display: none;"> 
      <li><a rel="nofollow" href="userMain.page">个人中心</a></li>  
     </ul> |</span> 
    <span class="operate_nav"> <a rel="nofollow" href="">我的订单</a> | </span> 
    <span class="operate_nav"> <a href="index.cart" name="购物车" dd_name="购物车"><i class="icon_card"></i>购物车<b id="cart_items_count"></b></a> </span> 
   </div> 
  </div>
  <script type="text/javascript">
//头部topbar会员中心显示与隐藏
var Account= document.getElementById('account');
            var Downmenu= document.getElementById('dbox');
            var timer = null;//定义定时器变量
            //鼠标移入div1或div2都把定时器关闭了，不让他消失
            Account.onmouseover = Downmenu.onmouseover = function ()
            {
				 //改变箭头方向
				$("i#icount").attr("class","fa fa-angle-up");
               
				 //改变背景颜色
				 Account.style.backgroundColor = '#fff';
				 //显示下拉框
                $("#dbox").show();
				//关闭定时执行
                clearTimeout(timer);
            }
			
            //鼠标移出div1或div2都重新开定时器，让他延时消失
            Account.onmouseout = Downmenu.onmouseout = function ()
            {
				$("i#icount").attr("class","fa fa-angle-down");
				Account.style.backgroundColor = '#F5F5F5';
				 //开定时器，每隔200微妙下拉框消失
                timer = setTimeout(function () { 
                    $("#dbox").hide(); }, 200);
            }
       
		
		</script> 
  <!-- 顶部工具条 结束 --> 
  <!-- /工具条 --> 
  <!-- 头部 --> 
  <div class="wrapper-cart"> 
   <!-- 导航条 --> 
   <div class="header-wrap">
    <a href="" class="logo" title="宅商城系统"><img src="images/logo.png" alt="宅商城系统" /></a> 
    <div class=""> 
    </div> 
   </div> 
   <!-- /头部 --> 
   <!-- 主体 --> 
   <div class="wrapper-order"> 
    <div class="mainwrap"> 
     <div class="think-form"> 
      <div class="loginform"> 
       <div class="hd"> 
        <strong>用户登录</strong>
        <span>已有帐号？点击<a href="rege.jsp">注册</a></span> 
       </div> 
       <a rel="nofollow" href="" class="img"><img src="images/login-3.jpg" width="400" height="300" /></a>
      </div>
      <div class="login-other"> 
       <form name="frm" class="form-horizontal login-user" action="mainLogin.user" id="loginform" name="user" method="post"> 
        <div class="control-group "> 
         <label class="control-label" for="username">用户名</label> 
         <div class="controls"> 
          <em class="user glyphicon glyphicon-user"></em>
          <input type="text" class="v_inp" id="inputusername" name="username" value="${sessionScope.loginFail }" /> 
          <label id="usernameYz"></label> 
         </div> 
        </div> 
        <div class="control-group"> 
         <label class="control-label" for="inputPassword">密码</label> 
         <div class="controls"> 
          <em class="user glyphicon glyphicon-lock"></em> 
          <input type="password" class="v_inp" id="inputpassword" name="password" /> 
          <label id="mm"></label> 
          <c:if test="${sessionScope.loginFail !=null }">
       		 <script type="text/javascript">
       			nameisyz=true; //让昵称验证通过
       			mm.innerHTML="<font color='red' size='-1'>密码输入有误</font>";
       			inputpassword.style.border="1px solid red";
          	 </script>
		  </c:if>
		 <c:remove var="loginFail" scope="session"/>
         </div> 
         <div class="controls Validform_checktip text-warning"></div> 
        </div> 
        <div class="control-group"> 
          <label class="control-label" for="inputEmail">验证码</label> 
          <div class="controls"> 
          <img id="CreateCheckCode" src="genImage.yzm">
			<a href="javascript:;" onclick="changeImage()"> 看不清</a>
           <input id="yzm" class="v_inp" placeholder="请输入验证码" name="yzm" type="text"> 
           <label id="tyzm"></label>
          </div> 
          <div class="controls Validform_checktip text-warning"></div> 
         </div>
        <input name="reffer" type="hidden" value="" /> 
        <div class="control-group"> 
         <div class="controls"> 
          <!-- <label class="checkbox"> <input type="checkbox" name="remember" /> 自动登陆 </label>  -->
          <br />
          <button type="button" id="login_btn_cart" class="v_dark_btn" onclick="bdtj()">登 陆</button> 
         </div> 
        </div> 
       </form> 
      </div> 
     </div> 
    </div> 
    <!-- /主体 --> 
    <!-- 底部 --> 
    <!-- 底部--> 
   </div>
  </div> 
 <%@ include file="jsps/footer.jsp" %>  
  <!-- /底部 --> 
  </body>
</html>
