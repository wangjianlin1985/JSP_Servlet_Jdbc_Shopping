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
		System.out.println("MethodName："+MethodName);
		try {
			Method method = getClass().getDeclaredMethod(MethodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	/**
	 * 查询所有 and 模糊搜索
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了商品查询");
		String p = request.getParameter("page"); //需求页码
		String rows = request.getParameter("rows"); //每页多少条
		String sel = request.getParameter("name"); //如果是查询这不为空
		System.out.println("收到请求："+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_goods";
			int count = goodsDao.count(sql); //获取条数
			
			JSONArray jsonArray = goodsDao.selAll(Integer.parseInt(p), Integer.parseInt(rows)); //获取dao返回的json集合
			
			result.put("rows", jsonArray);
			result.put("total", count);
			responseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_goods where name like '%"+sel+"%'";
			System.out.println("查询行数sql为："+sql);
			int count = bigTypeDao.count(sql); //获取条数
			JSONArray jsonArray = goodsDao.nameSel(Integer.parseInt(p), Integer.parseInt(rows),sel); //获取dao返回的json集合
			result.put("rows", jsonArray);
			result.put("total", count);
			responseUtil.write(response, result);
		}
	}
	/**
	 * 单个删除 and 多个删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("ids");
		
		System.out.println("接收到的为："+id);
		int i = goodsDao.manyDel(id);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "删除失败");
		}else{
			result.put("success", "true");
		}
		responseUtil.write(response, result);
	}
	/**
	 * 添加商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了商品添加");
		SmartUpload su=new SmartUpload();
		PageContext pc=JspFactory.getDefaultFactory().getPageContext(this, request, response,null, true, 8192, true);
		su.initialize(pc);//初始化
		su.setMaxFileSize(1024*2048);//限制大小2M
		try {
			su.setAllowedFilesList("jpg,JPG,png,PNG,bmp,gif,jpeg,txt,doc,xls,ppt");//允许上传类型
			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//不允许上传的类型
			su.upload();//上传及检测	
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
			
			Files fs=su.getFiles();//获取所有上传的文件集合
			File f=fs.getFile(0);//获取上传的文件
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
			String url = "images/goodsImg/"+sdf.format(new Date())+"."+f.getFileExt();
			
			goodsBean g = new goodsBean(name, Double.parseDouble(price), url, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId), state);
			
			int i = goodsDao.add(g);
			JSONObject result=new JSONObject();
			if(i==0){
				result.put("errorMsg", "添加失败");
			}else{
				f.saveAs(url);//保存
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
		su.initialize(pc);//初始化
		su.setMaxFileSize(1024*2048);//限制大小2M
		try {
			su.setAllowedFilesList("jpg,png,bmp,gif,jpeg,txt,doc,xls,ppt");//允许上传类型
			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//不允许上传的类型
			su.upload();//上传及检测
			
			
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
			Files fs=su.getFiles();//获取所有上传的文件集合
			File f=fs.getFile(0);//获取上传的文件
			if(f.getSize()==0){
				goodsBean g = new goodsBean(name, Double.parseDouble(price), null, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId), state);
				g.setId(id);
				int i = goodsDao.update(g);
				JSONObject result=new JSONObject();
				if(i==0){
					result.put("errorMsg", "更新失败");
				}else{
					result.put("success", "true");
				}
				responseUtil.write(response, result);
				//未接收到图片
				return;
			}else{
				//接收到图片
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
				String url = "images/goodsImg/"+sdf.format(new Date())+"."+f.getFileExt();
				f.saveAs(url);//保存
				goodsBean g = new goodsBean(name, Double.parseDouble(price), url, brand, Integer.parseInt(sales), Integer.parseInt(views), Integer.parseInt(stock), contents, Integer.parseInt(bigTypeId), Integer.parseInt(smallTypeId), state);
				g.setId(id);
				
				int i = goodsDao.update(g);
				JSONObject result=new JSONObject();
				if(i==0){
					result.put("errorMsg", "更新失败");
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
		jsonObject.put("name", "请选择...");
		jsonArray.add(jsonObject);
		JSONArray jsonArray2 = bigTypeDao.selAll(1, 100); //获取dao返回的json集合
		jsonArray.addAll(jsonArray2);
		responseUtil.write(response, jsonArray);
		
	}
	/**
	 * 放入购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addShoppingCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username"); //获取登录的用户名
		String id = request.getParameter("id"); //商品ID
		int num =Integer.parseInt( request.getParameter("num")); //商品当前数量
		double price = Double.parseDouble(request.getParameter("price")); //商品单价
		//响应
		String exsit = ""; // 存在是1  不存在是0
		double fee = 0; //购物车全部合计
		int status = 1;//现状   1
		int sum = 0; //购物车中全部商品的数量
		String msg = "";
		DecimalFormat df=new DecimalFormat(".##");
		HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
		if(username==null){
			//没有登录
			System.out.println("用户没有登录  购物车放入session中");
			if(!gwc.containsKey(id)){
				goodsBean goods = new goodsBean();  //实例化一个商品对象
				goods.setId(Integer.parseInt(id));
				goods.setNum(num);
				goods.setPrice(price);
				gwc.put(id, goods);
				exsit = "0"; //不存在
				msg = "本无，插入成功";
			}else{
				exsit = "1";
				goodsBean hgoods = gwc.get(id); //如果存在  从集合中获取到
				num = hgoods.getNum()+num;
				hgoods.setNum(num);
				gwc.put(id, hgoods); //重新放入
				msg = "本有，插入成功";
			}
			
			Set keyList = gwc.keySet();
			Iterator it = keyList.iterator();
			
			while(it.hasNext()){
				String hid = (String)it.next();
				goodsBean hgoods = gwc.get(hid);
				sum+=hgoods.getNum(); //获取所有商品的数量
				fee+=hgoods.getNum()*hgoods.getPrice(); //购物车合计
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
			//已经登录
			System.out.println("用户已经登录  用户名为："+username);
			int userId = usersDao.nameIsId(username); //获取用户ID
			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
			if(i>0){
				//存在购物项  增加数量
				exsit = "1"; //存在
				shoppingCartDao.updateNum(new shoppingCart(userId, Integer.parseInt(id), num, 0));
				num=num+i; //更新当前购物项目数量
			}else{
				//不存在  添加购物项
				exsit = "0"; //不存在
				shoppingCartDao.add(new shoppingCart(userId, Integer.parseInt(id), num, price));
			}
			
			//获取购物车中所有购物项目
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
	 * 删除购物车购物项
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delShoppingCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username"); //获取登录的用户名
		String id = request.getParameter("sort"); //要操作的商品ID
		//响应
		int count = 0;//商品类总数
		double price = 0;//购物车剩余商品总价格
		String num = null;
		int sum = 0;//全部商品总数
		int status = 1;
		if(username==null){
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			System.out.println("接收到要删除的ID为："+id);
			gwc.remove(id);
			Set keyList = gwc.keySet();
			Iterator it = keyList.iterator();
			
			while(it.hasNext()){
				count++;
				String hid = (String)it.next();
				goodsBean hgoods = gwc.get(hid);
				sum+=hgoods.getNum(); //获取所有商品的数量
				price+=hgoods.getNum()*hgoods.getPrice(); //购物车合计
			}
			JSONObject result=new JSONObject();
			result.put("count", count);
			result.put("price", price);
			result.put("num", num);
			result.put("sum", sum);
			result.put("status", status);
			System.out.println("执行购物车删除");
			System.out.println("count="+count+",price="+price+",status="+status+",num="+num+",sum="+sum);
			responseUtil.write(response, result);
		}else{
			int userId = usersDao.nameIsId(username); //获取用户ID
			shoppingCartDao.del(userId, Integer.parseInt(id));
			//获取购物车中所有购物项目
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
			System.out.println("执行购物车删除");
			System.out.println("count="+count+",price="+price+",status="+status+",num="+num+",sum="+sum);
			responseUtil.write(response, result);
		}
	}
	public void jiaCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username"); //获取登录的用户名
		String id = request.getParameter("sort"); //获取ID
		if(username==null){
			System.out.println("进入了购物车增加数量");
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			goodsBean hgoods = gwc.get(id); //如果存在  从集合中获取到
			hgoods.setNum(hgoods.getNum()+1);
			gwc.put(id, hgoods); //重新放入
			System.out.println("完成增加一个数量："+id);
			//返回JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			responseUtil.write(response, result);
		}else{
			int userId = usersDao.nameIsId(username); //获取用户ID
			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
			shoppingCartDao.updateNum(new shoppingCart(userId, Integer.parseInt(id), 1, 0));
			//返回JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			responseUtil.write(response, result);
		}
		
		
		
	}
	public void jianCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入了购物车减少数量");
		String username = (String)request.getSession().getAttribute("username"); //获取登录的用户名
		String id = request.getParameter("sort"); //获取ID
		
		if(username==null){
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			goodsBean hgoods = gwc.get(id); //如果存在  从集合中获取到
			hgoods.setNum(hgoods.getNum()-1);
			gwc.put(id, hgoods); //重新放入
			System.out.println("完成减少一个数量："+id);
			//返回JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			responseUtil.write(response, result);
		}else{
			int userId = usersDao.nameIsId(username); //获取用户ID
			int i = shoppingCartDao.count(userId, Integer.parseInt(id));
			shoppingCartDao.updateNum(new shoppingCart(userId, Integer.parseInt(id), -1, 0));
			//返回JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			responseUtil.write(response, result);
		}
		
	}
	public void delCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String)request.getSession().getAttribute("username"); //获取登录的用户名
		String id = request.getParameter("sort"); //要操作的商品ID
		int sum = 0;
		if(username==null){
			HashMap<String, goodsBean> gwc = (HashMap)request.getSession().getAttribute("gwc");
			gwc.remove(id);
			sum = gwc.size();
			
			System.out.println("完成删除购物车商品："+id);
			//返回JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			result.put("sum",sum);
			responseUtil.write(response, result);
			
		}else{
			int userId = usersDao.nameIsId(username); //获取用户ID
			shoppingCartDao.del(userId, Integer.parseInt(id));
			//获取购物车中所有购物项目
			List<shoppingCart> list = shoppingCartDao.selList(userId);
			sum = list.size();
			
			//返回JSON
			JSONObject result=new JSONObject();
			result.put("st",1);
			result.put("sum",sum);
			responseUtil.write(response, result);
		}
		
	}
}
