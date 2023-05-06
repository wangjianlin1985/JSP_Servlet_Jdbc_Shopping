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
		/*List<bigTypeBean> floor = bigTypeDao.selList(); //��ȡ¥����༯��
		List<slideBean> slideList = slideDao.selList();  //��ȡ�õ�ͼƬ����
		request.setAttribute("floor", floor);
		request.setAttribute("slideList", slideList);
		System.out.println("��¥�������ǣ�"+floor.get(1).getName());*/
		
		System.out.println("��������ҳservlet");
		String username = (String)request.getSession().getAttribute("username"); //��ȡ��¼���û���
		List<goodsBean> goodsList = new ArrayList<goodsBean>(); //���ﳵ����
		if(username==null){
			//�û�û�е�¼
			//��ȡ���ﳵ
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			Set keyList = gwc.keySet();
			Iterator it = keyList.iterator();
			
			while(it.hasNext()){
				String hid = (String)it.next();
				goodsBean hgoods = gwc.get(hid);
				int num = hgoods.getNum(); //��ȡ�����Ʒ������
				goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(Integer.parseInt(hid)); //�����ݿ����
				hgoods2.setNum(num);
				goodsList.add(hgoods2);
			}
			
		}else{
			//�û��Ѿ���¼
			int userId = usersDao.nameIsId(username);
			//��ȡ���ﳵ�����й�����Ŀ
			List<shoppingCart> list = shoppingCartDao.selList(userId);
			for(shoppingCart s: list){
				goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //�����ݿ����
				hgoods2.setNum(s.getNum());
				goodsList.add(hgoods2);
			}
			
		}
		
		
		request.setAttribute("gwcGoodsList", goodsList);
		request.getRequestDispatcher("/index.jsp").forward(request, response); //ת������ҳ
		
	}

}
