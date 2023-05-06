package me.ilt.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import me.ilt.Bean.goodsBean;

public class SesListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("创建了session对象");
		HashMap<String, goodsBean> gwc = new HashMap<String, goodsBean>();
		arg0.getSession().setAttribute("gwc", gwc);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("销毁了session对象");
	}

}
