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
		System.out.println("�յ���֤������");
		String MethodName = request.getServletPath();
		MethodName = MethodName.substring(1, MethodName.length() - 4);
		System.out.println("MethodName��"+MethodName);
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
		// ����û���д����֤��
		String userVCode = request.getParameter("vCode");
		// ���session����֤��
		HttpSession session = request.getSession();
		String genVCode = (String) session.getAttribute("vCode");
		// �ж��Ƿ���ͬ
		String vailResult = "";
		if (genVCode != null) {
			if (genVCode.equalsIgnoreCase(userVCode)) {
				vailResult = "��֤ͨ����";
			} else {
				vailResult = "��֤�����";
			}
			// ��֤��ֻ������һ��
			session.removeAttribute("vCode");

		} else {
			vailResult = "��֤��ʧЧ��";
		}
		request.setAttribute("vailResult", vailResult);
		request.getRequestDispatcher("/checkResult.jsp").forward(request, response);
	}

	public void genImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//���ò�����ͼƬ
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		request.setCharacterEncoding("UTF-8");
		//ָ�����ɵ���ӦͼƬ,һ������ȱ����仰,�������.
		response.setContentType("image/jpeg");

		// ����ͼ��
		int width = 80, height = 40;
		//����BufferedImage����,�������൱��һͼƬ
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// ����ͼ�㣬��û���
		Graphics g = image.getGraphics();
	    //������ɫ
		g.setColor(Color.BLACK);
	    //��������
	    g.fillRect(0, 0, width, height);
		//�����߿�
	    g.setColor(Color.WHITE);
	    g.fillRect(1, 1, width-2, height-2);
	    
		  //����ַ�
	    String data = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefhjkmnpqrstuvwxyz2345678";
	    Random random=new Random();
	    g.setFont(new Font("����", Font.BOLD, 30)); //����������ʽ
	    StringBuffer buff = new StringBuffer();
	    //�������4���ַ�
	    for (int i = 0; i < 4; i++) {
	        int index = random.nextInt(53);
	        String str = data.substring(index, index + 1);
	        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	        g.drawString(str, 20 * i, 30);
	        buff.append(str);
	    }
	    
	    //���õ����ַ������浽session��
	    HttpSession session = request.getSession();
	    session.setAttribute("vCode", buff.toString());
		
	    //����10��������
	    for (int i = 0; i < 10; i++) {
	    	g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	        g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
	    }
		
		g.dispose();	//�ͷ�g��ռ�õ�ϵͳ��Դ
		ImageIO.write(image, "jpg", response.getOutputStream()); //���ͼƬ
	}

}
