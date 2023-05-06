<%@page import="me.ilt.Bean.goodsBean"%>
<%@page import="me.ilt.Bean.smallTypeBean"%>
<%@page import="me.ilt.Bean.bigTypeBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if(request.getAttribute("gwcGoodsList")==null){
		pageContext.forward("indexServlet");
	}
%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>首页_宅商城</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="css/top.css" rel="stylesheet" />
<link href="css/common.css" rel="stylesheet" />
<link href="css/footer.css" rel="stylesheet" />
<link href="css/lunbo.css" rel="stylesheet" />
<!-- 轮播css -->
<script type="text/javascript" src="js/public.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.lazyload.min.js"></script>
</head>

<body>
<%@ include file="jsps/top.jsp"  %>
<div class="yershop_wrapper">
<%@ include file="jsps/hader.jsp" %>
		<!-- /头部 -->
		<!-- 主体 -->
		<div class="common_wrapper">
			<!--container begin-->
			<div class="flexcontainer">
				<!--menu begin-->
				<div class="nav-wrap">
					<!--fenlei begin-->
					<div class="nava">
						<div class="goods">
							<div>
								<h2>
									<span class="grid"><img src="images/grid.png" /></span> <span>商品分类</span><b></b>
								</h2>
							</div>
							<div class="all-goods" id="all-goods">

								<!-- 商品分类循环go -->
								<c:forEach items="${floor }" var="bigType">
									<div class="item">
										<div class="product">
											<h4>
												<a href="search?bid=${bigType.id }&p=1">${bigType.name }</a>
											</h4>
										</div>
										<div class="product-wrap pos">
											<!--子分类-->
											<div class="cf">
												<div class="fl wd252 pr20">
													<ul class="cf">
														<c:forEach items="${bigType.smallTypeList }"
															var="smallType">
															<li><span><a
																	href="search?sid=${smallType.id }&p=1">${smallType.name
																		}</a></span></li>
														</c:forEach>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
								<!-- 商品分类循环end -->

							</div>
						</div>
						<!--fenlei over-->
						<ul class="menu">
							<li><a href="#" target="_self">首页</a></li>
							<li><a href="#" target="_blank">团购</a></li>
							<li><a href="#" target="_self">抢购</a></li>
							<li><a href="#" target="_self">预约</a></li>
						</ul>
					</div>
<script>
    $(".menu-list li").eq(0).css('background','#474747');  
</script>
					<!--首页高亮-->
				</div>
				<!--menu over-->
				<!--pic begin-->
				<div class="screen">
					<div class="ck-slide">
						<ul class="ck-slide-wrapper">
							<c:forEach items="${slideList }" var="u">
								<li><a href="${u.url }" target="_blank"> <img src="${u.proPic }" /></a></li>
							</c:forEach>
						</ul>
						<a href="javascript:;" class="ctrl-slide ck-prev">上一张</a> <a
							href="javascript:;" class="ctrl-slide ck-next">下一张</a>
						<div class="ck-slidebox">
							<div class="slideWrap">
								<ul class="dot-wrap">
									<li class="current"><em>1</em></li>
									<li><em>2</em></li>
									<li><em>3</em></li>
									<li><em>4</em></li>
									<li><em>5</em></li>
								</ul>
							</div>
						</div>
					</div>
					<script type="text/javascript" src="js/slide.js"></script>
					<!-- 轮播js -->
