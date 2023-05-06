package me.ilt.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;

import me.ilt.Bean.bigTypeBean;
import me.ilt.Bean.goodsBean;
import me.ilt.Bean.shoppingCart;
import me.ilt.Bean.userBean;
import me.ilt.Dao.bigTypeDao;
import me.ilt.Dao.bigTypeDao;
import me.ilt.Dao.goodsDao;
import me.ilt.Dao.shoppingCartDao;
import me.ilt.Dao.usersDao;
import me.ilt.Util.responseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class goodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
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
		System.out.println("��������Ʒ��ѯ");
		String p = request.getParameter("page"); //����ҳ��
		String rows = request.getParameter("rows"); //ÿҳ������
		String sel = request.getParameter("name"); //����ǲ�ѯ�ⲻΪ��
		System.out.println("�յ�����"+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_goods";
			int count = goodsDao.count(sql); //��ȡ����
			
			JSONArray jsonArray = goodsDao.selAll(Integer.parseInt(p), Integer.parseInt(rows)); //��ȡdao���ص�json����
			
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
	 * ����ɾ�� and ���ɾ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("ids");
		
		System.out.println("���յ���Ϊ��"+id);
		int i = goodsDao.manyDel(id);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "ɾ��ʧ��");
		}else{
			result.put("success", "true");
		}
		responseUtil.write(response, result);
	}
	/**
	 * �����Ʒ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("��������Ʒ���");
		SmartUpload su=new SmartUpload();
		PageContext pc=JspFactory.getDefaultFactory().getPageContext(this, request, response,null, true, 8192, true);
		su.initialize(pc);//��ʼ��
		su.setMaxFileSize(1024*2048);//���ƴ�С2M
		try {
			su.setAllowedFilesList("jpg,JPG,png,PNG,bmp,gif,jpeg,txt,doc,xls,ppt");//�����ϴ�����
			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//�������ϴ�������
			su.upload();//�ϴ������	
			String name = su.getRequest().getParameter("name");
			String price = su.getRequest().getParameter("price");
			String brand = su.getRequest().getParameter("brand");
			String sales = su.getRequest().getParameter("sales");
			String views = su.getRequest().getParameter("views");
			String stock = su.getRequest().getParameter("stock");
			String state = su.getRequest().getParameter("state");
			String smallTypeId = su.getRequest().getParameter("smallTypeId");
			String bigTypeId = su.getRequest().getParameter("bigTypeId");
			String contents = su.getRequest().getParameter("contents");
			
			Files fs=su.getFiles();//��ȡ�����ϴ����ļ�����
			File f=fs.getFile(0);//��ȡ�ϴ����ļ�
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
			String url = "images/goodsImg/"+sdf.format(new Date())+"."+f.getFileExt();
			
			goodsBean g = new goodsBean(name, Double.parseDouble(price), url, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId), state);
			
			int i = goodsDao.add(g);
			JSONObject result=new JSONObject();
			if(i==0){
				result.put("errorMsg", "���ʧ��");
			}else{
				f.saveAs(url);//����
				result.put("success", "true");
			}
			responseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SmartUpload su=new SmartUpload();
		PageContext pc=JspFactory.getDefaultFactory().getPageContext(this, request, response,null, true, 8192, true);
		su.initialize(pc);//��ʼ��
		su.setMaxFileSize(1024*2048);//���ƴ�С2M
		try {
			su.setAllowedFilesList("jpg,png,bmp,gif,jpeg,txt,doc,xls,ppt");//�����ϴ�����
			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//�������ϴ�������
			su.upload();//�ϴ������
			
			
			String name = su.getRequest().getParameter("name");
			String price = su.getRequest().getParameter("price");
			String brand = su.getRequest().getParameter("brand");
			String sales = su.getRequest().getParameter("sales");
			String views = su.getRequest().getParameter("views");
			String stock = su.getRequest().getParameter("stock");
			String state = su.getRequest().getParameter("state");
			String smallTypeId = su.getRequest().getParameter("smallTypeId");
			String bigTypeId = su.getRequest().getParameter("bigTypeId");
			String contents = su.getRequest().getParameter("contents");
			int id = Integer.parseInt(request.getParameter("id"));
			Files fs=su.getFiles();//��ȡ�����ϴ����ļ�����
			File f=fs.getFile(0);//��ȡ�ϴ����ļ�
			if(f.getSize()==0){
				goodsBean g = new goodsBean(name, Double.parseDouble(price), null, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId), state);
				g.setId(id);
				int i = goodsDao.update(g);
				JSONObject result=new JSONObject();
				if(i==0){
					result.put("errorMsg", "����ʧ��");
				}else{
					result.put("success", "true");
				}
				responseUtil.write(response, result);
				//δ���յ�ͼƬ
				return;
			}else{
				//���յ�ͼƬ
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
				String url = "images/goodsImg/"+sdf.format(new Date())+"."+f.getFileExt();
				f.saveAs(url);//����
				goodsBean g = new goodsBean(name, Double.parseDouble(price), url, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId), state);
				g.setId(id);
				
				int i = goodsDao.update(g);
				JSONObject result=new JSONObject();
				if(i==0){
					result.put("errorMsg", "����ʧ��");
				}else{
					result.put("success", "true");
				}
				responseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void selList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "��ѡ��...");
		jsonArray.add(jsonObject);
		JSONArray jsonArray2 = bigTypeDao.selAll(1, 100); //��ȡdao���ص�json����
		jsonArray.addAll(jsonArray2);
		responseUtil.write(response, jsonArray);
		
	}
	/**
	 * ���빺�ﳵ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addShoppingCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username"); //��ȡ��¼���û���
		String id = request.getParameter("id"); //��ƷID
		int num =Integer.parseInt( request.getParameter("num")); //��Ʒ��ǰ����
		double price = Double.parseDouble(request.getParameter("price")); //��Ʒ����
		//��Ӧ
		String exsit = ""; // ������1  ��������0
		double fee = 0; //���ﳵȫ���ϼ�
		int status = 1;//��״   1
		int sum = 0; //���ﳵ��ȫ����Ʒ������
		String msg = "";
		DecimalFormat df=new DecimalFormat(".##");
		HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
		if(username==null){
			//û�е�¼
			System.out.println("�û�û�е�¼  ���ﳵ����session��");
			if(!gwc.containsKey(id)){
				goodsBean goods = new goodsBean();  //ʵ����һ����Ʒ����
				goods.setId(Integer.parseInt(id));
				goods.setNum(num);
				goods.setPrice(price);
				gwc.put(id, goods);
				exsit = "0"; //������
				msg = "���ޣ�����ɹ�";
			}else{
				exsit = "1";
				goodsBean hgoods = gwc.get(id); //�������  �Ӽ����л�ȡ��
				num = hgoods.getNum()+num;
				hgoods.setNum(num);
				gwc.put(id, hgoods); //���·���
				msg = "���У�����ɹ�";
			}
			
			Set keyList = gwc.keySet();
			Iterator it = keyList.iterator();
			
			while(it.hasNext()){
				String hid = (String)it.next();
				goodsBean hgoods = gwc.get(hid);
				sum+=hgoods.getNum(); //��ȡ������Ʒ������
				fee+=hgoods.getNum()*hgoods.getPrice(); //���ﳵ�ϼ�
			}
			JSONObject result=new JSONObject();
			result.put("exsit",exsit);
			result.put("fee", df.format(fee));
			result.put("status", status);
			result.put("num", num);
			result.put("sum", sum);
			result.put("msg", msg);
			
			System.out.println("exsit="+exsit+",fee="+df.format(fee)+",status="+status+",num="+num+",sum="+sum+",msg="+msg);
			responseUtil.write(response, result);
		}else{
			//�Ѿ���¼
			System.out.println("�û��Ѿ���¼  �û���Ϊ��"+username);
			int userId = usersDao.nameIsId(username); //��ȡ�û�ID
			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
			if(i>0){
				//���ڹ�����  ��������
				exsit = "1"; //����
				shoppingCartDao.updateNum(new shoppingCart(userId, Integer.parseInt(id), num, 0));
				num=num+i; //���µ�ǰ������Ŀ����
			}else{
				//������  ��ӹ�����
				exsit = "0"; //������
				shoppingCartDao.add(new shoppingCart(userId, Integer.parseInt(id), num, price));
			}
			
			//��ȡ���ﳵ�����й�����Ŀ
			List<shoppingCart> list = shoppingCartDao.selList(userId);
			for(shoppingCart s: list){
				sum+=s.getNum();
				fee+=s.getNum()*s.getGoodsPrice();
			}
			JSONObject result=new JSONObject();
			result.put("exsit",exsit);
			result.put("fee", df.format(fee));
			result.put("status", status);
			result.put("num", num);
			result.put("sum", sum);
			result.put("msg", msg);
			
			System.out.println("exsit="+exsit+",fee="+df.format(fee)+",status="+status+",num="+num+",sum="+sum+",msg="+msg);
			responseUtil.write(response, result);
		}
	}
	/**
	 * ɾ�����ﳵ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delShoppingCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username"); //��ȡ��¼���û���
		String id = request.getParameter("sort"); //Ҫ��������ƷID
		//��Ӧ
		int count = 0;//��Ʒ������
		double price = 0;//���ﳵʣ����Ʒ�ܼ۸�
		String num = null;
		int sum = 0;//ȫ����Ʒ����
		int status = 1;
		if(username==null){
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			System.out.println("���յ�Ҫɾ����IDΪ��"+id);
			gwc.remove(id);
			Set keyList = gwc.keySet();
			Iterator it = keyList.iterator();
			
			while(it.hasNext()){
				count++;
				String hid = (String)it.next();
				goodsBean hgoods = gwc.get(hid);
				sum+=hgoods.getNum(); //��ȡ������Ʒ������
				price+=hgoods.getNum()*hgoods.getPrice(); //���ﳵ�ϼ�
			}
			JSONObject result=new JSONObject();
			result.put("count", count);
			result.put("price", price);
			result.put("num", num);
			result.put("sum", sum);
			result.put("status", status);
			System.out.println("ִ�й��ﳵɾ��");
			System.out.println("count="+count+",price="+price+",status="+status+",num="+num+",sum="+sum);
			responseUtil.write(response, result);
		}else{
			int userId = usersDao.nameIsId(username); //��ȡ�û�ID
			shoppingCartDao.del(userId, Integer.parseInt(id));
			//��ȡ���ﳵ�����й�����Ŀ
			List<shoppingCart> list = shoppingCartDao.selList(userId);
			for(shoppingCart s: list){
				count++;
				sum+=s.getNum();
				price+=s.getNum()*s.getGoodsPrice();
			}
			
			JSONObject result=new JSONObject();
			result.put("count", count);
			result.put("price", price);
			result.put("num", num);
			result.put("sum", sum);
			result.put("status", status);
			System.out.println("ִ�й��ﳵɾ��");
			System.out.println("count="+count+",price="+price+",status="+status+",num="+num+",sum="+sum);
			responseUtil.write(response, result);
		}
	}
	public void jiaCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username"); //��ȡ��¼���û���
		String id = request.getParameter("sort"); //��ȡID
		if(username==null){
			System.out.println("�����˹��ﳵ��������");
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			goodsBean hgoods = gwc.get(id); //�������  �Ӽ����л�ȡ��
			hgoods.setNum(hgoods.getNum()+1);
			gwc.put(id, hgoods); //���·���
			System.out.println("�������һ��������"+id);
			//����JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			responseUtil.write(response, result);
		}else{
			int userId = usersDao.nameIsId(username); //��ȡ�û�ID
			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
			shoppingCartDao.updateNum(new shoppingCart(userId, Integer.parseInt(id), 1, 0));
			//����JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			responseUtil.write(response, result);
		}
		
		
		
	}
	public void jianCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�����˹��ﳵ��������");
		String username = (String)request.getSession().getAttribute("username"); //��ȡ��¼���û���
		String id = request.getParameter("sort"); //��ȡID
		
		if(username==null){
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			goodsBean hgoods = gwc.get(id); //�������  �Ӽ����л�ȡ��
			hgoods.setNum(hgoods.getNum()-1);
			gwc.put(id, hgoods); //���·���
			System.out.println("��ɼ���һ��������"+id);
			//����JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			responseUtil.write(response, result);
		}else{
			int userId = usersDao.nameIsId(username); //��ȡ�û�ID
			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
			shoppingCartDao.updateNum(new shoppingCart(userId, Integer.parseInt(id), -1, 0));
			//����JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			responseUtil.write(response, result);
		}
		
	}
	public void delCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username"); //��ȡ��¼���û���
		String id = request.getParameter("sort"); //Ҫ��������ƷID
		int sum = 0;
		if(username==null){
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			gwc.remove(id);
			sum = gwc.size();
			
			System.out.println("���ɾ�����ﳵ��Ʒ��"+id);
			//����JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			result.put("sum",sum);
			responseUtil.write(response, result);
			
		}else{
			int userId = usersDao.nameIsId(username); //��ȡ�û�ID
			shoppingCartDao.del(userId, Integer.parseInt(id));
			//��ȡ���ﳵ�����й�����Ŀ
			List<shoppingCart> list = shoppingCartDao.selList(userId);
			sum = list.size();
			
			//����JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			result.put("sum",sum);
			responseUtil.write(response, result);
		}
		
	}
}
