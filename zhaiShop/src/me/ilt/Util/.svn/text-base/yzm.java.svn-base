package me.ilt.Util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class yzm extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("收到验证码请求");
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
	public static String getImg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		return (String) session.getAttribute("vCode");
	}
	public void valiCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// 获得用户填写的验证码
		String userVCode = request.getParameter("vCode");
		// 获得session中验证码
		HttpSession session = request.getSession();
		String genVCode = (String) session.getAttribute("vCode");
		// 判断是否相同
		String vailResult = "";
		if (genVCode != null) {
			if (genVCode.equalsIgnoreCase(userVCode)) {
				vailResult = "验证通过！";
			} else {
				vailResult = "验证码错误！";
			}
			// 验证码只允许用一次
			session.removeAttribute("vCode");

		} else {
			vailResult = "验证码失效！";
		}
		request.setAttribute("vailResult", vailResult);
		request.getRequestDispatcher("/checkResult.jsp").forward(request, response);
	}

	public void genImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		request.setCharacterEncoding("UTF-8");
		//指定生成的响应图片,一定不能缺少这句话,否则错误.
		response.setContentType("image/jpeg");

		// 创建图像
		int width = 80, height = 40;
		//创建BufferedImage对象,其作用相当于一图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 创建图层，获得画笔
		Graphics g = image.getGraphics();
	    //设置颜色
		g.setColor(Color.BLACK);
	    //画出矩形
	    g.fillRect(0, 0, width, height);
		//画出边框
	    g.setColor(Color.WHITE);
	    g.fillRect(1, 1, width-2, height-2);
	    
		  //填充字符
	    String data = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefhjkmnpqrstuvwxyz2345678";
	    Random random=new Random();
	    g.setFont(new Font("宋体", Font.BOLD, 30)); //定义字体样式
	    StringBuffer buff = new StringBuffer();
	    //随机生成4个字符
	    for (int i = 0; i < 4; i++) {
	        int index = random.nextInt(53);
	        String str = data.substring(index, index + 1);
	        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	        g.drawString(str, 20 * i, 30);
	        buff.append(str);
	    }
	    
	    //将得到的字符串保存到session中
	    HttpSession session = request.getSession();
	    session.setAttribute("vCode", buff.toString());
		
	    //画出10条干扰线
	    for (int i = 0; i < 10; i++) {
	    	g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	        g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
	    }
		
		g.dispose();	//释放g所占用的系统资源
		ImageIO.write(image, "jpg", response.getOutputStream()); //输出图片
	}

}
