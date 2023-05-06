package me.ilt.Servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import me.ilt.Bean.PageBean;
import me.ilt.Bean.addressBean;
import me.ilt.Bean.bigTypeBean;
import me.ilt.Bean.collectBean;
import me.ilt.Bean.goodsBean;
import me.ilt.Bean.orderBean;
import me.ilt.Bean.orderItemBean;
import me.ilt.Bean.shoppingCart;
import me.ilt.Bean.slideBean;
import me.ilt.Bean.userBean;
import me.ilt.Dao.addressDao;
import me.ilt.Dao.bigTypeDao;
import me.ilt.Dao.collectDao;
import me.ilt.Dao.goodsDao;
import me.ilt.Dao.orderDao;
import me.ilt.Dao.orderItemDao;
import me.ilt.Dao.shoppingCartDao;
import me.ilt.Dao.slideDao;
import me.ilt.Dao.usersDao;
import me.ilt.Util.responseUtil;

public class pageServlet extends HttpServlet {

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
	public void userMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�������û�����");
		String username = (String)request.getSession().getAttribute("username");
		String p = request.getParameter("p");
		
		if(p==null){ //���δ�յ�����ҳ��  ��ֵ1
			p="1";
		}
		String type = request.getParameter("type");
		/**
		 * type˵��
		 * 1��ȫ������
		 * 2����֧������
		 * 3������������
		 * 4����ȷ�϶���
		 */
		if(type==null){
			type="1";
		}
		
