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
import me.ilt.Bean.PageBean;
import me.ilt.Bean.shoppingCart;
import me.ilt.Dao.bigTypeDao;
import me.ilt.Dao.goodsDao;
import me.ilt.Dao.shoppingCartDao;
import me.ilt.Dao.smallTypeDao;
import me.ilt.Dao.usersDao;

public class searchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s = request.getParameter("name"); //��ȡ��������Ʒ����
		int p = Integer.parseInt(request.getParameter("p")); //��ȡ�����ҳ��
		String order = request.getParameter("order"); //��ȡ����ʽ
		System.out.println("��ȡĬ�ϵ�����ʽ��:"+order);
		String bid = request.getParameter("bid");
		String sid = request.getParameter("sid");
		List<goodsBean> salesTop = goodsDao.salesTop(); //ǰ������
		PageBean PageDate = null;
		if(order==null){
			order="1";
		}
		if(bid!=null){
			//���ݴ���ID��ѯ
			PageDate = goodsDao.bidPageSel(bid, p, order);
			request.setAttribute("bigTypeName", bigTypeDao.bidIsName(bid));  //�������Ʒ��뵽������
		}else if(sid!=null){
			//����С��ID��ѯ
			PageDate = goodsDao.sidPageSel(sid, p, order);
			request.setAttribute("smallTypeName", smallTypeDao.sidIsName(sid));  //С�����Ʒ�������
		}else{
			//��Ʒ���Ʋ�ѯ
			/*
			1:���ȶ�����
			2:������ʱ��
			3:���۸�����
			4:����������
			 */
			PageDate = goodsDao.pageSel(s, p,order); //�õ���ѯҳ������
			}
		//��ȡ���ﳵ
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
			for(shoppingCart s1: list){
				goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s1.getGoodsId()); //�����ݿ����
				hgoods2.setNum(s1.getNum());
				goodsList.add(hgoods2);
			}
		}
		request.setAttribute("gwcGoodsList", goodsList);
		request.setAttribute("PageDate", PageDate);  //���뵽������
		request.setAttribute("salesTop", salesTop); //����ǰ5����
		request.setAttribute("order", order); //��������ʽ
		
		
		

		String type = "name";  //��ʶ����������   ����Ʒ���Ʋ�ѯ  ������ID��ѯ  ��С��ID��ѯ
		String tname = s; //�������ƻ�����ѯ������
		if(s==null){
			if(bid!=null){
				s=bid;
				type="bid";
				tname=(String)request.getAttribute("bigTypeName");
			}else if(sid!=null){
				s=sid;	
				type="sid";
				tname=(String)request.getAttribute("smallTypeName");
			}
		}
		request.setAttribute("s", s); //�����������ƻ�ID
		request.setAttribute("type", type); //������������
		request.setAttribute("tname", tname); //������������
		request.getRequestDispatcher("search.jsp").forward(request, response);  //ת����jsp

	}

}
