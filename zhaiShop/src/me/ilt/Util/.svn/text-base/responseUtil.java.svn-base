package me.ilt.Util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class responseUtil {
	public static void write(HttpServletResponse response,Object o){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(o.toString());
			System.out.println("servletÏìÓ¦Îª£º"+o.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
