package me.ilt.Servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.activation.URLDataSource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONObject;

import me.ilt.Bean.bigTypeBean;
import me.ilt.Dao.bigTypeDao;
import me.ilt.Util.responseUtil;

import com.jspsmart.upload.*;

public class UploadFile extends HttpServlet {
	
	public UploadFile() {
		System.out.println("上传图片执行了构造方法");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SmartUpload su=new SmartUpload();
		PageContext pc=JspFactory.getDefaultFactory().getPageContext(this, request, response,null, true, 8192, true);
			/*第一个参数是传递一个Servlet，在servlet中传递this就可以了；
			第二个和第三个参数是request与response不多说明了；
			第四个参数是发生错误后的url路径地址，如果没有可以键入null；
			第五个参数是是否需要session，这里可以写入true；
			第六个参数是缓存大小，我们用了8*1024；
			第七个参数是是否需要刷新，键入ture；*/
		su.initialize(pc);//初始化
		su.setMaxFileSize(1024*2048);//限制大小2M
		try {
			su.setAllowedFilesList("jpg,png,bmp,gif,jpeg,txt,doc,xls,ppt");//允许上传类型
			su.setDeniedFilesList("exe,bat,jsp,html,htm,java,class");//不允许上传的类型
			su.upload();//上传及检测
			String name=su.getRequest().getParameter("productBigType.name");//获取表单组件nam的值
			String remarks=su.getRequest().getParameter("productBigType.remarks");
			System.out.println("name="+name);
			System.out.println("remarks"+remarks);
			Files fs=su.getFiles();//获取所有上传的文件集合
			System.out.println("总字节数："+fs.getSize());
			File f=fs.getFile(0);//获取上传的文件
			System.out.println("文件全名"+f.getFilePathName());
			System.out.println("文件表单名称："+f.getFieldName());
			System.out.println("文件名："+f.getFileName());
			System.out.println("文件全名称："+f.getFilePathName());
			System.out.println("文件扩展名："+f.getFileExt());
			System.out.println("文件大小："+f.getSize());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH-mm-ss");
			String url = "/images/bigTypeImg/"+sdf.format(new Date())+"."+f.getFileExt();
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
			
			//response.sendRedirect("info.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