		if(username==null){
			response.sendRedirect("login.jsp");
		}else{
			int userId = usersDao.nameIsId(username);
			PageBean pageBean =orderDao.userIdIsorder(userId,Integer.parseInt(p),type);
			request.setAttribute("PageDate", pageBean); //���붩������
			
			//��ȡ���ﳵ
			List<goodsBean> goodsList = new ArrayList<goodsBean>(); //���ﳵ����
			//��ȡ���ﳵ�����й�����Ŀ
			List<shoppingCart> list = shoppingCartDao.selList(userId);
			for(shoppingCart s: list){
				goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //�����ݿ����
				hgoods2.setNum(s.getNum());
				goodsList.add(hgoods2);
			}
			request.setAttribute("type", type); //������������
			request.setAttribute("p", p); //��������ҳ��
			request.setAttribute("gwcGoodsList", goodsList); //���빺�ﳵ����
			request.setAttribute("rightPage", "orderAll");
			request.getRequestDispatcher("userMain.jsp").forward(request, response);
		}
	}
	/**
	 * ��������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void orderDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username");
		if(username==null){
			response.sendRedirect("login.jsp");
		}
		
		System.out.println("�����˶�������");
		String id = request.getParameter("id");
		orderBean orderBean = orderDao.orderIdSel(id);
		if(orderBean.getRemarks().equals("")){
			orderBean.setRemarks("δ��д");
		}
		addressBean address = addressDao.idSel(orderBean.getAddressId());
		String addressStr = address.getProvince()+"  "+address.getCity()+"  "+address.getArea()+"  "+address.getAddress()+"&nbsp;&nbsp;&nbsp;&nbsp;�ֻ���"+address.getPhone()+"&nbsp;&nbsp;&nbsp;&nbsp;�ջ���������"+address.getUsername();
		if(address.getProvince().equals("δ��д")){
			addressStr="δ��д";
		}
		List<orderItemBean> orderItemList = orderItemDao.orderIdSelItem(id); //��ȡ������Ŀ
		
		
		//��ȡ���ﳵ
		List<goodsBean> goodsList = new ArrayList<goodsBean>(); //���ﳵ����
		//��ȡ���ﳵ�����й�����Ŀ
		int userId = usersDao.nameIsId(username);
		List<shoppingCart> list = shoppingCartDao.selList(userId);
		for(shoppingCart s: list){
			goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //�����ݿ����
			hgoods2.setNum(s.getNum());
			goodsList.add(hgoods2);
		}
		
		
		request.setAttribute("gwcGoodsList", goodsList); //���빺�ﳵ����
		request.setAttribute("orderBean", orderBean);
		request.setAttribute("address", addressStr);
		request.setAttribute("orderItemList", orderItemList);
		request.setAttribute("rightPage", "orderDetail");
		request.getRequestDispatcher("userMain.jsp").forward(request, response);
	}
	/**
	 * ajax����ղ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addCollect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String goodsId = request.getParameter("id");
		String username = (String)request.getSession().getAttribute("username");
		int userId = usersDao.nameIsId(username);
		goodsBean goods = goodsDao.goodsIdSel(Integer.parseInt(goodsId));
		int i = collectDao.count(userId, Integer.parseInt(goodsId));
		JSONObject json = new JSONObject();
		if(i!=0){
			json.put("msg", "�����ղع�����Ʒ��");
		}else{
			collectDao.add(new collectBean(userId, Integer.parseInt(goodsId), goods.getName(), goods.getProPic(), goods.getPrice(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
			json.put("msg", "�ղسɹ���");
		}
		responseUtil.write(response, json);
	}
	/**
	 * ɾ���ղ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCollect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String goodsId = request.getParameter("id");
		String username = (String)request.getSession().getAttribute("username");
		int userId = usersDao.nameIsId(username);
		int i = collectDao.del(userId, Integer.parseInt(goodsId));
		JSONObject json = new JSONObject();
		if(i!=0){
			json.put("msg", "ɾ���ɹ���");
		}
		responseUtil.write(response, json);
	}
	/**
	 * �ҵ��ղ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void MyCollect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username");
		if(username==null){
			response.sendRedirect("login.jsp");
		}
		//��ȡ���ﳵ
		List<goodsBean> goodsList = new ArrayList<goodsBean>(); //���ﳵ����
		//��ȡ���ﳵ�����й�����Ŀ
		int userId = usersDao.nameIsId(username);
		List<shoppingCart> list2 = shoppingCartDao.selList(userId);
		for(shoppingCart s: list2){
			goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //�����ݿ����
			hgoods2.setNum(s.getNum());
			goodsList.add(hgoods2);
		}
		request.setAttribute("gwcGoodsList", goodsList); //���빺�ﳵ����
		
		String page = request.getParameter("page");
		if(page==null){
			page="1";
		}
		int count = (int)Math.ceil(collectDao.count(userId)*1.0/10);
		List<collectBean> list =collectDao.selList(userId,Integer.parseInt(page));
		request.setAttribute("count", count); 
		request.setAttribute("collectList", list); 
		request.setAttribute("rightPage", "MyCollect");
		request.getRequestDispatcher("userMain.jsp").forward(request, response);
	}
	/**
	 * �û���Ϣҳ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void userData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username");
		if(username==null){
			response.sendRedirect("login.jsp");
		}
		//��ȡ���ﳵ
		List<goodsBean> goodsList = new ArrayList<goodsBean>(); //���ﳵ����
		//��ȡ���ﳵ�����й�����Ŀ
		int userId = usersDao.nameIsId(username);
		List<shoppingCart> list2 = shoppingCartDao.selList(userId);
		for(shoppingCart s: list2){
			goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //�����ݿ����
			hgoods2.setNum(s.getNum());
			goodsList.add(hgoods2);
		}
		userBean u = usersDao.idSel(userId);
		System.out.println("�õ���userBean����"+u);
		request.setAttribute("userBean", u); //����userBean
		request.setAttribute("gwcGoodsList", goodsList); //���빺�ﳵ����
		request.setAttribute("rightPage", "userData");
		request.getRequestDispatcher("userMain.jsp").forward(request, response);
		
	}
	/**
	 * �û���ַ����ҳ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void userAddress(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username");
		if(username==null){
			response.sendRedirect("login.jsp");
		}
		//��ȡ���ﳵ
		List<goodsBean> goodsList = new ArrayList<goodsBean>(); //���ﳵ����
		//��ȡ���ﳵ�����й�����Ŀ
		int userId = usersDao.nameIsId(username);
		List<shoppingCart> list2 = shoppingCartDao.selList(userId);
		for(shoppingCart s: list2){
			goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //�����ݿ����
			hgoods2.setNum(s.getNum());
			goodsList.add(hgoods2);
		}
		request.setAttribute("gwcGoodsList", goodsList); //���빺�ﳵ����
		
		
		List<addressBean> list = addressDao.selAll(userId); //��ȡ���û�ȫ���ĵ�ַ��Ŀ
		
		request.setAttribute("addressList", list);
		request.setAttribute("rightPage", "address");
		request.getRequestDispatcher("userMain.jsp").forward(request, response);
	}
	/**
	 * �Ñ���ӵ�ַ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addAddress(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username");
		if(username==null){
			response.sendRedirect("login.jsp");
		}
		
		//��ȡ���ﳵ
		List<goodsBean> goodsList = new ArrayList<goodsBean>(); //���ﳵ����
		//��ȡ���ﳵ�����й�����Ŀ
		int userId = usersDao.nameIsId(username);
		List<shoppingCart> list2 = shoppingCartDao.selList(userId);
		for(shoppingCart s: list2){
			goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //�����ݿ����
			hgoods2.setNum(s.getNum());
			goodsList.add(hgoods2);
		}
		request.setAttribute("gwcGoodsList", goodsList); //���빺�ﳵ����
		
		request.setAttribute("rightPage", "addAddress");
		request.getRequestDispatcher("userMain.jsp").forward(request, response);
	}
	/**
	 * �û��޸�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username");
		if(username==null){
			response.sendRedirect("login.jsp");
		}
		
		//��ȡ���ﳵ
		List<goodsBean> goodsList = new ArrayList<goodsBean>(); //���ﳵ����
		//��ȡ���ﳵ�����й�����Ŀ
		int userId = usersDao.nameIsId(username);
		List<shoppingCart> list2 = shoppingCartDao.selList(userId);
		for(shoppingCart s: list2){
			goodsBean hgoods2 = goodsDao.gwcGoodsIdSel(s.getGoodsId()); //�����ݿ����
			hgoods2.setNum(s.getNum());
			goodsList.add(hgoods2);
		}
		request.setAttribute("gwcGoodsList", goodsList); //���빺�ﳵ����
		
		request.setAttribute("rightPage", "changePassword");
		request.getRequestDispatcher("userMain.jsp").forward(request, response);
	}
	/**
	 * ˢ�»���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void refreshSystem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<bigTypeBean> floor = bigTypeDao.selList(); //��ȡ¥����༯��
		List<bigTypeBean> bigTypes = bigTypeDao.bigselList(); //��ȡ���༰С�༶��
		List<slideBean> slideList = slideDao.selList();  //��ȡ�õ�ͼƬ����
		
		ServletContext application = request.getServletContext();
		application.setAttribute("floor", floor);
		application.setAttribute("slideList", slideList);
		application.setAttribute("bigTypes", bigTypes);
		System.out.println("��ˢ��ϵͳ����");
		JSONObject json = new JSONObject();
		json.put("success", "true");
		responseUtil.write(response, json);
		
	}

}
