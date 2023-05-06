package me.ilt.Util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import me.ilt.Bean.bigTypeBean;
import me.ilt.Bean.slideBean;
import me.ilt.Dao.bigTypeDao;
import me.ilt.Dao.slideDao;

public class applicationListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("application容器销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("application容器初始化");
		//获取上下文对象  放入首页的内容
		List<bigTypeBean> floor = bigTypeDao.selList(); //获取楼层大类集合
		List<bigTypeBean> bigTypes = bigTypeDao.bigselList(); //获取大类及小类级联
		List<slideBean> slideList = slideDao.selList();  //获取幻灯图片集合
		
		ServletContext application = arg0.getServletContext();
		application.setAttribute("floor", floor);
		application.setAttribute("slideList", slideList);
		application.setAttribute("bigTypes", bigTypes);
		System.out.println("已放入application");
	}

}
