<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="center_right" id="MemberCenterCtrl" style="display: block;">
	<h3 class="cr_h3 cr_h3_gray">我的收藏</h3>
	<ul class="shoucang">
	 <c:forEach items="${collectList }" var="collect">
		<li class="out" onmouseover="this.className='over'" onmouseout="this.className='out'">
		<h3>收藏时间：${collect.time }
		<a href="javascript:delCoolect(${collect.goodsId })" style="color: #ff3c00;font-size: 13px;">  删除收藏</a>
		</h3>
			 <p> 
			   <span class="red"> <a href="goodsPageServlet?id=${collect.goodsId }"> 
				<img src="${collect.goodsProPic }" width="70" height="70"></a>
				<a href="goodsPageServlet?id=${collect.goodsId }">${collect.goodsName }</a> 
			   </span>
				<span class="red">￥${collect.goodsPrice }</span>
			 </p>
		</li>
	 </c:forEach>
	</ul>	
	<script type="text/javascript" src="js/laypage/laypage.js"></script> 
	<div id="page" style="text-align: center;margin: 10px;"></div>
	<script type="text/javascript">
		laypage({
		    cont: 'page',
		    skin: '#f63',
		    pages: "${count}", //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是18
		    curr: function(){ //通过url获取当前页，也可以同上（pages）方式获取
		        var page = location.search.match(/page=(\d+)/);
		        return page ? page[1] : 1;
		    }(), 
		    jump: function(e, first){ //触发分页后的回调
		        if(!first){ //一定要加此判断，否则初始时会无限刷新
		            location.href = 'MyCollect.page?page='+e.curr;
		        }
		    }
		});
		function delCoolect(id){
			//layer.alert(id,{icon:2});
			layer.confirm('确定要删除此条收藏吗？', {title:'删除收藏',icon: 3,shift:6,
			    btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
					type:'post', //传送的方式,get/post
					url:'delCollect.page', //发送数据的地址
					data:{id:id},
					 dataType: "json",
					success:function(data){
						//alert()
						location.reload();
					}});
			});
		}
	</script>
	<style>
		.layui-layer-btn .layui-layer-btn0 {
		    border-color: #f60;
		    background-color: #f40;
		    color: #fff;
		}
	</style>
	<!-- 分页 -->
        <!-- <div class="page">
<div>     <span class="rows">共 3 条记录</span></div>        </div> -->


<!-- 个人中心 初始状态 end --></div>