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
    
    <title>${tname }_搜索</title>
    
	<link href="css/top.css" rel="stylesheet" /> 
	<link href="css/detail.css" rel="stylesheet" /> 
	<link href="css/footer.css" rel="stylesheet" /> 
	<link href="css/tuan.css" rel="stylesheet" /> 
	<link href="css/common.css" rel="stylesheet" />
	
	<script type="text/javascript" src="js/public.js"></script> 
	<script type="text/javascript" src="js/jquery.min.js"></script>
  </head>
  <body>
<%@ include file="jsps/top.jsp"  %>
  <div class="yershop_wrapper"> 
   <!-- logo --> 
<%@ include file="jsps/hader.jsp" %>
   <!-- /头部 --> 
   <!-- 菜单 --> 
   <!-- 导航条--> 
   <!-- 导航条--> 
   <div class="menu-wrapper"> 
   <div class="nava"> 
    <div class="nav-all"> 
     <div class="all_class" id="all-class"> 
      <h2><span class="grid"><img src="images/grid.png" /></span><span>商品分类</span><b></b></h2> 
     </div> 
     <div class="all-goods" style="display: none;" id="all-goods"> 
      <!-- 循环开始 --> 
      <c:forEach items="${bigTypes }" var="bt">
         <div class="item"> 
          <div class="product">
           <h4><a href="search?bid=${bt.id }&p=1">${bt.name }</a> </h4>
          </div> 
          <div class="product-wrap pos"> 
           <!--子分类--> 
           <div class="cf"> 
            <div class="fl wd252 pr20"> 
            
             <ul class="cf"> 
             <c:forEach items="${bt.smallTypeList }" var="sm">
              <li><span><a href="search?sid=${sm.id }&p=1">${sm.name }</a></span> </li> 
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
    <!-- Contents  --> 
    <div class="ml_content"> 
     <div class="goodlist-main"> 
      <div class="lists-position"> 
      <!-- 如果搜索的是大类则左侧显示小类集合 -->
      <c:if test="${type=='bid' }">
       <div class="category-title"> 
        <h4><span>${tname }</span></h4> 
        <div class="category-child"> 
        
    		<c:forEach items="${bigTypes }" var="bt" >
    		
    			<c:if test="${bt.id==param.bid }">
    				<c:forEach items="${bt.smallTypeList }" var="small">
         				<span><a href="search?sid=${small.id }&p=1">${small.name }</a></span>
    				</c:forEach>
    			</c:if>
    		</c:forEach>
        </div> 
       </div>  
       </c:if>
       
       <div class="salesrank"> 
        <h5><span>销量排行</span></h5> 
        <ul>
         <!-- 销量排行循环开始 -->
         
       	<c:forEach items="${salesTop }" var="sg">
         <li>
         <a class="picture" href="goodsPageServlet?id=${sg.id }">
         <img src="${sg.proPic }" />
         </a> 
         <a class="title" href="goodsPageServlet?id=${sg.id }"> ${sg.name }</a> 
         <span>￥${sg.price }</span> 
         </li> 
         </c:forEach>
         <!-- 销量排行循环开始 --> 
        </ul> 
       </div> 
      </div> 
      <div class="lists-area"> 
       <p class="serach-title" id="s">"${tname }"的相关结果</p>
       <ul class="ml_content_top">
        <li <c:if test="${param.order=='1' || param.order==null }">class="active"</c:if> ><a href="search?${type }=${s }&p=1&order=1" class="asc">热度<i></i></a></li>
        <li <c:if test="${param.order=='2' }">class="active"</c:if> ><a href="search?${type }=${s }&p=1&order=2" class="asc">最新<i></i></a></li>
        <li <c:if test="${param.order=='3' }">class="active"</c:if> ><a href="search?${type }=${s }&p=1&order=3" class="asc">价格<i></i></a></li>
        <li <c:if test="${param.order=='4' }">class="active"</c:if> ><a href="search?${type }=${s }&p=1&order=4" class="asc">销量<i></i></a></li>
       </ul> 
       <ul class="goodlist"> 
       <c:if test="${PageDate.count==0 }">
        	<img src="images/s404.png" />
        	抱歉！没有找到与“${tname }”相关的宝贝。
        </c:if>
       <!-- 商品展示循环开始 -->
       	<c:forEach items="${PageDate.data }" var="goods">
       	<li class="face" onmouseover="this.className='facehover'" onmouseout="this.className='face'"> 
        	<a href="goodsPageServlet?id=${goods.id }" class="list-img"> 
        		<img src="${goods.proPic }" />
        	</a> 
	        <p> 
	        	<a href="goodsPageServlet?id=${goods.id }" class="t2"> ${goods.name }</a>
	        </p> 
	        <p>
	        	<span class="red" title="预览：4.90">价格：￥${goods.price }</span>
	        </p> 
        </li> 
        </c:forEach>
        <!-- 商品展示循环结束 -->
       </ul> 
       <!-- 分页 --> 
       <c:if test="${PageDate.count!=0 }">
       <div class="page"> 
        <div> 
        	<c:if test="${param.p!=1  }">
        		<a class="prev" href="search?${type }=${s }&p=${param.p-1 }&order=${order }#s">上一页</a>
        	</c:if>
        	<c:forEach begin="1" end="${PageDate.pagetotal }" varStatus="sta">
        		<c:if test="${param.p==sta.count }">
        			<span class="current">${param.p }</span>
        		</c:if>
        		<c:if test="${param.p!=sta.count }">
        			<a class="num" href="search?${type }=${s }&p=${sta.count }&order=${order }#s">${sta.count }</a>
        		</c:if>
        	</c:forEach>
        	<c:if test="${param.p!=PageDate.pagetotal}">
        		<a class="next" href="search?${type }=${s }&p=${param.p+1 }&order=${order }#s">下一页</a>
        	</c:if>
         	<span class="rows">共${PageDate.count } 条记录</span>
        </div> 
       </div>
       </c:if>
      </div> 
     </div> 
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
  </div> 
 <%@ include file="jsps/footer.jsp" %> 
  </body>
</html>
