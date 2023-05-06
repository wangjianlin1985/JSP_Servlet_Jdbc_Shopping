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
		// TODO �Զ����ɵķ������
		System.out.println("application��������");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("application������ʼ��");
		//��ȡ�����Ķ���  ������ҳ������
		List<bigTypeBean> floor = bigTypeDao.selList(); //��ȡ¥����༯��
		List<bigTypeBean> bigTypes = bigTypeDao.bigselList(); //��ȡ���༰С�༶��
		List<slideBean> slideList = slideDao.selList();  //��ȡ�õ�ͼƬ����
		
		ServletContext application = arg0.getServletContext();
		application.setAttribute("floor", floor);
		application.setAttribute("slideList", slideList);
		application.setAttribute("bigTypes", bigTypes);
		System.out.println("�ѷ���application");
	}

}
