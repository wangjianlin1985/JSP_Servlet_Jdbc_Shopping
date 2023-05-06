package me.ilt.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.ilt.Bean.addressBean;
import me.ilt.Bean.goodsBean;
import me.ilt.Bean.orderBean;
import me.ilt.Bean.orderItemBean;
import me.ilt.Dao.addressDao;
import me.ilt.Dao.bigTypeDao;
import me.ilt.Dao.goodsDao;
import me.ilt.Dao.orderDao;
import me.ilt.Dao.orderItemDao;
import me.ilt.Dao.usersDao;
import me.ilt.Util.responseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class orderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String MethodName = request.getServletPath();
		MethodName = MethodName.substring(1, MethodName.length() - 6);
		System.out.println("MethodName��"+MethodName);
		try {
			Method method = getClass().getDeclaredMethod(MethodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	/**
	 * ��ѯ���� and ģ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�����˶�����ѯ");
		String p = request.getParameter("page"); //����ҳ��
		String rows = request.getParameter("rows"); //ÿҳ������
		String sel = request.getParameter("name"); //����ǲ�ѯ�ⲻΪ��
		System.out.println("�յ�����"+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_order";
			int count = orderDao.count(sql); //��ȡ����
			
			JSONArray jsonArray = orderDao.selAll(Integer.parseInt(p), Integer.parseInt(rows)); //��ȡdao���ص�json����
			
			result.put("rows", jsonArray);
			result.put("total", count);
			responseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_goods where name like '%"+sel+"%'";
			System.out.println("��ѯ����sqlΪ��"+sql);
			int count = bigTypeDao.count(sql); //��ȡ����
			JSONArray jsonArray = goodsDao.nameSel(Integer.parseInt(p), Integer.parseInt(rows),sel); //��ȡdao���ص�json����
			result.put("rows", jsonArray);
			result.put("total", count);
			responseUtil.write(response, result);
		}
	}
	/**
	 * �ύ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void tjdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�������ύ����");
		String orderId = request.getParameter("tag");//�������
		String addressId = request.getParameter("sender");//��ַID
		String PayType = request.getParameter("PayType");//֧����ʽ
		String liuyan = request.getParameter("liuyan");//������Ϣ
		
		//���ݶ�����Ų�ѯ������Ʒ  ��ȥ���
		List<orderItemBean> list = orderItemDao.orderIdSelItem(orderId);
		for(orderItemBean g:list){
			goodsDao.stockJian(g.getId(), g.getSum()); //��ȥ���
		}
		
		//�޸Ķ�����״̬����Ϣ
		int i = orderDao.update(orderId, addressId, PayType, liuyan); 
		
		if(i==1){
			System.out.println("�޸Ķ���״̬�ɹ�");
			request.setAttribute("orderState", 1);
			request.getRequestDispatcher("orderState.jsp").forward(request, response);
		}else{
			System.out.println("�޸Ķ���״̬ʧ��");
		}
		
	}
	/**
	 * ȡ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void qxdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		String username = (String)request.getSession().getAttribute("username");
		int userId = usersDao.nameIsId(username); //�����û�����ȡID
		orderBean o = orderDao.orderIdSel(orderId);
		if(o.getUserId()==userId){
			//�޸Ķ���״̬
			orderDao.qxdd(orderId, 4);
			response.sendRedirect("userMain.page");
		}else{
			//�Ƿ�����
			response.sendRedirect("userMain.page");
		}
	}
	/**
	 * ȷ���ջ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void qdsh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		String username = (String)request.getSession().getAttribute("username");
		int userId = usersDao.nameIsId(username); //�����û�����ȡID
		orderBean o = orderDao.orderIdSel(orderId);
		if(o.getUserId()==userId){
			//�޸Ķ���״̬
			orderDao.qxdd(orderId, 5);
			response.sendRedirect("userMain.page");
		}else{
			//�Ƿ�����
			response.sendRedirect("userMain.page");
		}
	}
	/**
	 * ��������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ddfh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderNos");
		
		
		//�޸Ķ���״̬
		orderDao.qxdd(orderId, 3);
		
		//success
		JSONObject result = new JSONObject();
		
		result.put("success", true);
		responseUtil.write(response, result);
		
	}
	/**
	 * ֧������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void zfdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("������֧������");
		String orderId = request.getParameter("orderId");
		String username = (String)request.getSession().getAttribute("username");
		
		System.out.println("��ȡ���Ķ���idΪ��"+orderId);
		System.out.println("��ȡ���ĵ�ǰ��¼��Ϊ��"+username);
		
		int userId = usersDao.nameIsId(username); //�����û�����ȡID
		
		System.out.println("�����û�����ѯidΪ��"+userId);
		
		orderBean o = orderDao.orderIdSel(orderId);
		if(o.getUserId()==userId){
			System.out.println("��ǰ��¼��Ϊ����");
			DecimalFormat df = new DecimalFormat("#.00");  //��ֹ�۸�����쳣
			//��ѯ������Ϣ
			List<orderItemBean> list = orderItemDao.orderIdSelItem(orderId);
			List<goodsBean> goodsList = new ArrayList<goodsBean>();
			for(orderItemBean oi : list){
				System.out.println("��Ʒ����Ϊ��"+oi.getGoodsName()+"��ƷIDΪ��"+oi.getGoodsId());
				goodsBean hgoods = goodsDao.gwcGoodsIdSel(oi.getGoodsId()); //�����ݿ����
				System.out.println("С��δ��ʽ����"+oi.getSum()*hgoods.getPrice());
				double hj = Double.parseDouble(df.format(oi.getSum()*hgoods.getPrice()));
				System.out.println(oi.getGoodsName()+"С��Ϊ��"+hj);
				hgoods.setNum(oi.getSum());
				hgoods.setTotal(hj);
				goodsList.add(hgoods);
			}
			
			//�����û�ID��ȡ��ַ��Ϣ
			List<addressBean> addressList= addressDao.selAll(userId); 
			
			request.setAttribute("addressList", addressList); //��ַ
			request.setAttribute("ze", o.getTotal());  //�ܶ�
			request.setAttribute("gwcGoodsList", goodsList);  //���ﳵ�е���Ʒ
			request.setAttribute("addressId", orderId); //����id
			request.getRequestDispatcher("order.jsp").forward(request, response);  //ת����jsp
		}else{
			//�Ƿ�����
			response.sendRedirect("userMain.page");
		}
	}
	public void oidSel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�����˲鿴��������");
		String orderId = request.getParameter("orderNo");
		JSONObject result = new JSONObject();
		JSONArray jsonArray = orderItemDao.orderIdSel(orderId);
		
		result.put("rows", jsonArray);
		responseUtil.write(response, result);
		
	}

}
