<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String rightPage = (String)request.getAttribute("rightPage");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/top.css" rel="stylesheet" /> 
	<link href="css/detail.css" rel="stylesheet" /> 
	<link href="css/footer.css" rel="stylesheet" /> 
	<link href="css/tuan.css" rel="stylesheet" /> 
	<link href="css/common.css" rel="stylesheet" />
	
	<script type="text/javascript" src="js/public.js"></script> 
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/layer/layer.js"></script>
  </head>
  
  <body>
 <%@ include file="jsps/top.jsp" %> 
<div class="yershop_wrapper">
<%@ include file="jsps/hader.jsp" %>
   <div class="menu-wrapper"> 
   <div class="nava"> 
    <div class="nav-all"> 
     <div class="all_class" id="all-class"> 
      <h2><span class="grid"><img src="images/grid.png" /></span><span>商品分类</span><b></b></h2> 
     </div> 
     <div class="all-goods" style="display: none;" id="all-goods"> 
      <!-- 循环开始 --> 
    	<c:forEach items="${bigTypes }" var="b">
    		<div class="item"> 
          <div class="product">
           <h4><a href="search?bid=${b.id }&p=1">${b.name }</a> </h4>
          </div> 
          <div class="product-wrap pos"> 
           <!--子分类--> 
           <div class="cf"> 
            <div class="fl wd252 pr20"> 
             <ul class="cf"> 
             <c:forEach items="${b.smallTypeList }" var="s">
              <li><span><a href="search?sid=${s.id }&p=1">${s.name }</a></span> </li> 
             </c:forEach>
             </ul>
            </div> 
           </div> 
          </div> 
         </div>
    	</c:forEach>
      <!-- 循环结束 --> 
     </div>
    </div> 
    <ul class="menu"> 
     <li> <a href="http://115.28.68.131/index.php?s=/Home/Index/index.html" target="_self">首页</a> </li>
     <li> <a href="http://115.28.68.131/index.php?s=/Home/article/index/pid/145.html" target="_blank">团购</a> </li>
     <li> <a href="http://115.28.68.131/index.php?s=/Home/article/index/pid/146.html" target="_self">抢购</a> </li>
     <li> <a href="http://115.28.68.131/index.php?s=/Home/article/index/pid/149.html" target="_self">预约</a> </li> 
    </ul> 
    </div>
   </div>
	<!-- /菜单 -->


	<!-- 主体 -->
	<div class="commom_wrapper">

 

<div class="main"><div class="main_center"><div class="center_left">
<!-- 左侧菜单 -->
	<h3><a rel="nofollow" href="javascript:vod(0)" class="home-me">我的会员中心</a></h3>
<h3><a rel="nofollow" class="home-me">订单中心</a></h3>
<dl>
<dd><a rel="nofollow" href="userMain.page" <c:if test="${rightPage=='orderAll' || rightPage=='orderDetail' }">class="current"</c:if>>我的订单</a></dd>
<dd><a rel="nofollow" href="MyCollect.page" <c:if test="${rightPage=='MyCollect' }">class="current"</c:if>>我的收藏</a></dd>
</dl>

<h3><a rel="nofollow" class="home-me">个人信息</a></h3>

<dl class="last">

<dd><a href="userData.page" <c:if test="${rightPage=='userData' }">class="current"</c:if>>个人档案</a></dd>
<dd><a rel="nofollow" href="userAddress.page" <c:if test="${rightPage=='address' || rightPage=='addAddress' }">class="current"</c:if>>地址管理</a></dd>

<dd><a rel="nofollow" href="index.cart">我的购物车</a></dd>
<dd><a rel="nofollow" href="changePassword.page" <c:if test="${rightPage=='changePassword' }">class="current"</c:if>>修改密码</a></dd>

