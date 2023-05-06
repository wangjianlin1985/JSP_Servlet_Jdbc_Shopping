package me.ilt.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.ilt.Bean.bigTypeBean;
import me.ilt.Bean.goodsBean;
import me.ilt.Bean.shoppingCart;
import me.ilt.Bean.slideBean;
import me.ilt.Dao.bigTypeDao;
import me.ilt.Dao.goodsDao;
import me.ilt.Dao.shoppingCartDao;
import me.ilt.Dao.slideDao;
import me.ilt.Dao.usersDao;

public class indexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*List<bigTypeBean> floor = bigTypeDao.selList(); //获取楼层大类集合
		List<slideBean> slideList = slideDao.selList();  //获取幻灯图片集合
		request.setAttribute("floor", floor);
		request.setAttribute("slideList", slideList);
		System.out.println("二楼的名称是："+floor.get(1).getName());*/
		
		System.out.println("进入了首页servlet");
		String username = (String)request.getSession().getAttribute("username"); //获取登录的用户名
		List<goodsBean> goodsList = new ArrayList<goodsBean>(); //购物车集合
		if(username==null){
			//用户没有登录
			//获取购物车
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			Set keyList = gwc.keySet();
			Iterator it = keyList.iterator();
			
			while(it.hasNext()){
				String hid = (String)it.next();
				goodsBean hgoods = gwc.get(hid);
				int num = hgoods.getNum(); //获取这个商品的数量
				goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(Integer.parseInt(hid)); //从数据库查找
				hgoods2.setNum(num);
				goodsList.add(hgoods2);
			}
			
		}else{
			//用户已经登录
			int userId = usersDao.nameIsId(username);
			//获取购物车中所有购物项目
			List<shoppingCart> list = shoppingCartDao.selList(userId);
			for(shoppingCart s: list){
				goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //从数据库查找
				hgoods2.setNum(s.getNum());
				goodsList.add(hgoods2);
			}
			
		}
		
		
		request.setAttribute("gwcGoodsList", goodsList);
		request.getRequestDispatcher("/index.jsp").forward(request, response); //转发到首页
		
	}

}
