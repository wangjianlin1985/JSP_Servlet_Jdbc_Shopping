package me.ilt.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import me.ilt.Bean.userBean;
import me.ilt.Dao.bigTypeDao;
import me.ilt.Dao.bigTypeDao;
import me.ilt.Util.responseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class bigTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String MethodName = request.getServletPath();
		MethodName = MethodName.substring(1, MethodName.length() - 4);
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
		System.out.println("进入了大类查询");
		String p = request.getParameter("page"); //需求页码
		String rows = request.getParameter("rows"); //每页多少条
		String sel = request.getParameter("s_userName"); //如果是查询这不为空
		System.out.println("收到请求："+p+"  "+rows+"  "+sel);
		if(sel==null){
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_bigType";
			int count = bigTypeDao.count(sql); //获取条数
			
			JSONArray jsonArray = bigTypeDao.selAll(Integer.parseInt(p), Integer.parseInt(rows)); //获取dao返回的json集合
			
			result.put("rows", jsonArray);
			result.put("total", count);
			responseUtil.write(response, result);
		}else{
			JSONObject result = new JSONObject();
			String sql = "select count(*) count from t_bigType where name like '%"+sel+"%'";
			System.out.println("查询行数sql为："+sql);
			int count = bigTypeDao.count(sql); //获取条数
			JSONArray jsonArray = bigTypeDao.nameSel(Integer.parseInt(p), Integer.parseInt(rows),sel); //获取dao返回的json集合
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
		int s = id.indexOf(",");
		int i = 0;
		if(s!=-1){
			//是个数组
			i = bigTypeDao.manyDel(id);
			/*String [] ids = id.split(",");
			for(String j : ids){
				bigTypeDao.del(Integer.parseInt(j));
			}
			i = 1;*/
		}else{
			i = bigTypeDao.del(Integer.parseInt(id));
		}
		System.out.println("接收到的为："+id);
		JSONObject result=new JSONObject();
		if(i==0){
			result.put("errorMsg", "删除失败");
		}else{
			result.put("success", "true");
		}
		responseUtil.write(response, result);
	}
	/**
	 * 添加大类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SmartUpload su=new SmartUpload();
		PageContext pc=JspFactory.getDefaultFactory().getPageContext(this, request, response,null, true, 8192, true);
		su.initialize(pc);//初始化
		su.setMaxFileSize(1024*2048);//限制大小2M
		try {
			su.setAllowedFilesList("jpg,png,bmp,gif,jpeg,txt,doc,xls,ppt");//允许上传类型
			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//不允许上传的类型
			su.upload();//上传及检测
			String name=su.getRequest().getParameter("productBigType.name");//获取表单组件nam的值
			String remarks=su.getRequest().getParameter("productBigType.remarks");
			Files fs=su.getFiles();//获取所有上传的文件集合
			File f=fs.getFile(0);//获取上传的文件
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
			String url = "images/bigTypeImg/"+sdf.format(new Date())+"."+f.getFileExt();
			f.saveAs(url);//保存
			bigTypeBean b = new bigTypeBean(name,remarks);
			b.setImgUrl(url);
			int i = bigTypeDao.add(b);
			JSONObject result=new JSONObject();
			if(i==0){
				result.put("errorMsg", "添加失败");
			}else{
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
			String name=su.getRequest().getParameter("productBigType.name");//获取表单组件nam的值
			String remarks=su.getRequest().getParameter("productBigType.remarks");
			int id = Integer.parseInt(request.getParameter("productBigType.id"));
			Files fs=su.getFiles();//获取所有上传的文件集合
			File f=fs.getFile(0);//获取上传的文件
			if(f.getSize()==0){
				bigTypeBean b = new bigTypeBean(name,remarks);
				b.setId(id);
				int i = bigTypeDao.update(b);
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
				String url = "images/bigTypeImg/"+sdf.format(new Date())+"."+f.getFileExt();
				f.saveAs(url);//保存
				bigTypeBean b = new bigTypeBean(name,remarks);
				b.setId(id);
				b.setImgUrl(url);
				int i = bigTypeDao.update(b);
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

}
