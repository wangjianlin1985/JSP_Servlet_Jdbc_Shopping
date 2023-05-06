<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     <script type="text/javascript">
//显示灰色 jQuery 遮罩层
function showBg() {
    var bh = $("body").height();
    var bw = $("body").width();
    $("#fullbg").css({
        height:bh,
        width:bw,
        display:"block"
    });
    $("#dialog").show();
}
//关闭灰色 jQuery 遮罩
function closeBg() {
    $("#fullbg,#dialog").hide();
}
</script><!--[if lte IE 6]>
<script type="text/javascript">
// 浮动对话框
$(document).ready(function() {
    var domThis = $('#dialog')[0];
    var wh = $(window).height() / 2;
    $("body").css({
        "background-image": "url(about:blank)",
        "background-attachment": "fixed"
    });
    domThis.style.setExpression('top', 'eval((document.documentElement).scrollTop + ' + wh + ') + "px"');
});
</script>
<![endif]-->
   
  
       <script type="text/javascript">
//登录后刷新页面，载入数据
$("#login_btn").click(function(){
	   
   var yourname=$('#inputusername').val();
	var yourword=$('#inputpassword').val();
		
	$.ajax({
type:'post', //传送的方式,get/post
url:'/index.php?s=/Home/User/loginfromdialog.html', //发送数据的地址
data:{username:yourname,password:yourword},
 dataType: "json",
success:function(data)
{ if(data.status=="1")
{
$(".tips").html(data.info);
window.location.reload();
$("#uid").val(data.uid);
}
else{$(".tips").html(data.info);

}

},
error:function (event, XMLHttpRequest, ajaxOptions, thrownError) {
alert(XMLHttpRequest+thrownError); }
});});
	   //全选的实现
	$(".check-all").click(function(){
		$(".ids").prop("checked", this.checked);
	});
	$(".ids").click(function(){
		var option = $(".ids");
		option.each(function(i){
			if(!this.checked){
				$(".check-all").prop("checked", false);
				return false;
			}else{
				$(".check-all").prop("checked", true);
			}
		});
	});
	var uexist="";
	//全选的实现 定义加、减、删的发送路径
	if(uexist){
		var inc='/index.php?s=/Home/Shopcart/incNumByuid.html';
		var dec='/index.php?s=/Home/Shopcart/decNumByuid.html';
		var del='/index.php?s=/Home/Shopcart/delItemByuid.html';
	
	}
else{
	    var inc='/index.php?s=/Home/Shopcart/incNum.html';
		var dec='/index.php?s=/Home/Shopcart/decNum.html';
		var del='/index.php?s=/Home/Shopcart/delItem.html';
		
	
	}

	function checklogin() {
	var uexist="";

		if(uexist){document.myform.submit();}
		else{
			showBg();
	
			}
		
		 }
function favor(id) { 
var uexist="";
if(uexist){
var favorid=id;
	$.ajax({
type:'post', //传送的方式,get/post
url:'/index.php?s=/Home/User/favor.html', //发送数据的地址
data:{id:favorid},
 dataType: "json",
success:function(data){
	if(data.status=="1"){alert(data.msg);}
	}
	,
error:function (event, XMLHttpRequest, ajaxOptions, thrownError) {
alert(XMLHttpRequest+thrownError); }
})	
}

else{
showBg();
}
	
	}
	   
	function myfunction(event) { 
event = event ? event : window.event; 
var obj = event.srcElement ? event.srcElement : event.target; 
//这时obj就是触发事件的对象，可以使用它的各个属性 
//还可以将obj转换成jquery对象，方便选用其他元素 
str = obj.innerHTML.replace(/<\/?[^>]*>/g,''); //去除HTML tag

	var $obj = $(obj);


	if(obj.rel=="jia"){
	var num=$obj.next().val(); 

var gid=$obj.next().attr("id");
	
 a=parseInt(num)+1;
 $obj.next().val(a); 
//增加数据
		$.ajax({
type:'post', //传送的方式,get/post
url:inc, //发送数据的地址
data:{sort:gid},
 dataType: "json",
success:function(data)
{$("span#count").text(data.count);
$("span#total").text(data.price);
$("span#sum").text(data.sum);
	$("em.price").text(data.price);

},
error:function (event, XMLHttpRequest, ajaxOptions, thrownError) {
alert(XMLHttpRequest+thrownError); }
		})}

if(obj.rel=="jian")
{ var num=$obj.prev().val(); 

var str=$obj.prev().attr("id")	
    
     
    //如果文本框的值大于0才执行减去方法  
     if(num >0){  
      //并且当文本框的值为1的时候，减去后文本框直接清空值，不显示0  
      if(num==1)  
      { alert("最少为1");
   return true;
      }  
      //否则就执行减减方法  
      else  
      { 
      a=parseInt(num)-1;
	  
 $obj.prev().val(a);   
    
      } 
	  
    
     } 
	   
 

//减少数据
$.ajax({
type:'post', //传送的方式,get/post
url:dec, //发送数据的地址
data:{sort:str},
 dataType: "json",
success:function(data)
{$("span#count").text(data.count);
$("span#total").text(data.price);
$("span#sum").text(data.sum);
	$("em.price").text(data.price).fadeIn();

},
error:function (event, XMLHttpRequest, ajaxOptions, thrownError) {
alert(XMLHttpRequest+thrownError); }
		})
}
var html="<div class='shopcart_main_none'><div class='shopcart_main_none_img'></div><div class='shopcart_main_none_main'><p>你的购物车还是空的哦赶紧行动吧!</p><a  href='/index.php?s=/Home/Index/index.html'>马上购物</a></div>";
if(obj.rel=="del")
{ var string=obj.name;
//删除数据
$.ajax({
type:'post', //传送的方式,get/post
url:del, //发送数据的地址
data:{sort:string},
 dataType: "json",
success:function(data)
{var $obj = $(obj);
	$obj.parents("tr").empty();
	$("span#count").text(data.count);
$("span#total").text(data.price);
$("span#sum").text(data.sum);
	$("em.price").text(data.price);
	var a=data.sum;
if(a=="0"){$(".text").remove();$("#form").html(html);}
},
error:function (event, XMLHttpRequest, ajaxOptions, thrownError) {
alert(XMLHttpRequest+thrownError); }
		})
}


	}
	
	
    </script>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  </body>
</html>
