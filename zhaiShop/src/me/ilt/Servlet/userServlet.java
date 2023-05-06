package me.ilt.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.ilt.Bean.goodsBean;
import me.ilt.Bean.shoppingCart;
import me.ilt.Bean.userBean;
import me.ilt.Dao.shoppingCartDao;
import me.ilt.Dao.usersDao;
import me.ilt.Util.responseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class userServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�յ�����");
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
	/**
	 * ��ѯ���� and ģ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String p = request.getParameter("page"); //����ҳ��
		String rows = request.getParameter("rows"); //ÿҳ������
		String sel = request.getParameter("s_userName"); //����ǲ�ѯ�ⲻΪ��
		System.out.println("�յ�����"+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_user";
			int count = usersDao.count(sql); //��ȡ����
			
			JSONArray jsonArray = usersDao.selAll(Integer.parseInt(p), Integer.parseInt(rows)); //��ȡdao���ص�json����
			
			result.put("rows", jsonArray);
			result.put("total", count);
			responseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_user where userName like '%"+sel+"%'";
			System.out.println("��ѯ����sqlΪ��"+sql);
			int count = usersDao.count(sql); //��ȡ����
			JSONArray jsonArray = usersDao.nameSel(Integer.parseInt(p), Integer.parseInt(rows),sel); //��ȡdao���ص�json����
			result.put("rows", jsonArray);
			result.put("total", count);
			responseUtil.write(response, result);
		}
	}
	/**
	 * ����ɾ�� and ���ɾ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("ids");
		int s = id.indexOf(",");
		int i = 0;
		if(s!=-1){
			//�Ǹ�����
			i = usersDao.manyDel(id);
			/*String [] ids = id.split(",");
			for(String j : ids){
				usersDao.del(Integer.parseInt(j));
			}
			i = 1;*/
		}else{
			i = usersDao.del(Integer.parseInt(id));
		}
		System.out.println("���յ���Ϊ��"+id);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "ɾ��ʧ��");
		}else{
			result.put("success", "true");
		}
		responseUtil.write(response, result);
	}
	/**
	 * ����û�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("user.userName");
		String trueName = request.getParameter("user.trueName");
		String sex = request.getParameter("user.sex");
		String birthday = request.getParameter("user.birthday");
		String statusID = request.getParameter("user.statusID");
		String phone = request.getParameter("user.phone");
		String address = request.getParameter("user.address");
		String email = request.getParameter("user.email");
		String password = request.getParameter("user.password");
		System.out.println("����û����յ���"+userName+trueName+sex+birthday+statusID+phone+address+email+ password);
		
		userBean u = new userBean(userName, trueName, sex, birthday, statusID, phone, address, email, 1+"", password);
		int i = usersDao.add(u);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "ɾ��ʧ��");
		}else{
			result.put("success", "true");
		}
		responseUtil.write(response, result);
	}
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("user.userName");
		String trueName = request.getParameter("user.trueName");
		String sex = request.getParameter("user.sex");
		String birthday = request.getParameter("user.birthday");
		String statusID = request.getParameter("user.statusID");
		String phone = request.getParameter("user.phone");
		String address = request.getParameter("user.address");
		String email = request.getParameter("user.email");
		String password = request.getParameter("user.password");
		int id = Integer.parseInt(request.getParameter("user.id"));
		userBean u = new userBean(userName, trueName, sex, birthday, statusID, phone, address, email, 1+"", password);
		u.setId(id);
		int i = usersDao.update(u);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "ɾ��ʧ��");
		}else{
			result.put("success", "true");
		}
		responseUtil.write(response, result);
	}
	public void rege(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println("���յ�Ҫע����û���Ϊ��"+name);
		int i = usersDao.count("select count(*) count from t_user where userName = '"+name+"'");
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("success", "true");
		}else{
			result.put("success", "false");
		}
		responseUtil.write(response, result);
		
		
	}
	/**
	 * ע���û�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regeuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		userBean u = new userBean();
		u.setUserName(username);u.setPassword(password);
		int i = usersDao.adduser(u);
		if(i==1){
			//ע��ɹ�
			request.setAttribute("regeState", 1);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			//ע��ʧ��
			request.setAttribute("regeState", 2);
			request.getRequestDispatcher("rege.jsp").forward(request, response);
		}
		
	}
	/**
	 * ajax ��֤��У��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void yzm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jyzm = request.getParameter("jyzm"); //���յ�����֤��
		String yz = me.ilt.Util.yzm.getImg(request, response);
		JSONObject result=new JSONObject();
		if(jyzm.equalsIgnoreCase(yz)){
			result.put("yzm", "true");
		}else{
			result.put("yzm", "false");
		}
		responseUtil.write(response, result);
	}
	/**
	 * ����Ա��¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void adminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jyzm = request.getParameter("jyzm"); //���յ�����֤��
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String yz = me.ilt.Util.yzm.getImg(request, response);
		if(jyzm.equalsIgnoreCase(yz)){
			System.out.println("��֤����֤ͨ��");
			//��֤����ȷ
			String password2 = usersDao.adminLogin(name);
			System.out.println("���յ������֣�"+name);
			System.out.println("���յ�����Ϊ��"+password);
			System.out.println("���ص�����Ϊ��"+password2);
			if(password.equals(password2)){
				//������ȷ
				request.getSession().setAttribute("stateOK", 0);
				response.sendRedirect("admin/main.jsp");
				request.getSession().setAttribute("adminName", name);
			}else{
				//������� ���߷ǹ���Ա
				request.getSession().setAttribute("state", 2);
				response.sendRedirect("/zhaiShop/admin");
			}
		}else{
			//��֤�����
			request.getSession().setAttribute("state", 1);
			response.sendRedirect("/zhaiShop/admin");
		}
		
		
	}
	/**
	 * ����Ա�޸�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void adminXgmm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("adminName");
		int userId = usersDao.nameIsId(username); //��ȡ�û�ID
		String old = request.getParameter("oldPassword");
		String password = request.getParameter("newPassword");
		System.out.println("����Ա�޸����룺"+userId+old+password);
		
		int i = 0;
		i = usersDao.changePassword(userId, old, password);
		JSONObject result = new JSONObject();
		if(i==0){
			//�����޸�ʧ��
			result.put("error", true);
		}else{
			//�޸ĳɹ�
			result.put("success", true);
		}
		responseUtil.write(response, result);
	}
	
	/**
	 * �û���¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�����˵�½");
		String username = request.getParameter("username"); //���յ����û���
		String password = request.getParameter("password"); 
		JSONObject result=new JSONObject();
		String password2 = usersDao.login(username);
		if(password2==null){
			result.put("info", "�û������ڻ򱻽��ã�");
			result.put("status", 0);
			result.put("url", "");
		}else{
			if(password2.equals(password)){
				result.put("info", "��½�ɹ�");
				result.put("status", 1); //1��ɹ���½
				result.put("url", ""); 
				request.getSession().setAttribute("username", username);
				HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
				int userId = usersDao.nameIsId(username); //��ȡ�û�ID
				//�ж�δ��¼֮ǰ���ﳵ�Ƿ��й�����
				int gwcSize = gwc.size();
				if(gwcSize>0){
					Set keyList = gwc.keySet();
					Iterator it = keyList.iterator();
					
					while(it.hasNext()){
						String hid = (String)it.next();
						goodsBean hgoods = gwc.get(hid);
						int i = shoppingCartDao.count(userId, hgoods.getId());
						if(i>0){
							//���ڹ�����  ��������
							shoppingCartDao.updateNum(new shoppingCart(userId, hgoods.getId(), hgoods.getNum(), 0));
						}else{
							//������  ��ӹ�����
							shoppingCartDao.add(new shoppingCart(userId, hgoods.getId(), hgoods.getNum(), hgoods.getPrice()));
						}
					}
					gwc.clear(); //��յ�ǰsession�Ĺ��ﳵ
				}
			}else{
					result.put("info", "�������������");
					result.put("status", 0);
					result.put("url", "");
				}
		}
		responseUtil.write(response, result);
	}
	public void mainLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�������˵�½");
		String username = request.getParameter("username"); //���յ����û���
		String password = request.getParameter("password"); 
		String password2 = usersDao.login(username);
		
		if(password2.equals(password)){
			//��¼�ɹ�
			request.getSession().setAttribute("username", username);
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			int userId = usersDao.nameIsId(username); //��ȡ�û�ID
			//�ж�δ��¼֮ǰ���ﳵ�Ƿ��й�����
			int gwcSize = gwc.size();
			if(gwcSize>0){
				Set keyList = gwc.keySet();
				Iterator it = keyList.iterator();
				
				while(it.hasNext()){
					String hid = (String)it.next();
					goodsBean hgoods = gwc.get(hid);
					int i = shoppingCartDao.count(userId, hgoods.getId());
					if(i>0){
						//���ڹ�����  ��������
						shoppingCartDao.updateNum(new shoppingCart(userId, hgoods.getId(), hgoods.getNum(), 0));
					}else{
						//������  ��ӹ�����
						shoppingCartDao.add(new shoppingCart(userId, hgoods.getId(), hgoods.getNum(), hgoods.getPrice()));
					}
				}
				gwc.clear(); //��յ�ǰsession�Ĺ��ﳵ
			}
			response.sendRedirect("/zhaiShop"); //�ض�����ҳ
		}else{
			//��¼ʧ��������������
			request.getSession().setAttribute("loginFail", username);
			response.sendRedirect("login.jsp"); //�ض��򵽵�¼
			}
	}
	/**
	 * �û��޸�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void userDataUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String trueName = request.getParameter("trueName");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String statusID = request.getParameter("statusID");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		int userId = Integer.parseInt(request.getParameter("uid"));
		
		String username = (String)request.getSession().getAttribute("username");
		int userId2 = usersDao.nameIsId(username); //��ȡ�û�ID
		
		if(userId!=userId2){
			//�Ƿ�����
			request.getSession().invalidate();
			response.sendRedirect("login.jsp"); //�ض��򵽵�¼
		}
		userBean u = new userBean(null, trueName, sex, birthday, statusID, phone, null, email, null, null);
		u.setId(userId);
		int i = usersDao.userUpdate(u);
		JSONObject result = new JSONObject();
		if(i!=0){
			result.put("success", "true");
		}else{
			result.put("errorMsg", "����ʧ��");
		}
		responseUtil.write(response, result);
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
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		int userId = usersDao.nameIsId(username); //��ȡ�û�ID
		String old = request.getParameter("old");
		String password = request.getParameter("password");
		
		int i = 0;
		i = usersDao.changePassword(userId, old, password);
		JSONObject reslt = new JSONObject();
		if(i==0){
			//�����޸�ʧ��
			reslt.put("error", true);
		}else{
			//�޸ĳɹ�
			reslt.put("success", true);
		}
		responseUtil.write(response, reslt);
	}
	
	/**
	 * �˳���¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("/zhaiShop");
		
	}
}
