package me.ilt.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.ilt.Bean.addressBean;
import me.ilt.Bean.goodsBean;
import me.ilt.Bean.orderBean;
import me.ilt.Bean.orderItemBean;
import me.ilt.Bean.shoppingCart;
import me.ilt.Dao.addressDao;
import me.ilt.Dao.goodsDao;
import me.ilt.Dao.orderDao;
import me.ilt.Dao.orderItemDao;
import me.ilt.Dao.shoppingCartDao;
import me.ilt.Dao.usersDao;

public class cartServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String MethodName = request.getServletPath();
		MethodName = MethodName.substring(1, MethodName.length() - 5);
		System.out.println("MethodName��"+MethodName);
		try {
			Method method = getClass().getDeclaredMethod(MethodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	public void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			for(shoppingCart s: list){
				goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //�����ݿ����
				hgoods2.setNum(s.getNum());
				goodsList.add(hgoods2);
			}
		}
		request.setAttribute("gwcGoodsList", goodsList);
		request.getRequestDispatcher("cart.jsp").forward(request, response);  //ת����jsp
		
	}
	public void tijiao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
		//��ȡ�û���
		String username = (String)request.getSession().getAttribute("username");
		String id = request.getParameter("cartItemIds");
		int userId = usersDao.nameIsId(username);
		String [] ids = id.split(",");
		
		
		
		//��ȡ���ﳵ
		
		List<goodsBean> goodsList = new ArrayList<goodsBean>();
		 DecimalFormat df = new DecimalFormat("#.00");  //��ֹ�۸�����쳣
		 double ze = 0; //�ܶ�
		for(String j : ids){
			//���� gwc�����жϹ��ﳵ�еĹ���������session�� ���������ݿ���
			int num = 0;
			if(gwc.size()==0){
				//�������������ݿ���
				shoppingCart s = shoppingCartDao.goodsIdSel(userId, Integer.parseInt(j));
				num = s.getNum();
			}else{
				//����������session��
				goodsBean hgoods = gwc.get(j);
				num = hgoods.getNum(); //��ȡ�����Ʒ������
			}
			
			
			
			goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(Integer.parseInt(j)); //�����ݿ����
			hgoods2.setNum(num);
			double hj = num*hgoods2.getPrice();
			ze +=hj;
			hgoods2.setTotal(Double.parseDouble(df.format(hj)));
			System.out.println("����֮ǰ�ܶ"+hj+"����֮��"+df.format(hj));
			goodsList.add(hgoods2);
			if(gwc.size()==0){
				shoppingCartDao.del(userId, Integer.parseInt(j));
			}else{
				gwc.remove(j);//ɾ����Ʒ  ���ﳵ
			}
			
		}
		//��ӡ���ﳵ
		System.out.println("��ӡ�������ﳵ");
		for (int i = 0; i < goodsList.size(); i++) {
			goodsBean gb = goodsList.get(i);
			System.out.println("ID="+gb.getId()+",name="+gb.getName()+",proce="+gb.getPrice()+",proPic="+gb.getProPic()+",num="+gb.getNum());
		}
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//����һ������
		String orderId = sjs(); //���ɶ�����
		orderBean or = new orderBean(orderId, userId, Double.parseDouble(df.format(ze)), 1, "", sdf.format(d), 1);
		orderDao.add(or);
		//���뵽��Ʒ��������
		for(goodsBean g : goodsList){
			orderItemBean o = new orderItemBean(g.getId(), g.getName(), g.getProPic(), g.getPrice(), g.getNum(), g.getTotal(), orderId);
			orderItemDao.add(o);
		}
		
		//�����û�ID��ȡ��ַ��Ϣ
		List<addressBean> addressList= addressDao.selAll(userId); 
		
		request.setAttribute("addressList", addressList);
		request.setAttribute("addressId", orderId);
		request.setAttribute("ze", df.format(ze));
		request.setAttribute("gwcGoodsList", goodsList);
		request.getRequestDispatcher("order.jsp").forward(request, response);  //ת����jsp
		
	}
	/**
	 * ��������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void buy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<goodsBean> goodsList = new ArrayList<goodsBean>();
		DecimalFormat df = new DecimalFormat("#.00");  //��ֹ�۸�����쳣
		System.out.println("��������������");
		int goodsId = Integer.parseInt(request.getParameter("id"));
		int num = Integer.parseInt(request.getParameter("num"));
		String username = (String)request.getSession().getAttribute("username");
		goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(goodsId); //�����ݿ����
		hgoods2.setNum(num);
		double hj = Double.parseDouble(df.format(num*hgoods2.getPrice()));
		hgoods2.setTotal(hj); //��ʽ��������
		goodsList.add(hgoods2);
		
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//����һ������
		String orderId = sjs(); //���ɶ�����
		int userId = usersDao.nameIsId(username);
		orderBean or = new orderBean(orderId, userId, hj, 1, "", sdf.format(d), 1);
		orderDao.add(or);
		//���뵽��Ʒ��������
		for(goodsBean g : goodsList){
			orderItemBean o = new orderItemBean(g.getId(), g.getName(), g.getProPic(), g.getPrice(), g.getNum(), g.getTotal(), orderId);
			orderItemDao.add(o);
		}
		
		//�����û�ID��ȡ��ַ��Ϣ
		List<addressBean> addressList= addressDao.selAll(userId); 
		
		request.setAttribute("addressList", addressList);
		request.setAttribute("addressId", orderId);
		request.setAttribute("ze", hj);
		request.setAttribute("gwcGoodsList", goodsList);
		request.getRequestDispatcher("order.jsp").forward(request, response);  //ת����jsp
		
	}
	/**
	 * ���ɶ�����  ��ǰʱ�������λ�����
	 * @return
	 */
	public static String sjs() {
		String sjs = "";
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		int x=(int)(Math.random()*100);
		if(x<10){
			x=x+9;
		}else if(x==100){
			x--;
		}
		sjs=sdf.format(d)+x;
		return sjs;
	}

}