</dl> <script>/* 左边菜单高亮 */
            url = window.location.pathname + window.location.search;
            url = url.replace(/(\/(p)\/\d+)|(&p=\d+)|(\/(id)\/\d+)|(&id=\d+)|(\/(group)\/\d+)|(&group=\d+)/, "");
			
            $("a[href='" + url + "']").addClass("current") ;
			</script>
   
<!-- 左侧菜单 -->

</div><!-- 个人中心 初始状态 start -->

<c:if test="${rightPage=='orderAll' }">
<%@include file="jsps/userMain/orderAll.jsp" %>
</c:if>

<c:if test="${rightPage=='orderDetail' }">
<%@include file="jsps/userMain/orderDetail.jsp" %>
</c:if>

<c:if test="${rightPage=='MyCollect' }">
<%@include file="jsps/userMain/collect.jsp" %>
</c:if>

<c:if test="${rightPage=='userData' }">
<%@include file="jsps/userMain/userData.jsp" %>
</c:if>

<c:if test="${rightPage=='address' }">
<%@include file="jsps/userMain/address.jsp" %>
</c:if>

<c:if test="${rightPage=='addAddress' }">
<%@include file="jsps/userMain/addAddress.jsp" %>
</c:if>

<c:if test="${rightPage=='changePassword' }">
<%@include file="jsps/userMain/changePassword.jsp" %>
</c:if>

</div>
 </div>
	<!-- 主体 -->

  <!-- 购物车js --> 
   <script>
	//购物车显示与隐藏
	 var Shopcart= document.getElementById('shopping_cart');
     var Goodsmenu= document.getElementById('goods');
            var timerr = null;//定义定时器变量
            //鼠标移入div1或div2都把定时器关闭了，不让他消失
            Shopcart.onmouseover =Goodsmenu.onmouseover = function ()
            {
                Goodsmenu.style.display = 'block';
                clearTimeout(timerr);
            }
            //鼠标移出div1或div2都重新开定时器，让他延时消失
            Shopcart.onmouseout =Goodsmenu.onmouseout = function ()
            {
                //开定时器
                timerr= setTimeout(function () { 
                    Goodsmenu.style.display = 'none'; }, 10);
            }
			
//购物车动态删除
	function delcommon(event)
		{ //获取事件源
event = event ? event : window.event; 
var obj = event.srcElement ? event.srcElement : event.target; 
//这时obj就是触发事件的对象，可以使用它的各个属性 
//还可以将obj转换成jquery对象，方便选用其他元素 
str = obj.innerHTML.replace(/<\/?[^>]*>/g,''); //去除HTML tag

	var $obj = $(obj);
	var str=$obj.parent().prev().html();
	if(obj.rel=="del")
{ var str=obj.name;
var uexist="";
	//全选的实现 定义删的发送路径
	if(uexist){
		var del='/index.php?s=/Home/Shopcart/delItemByuid.html';	
	}
else{
var del='delShoppingCart.goods';
	
	}

$.ajax({
type:'post', //传送的方式,get/post
url:del, //发送数据的地址
data:{sort:str},
 dataType: "json",
success:function(data)
{var $obj = $(obj);
	$obj.parents("li").remove();
	if(data.sum=="0"){  //判断购物车数量是否为0，为0则隐藏底部查看工具
						$("div.sc_goods_foot").hide();
					   	var html='<p class="sc_goods_none" >您的购物车还是空的，赶紧行动吧！</p>';
					   $("ul.sc_goods_ul").html(html);
				
	
	}
	else{$("div.sc_goods_foot").show();}

},
error:function (event, XMLHttpRequest, ajaxOptions, thrownError) {
alert(XMLHttpRequest+thrownError); }
		})
}
	
	} 
//购物车删除结束


	
</script> 
   <!-- /购物车js --> 

	<!-- 底部 -->
	
    <!-- 底部-->
   <script type="text/javascript" charset="utf-8" src="js/menudown.js"></script> 

</div></div>
 <%@ include file="jsps/footer.jsp" %> 
  </body>
</html>