<script>
$('.ck-slide').ckSlide({
	dir: 'x', //左右滚动
	autoPlay: true
});
</script>
<script type="text/javascript" language="javascript">
var PicTotal = 5;
var CurrentIndex;
var ToDisplayPicNumber = 0;
$("div.LunBo div.LunBoNum span").click(DisplayPic);
function DisplayPic() {
// 测试是父亲的第几个儿子
CurrentIndex = $(this).index();
// 删除所有同级兄弟的类属性
$(this).parent().children().removeClass("CurrentNum")
// 为当前元素添加类
$(this).addClass("CurrentNum");
// 隐藏全部图片
var Pic = $(this).parent().parent().children("ul");
$(Pic).children().hide();
// 显示指定图片
$(Pic).children("li").eq(CurrentIndex).show();
}
function PicNumClick() {
$("div.LunBo div.LunBoNum span").eq(ToDisplayPicNumber).trigger("click");
ToDisplayPicNumber = (ToDisplayPicNumber + 1) % PicTotal;
setTimeout("PicNumClick()",3000);
}
setTimeout("PicNumClick()",3000);
</script>
					<!--pic over-->
					<!-- <div class="rside">
						<span> 首页广告位2 <a href="#" target="_blank"
							title="首页广告位2"> <img src="images/ad.jpg" /></a> 首页广告位2</span>
						<div class="note">
							<ul>
								<li class="selected">公告</li>
								<li>活动</li>
							</ul>
						</div>
						<div id="msg">
							<div class="message">
								<ul>
								</ul>
							</div>
							<div class="message" style="display: none">
								<ul>
									<li><a href="#"> 元朗荣华</a></li>
								</ul>
							</div>
							<div class="message" style="display: none"></div>
						</div>
					</div> -->
				</div>
				<!--screen over-->
			</div>
			<!--flexcontainer over-->
			<!-- 楼层循环go -->
			<c:forEach items="${floor }" var="bigType" varStatus="big">
				<div class="list">
					<h3>
						<span class="floor">${big.index+1 }F</span><span>${bigType.name
							}</span>
					</h3>
					<div class="category">
						<div class="icon">
							<ul class="tag clearfix" data-tpc="3">
								<c:forEach items="${bigType.smallTypeList }" var="sm">
									<a target="_blank" title="${sm.name }"
										href="search?sid=${sm.id }&p=1"> ${sm.name }</a>
								</c:forEach>
							</ul>
							<a href="search?bid=${bigType.id }&p=1" target="_blank">
							<img class="lazy" src="images/grey.png" data-original="${bigType.imgUrl }" /> </a>
						</div>
						<!--栏目推荐位调用结束-->
						<!--栏目文章调用开始-->
						<ul class="article" id="list_52">
							<!-- 循环展示商品go -->
							<c:forEach items="${bigType.goods }" var="goods">
								<li>
									<div class="pic">
										<a class="yershop_img" href="goodsPageServlet?id=${goods.id }"
											target="_blank" title="${goods.name }"> 
											<img class="lazy" src="images/grey.png" data-original="${goods.proPic }" /><em></em></a>
									</div>
									<div class="name">
										<a href="goodsPageServlet?id=${goods.id }" target="_blank"
											title="${goods.name }">${goods.name }</a>
									</div>
									<div class="price">
										<font>￥</font> <span>${goods.price }</span> <span></span>
									</div>
								</li>
							</c:forEach>
							<!-- 循环展示商品end -->
						</ul>
					</div>
					<!--栏目文章调用结束-->
				</div>
			</c:forEach>
			<!-- 楼层循环end -->
<script type="text/javascript" language="javascript">
function getlist(id,pid) {
$("#category_"+id).addClass('active').siblings().removeClass('active'); 		
$.post('/index.php?s=/Home/Index/getGoodlist.html', {cateid:id},function(data){
	  var html = '';
	 
$("ul#list_"+pid).empty(); 		  
$.each(data, function(i,n){			
var html='<li><div class="pic" > <a class="yershop_img" href="'+n.url+'" target="_blank" title="'+n.title+'"> <img src="'+n.pic+'" ></a>  </div> <div class="name"><a href="'+n.url+'"  target="_blank" title="'+n.title
+'">'+n.title+'</a></div> <div  class="price">  <font>￥</font><span>'+n.price+'</span></div></li> ';	
						 $("ul#list_"+pid).append(html);
						 });

});
}

</script>
		</div>
		<!-- /主体 -->
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
//图片延时加载开启
$('img.lazy').lazyload({ 
	  effect:'fadeIn' 
	});
</script>
		<!-- 购物车js -->
		<!-- 底部 -->
		<!-- 底部-->
		<script type="text/javascript" charset="utf-8" src="js/menudown.js"></script>
	</div>
<%@ include file="jsps/footer.jsp" %>
	<!-- /底部 -->

</body>
</html>
