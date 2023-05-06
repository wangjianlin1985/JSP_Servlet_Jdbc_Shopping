package me.ilt.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.ilt.Bean.bigTypeBean;
import me.ilt.Bean.goodsBean;
import me.ilt.Bean.PageBean;
import me.ilt.Util.Conn;
import me.ilt.Util.jsonUtil;
import net.sf.json.JSONArray;

public class goodsDao {
	/**
	 * ��������
	 * ����������ѯ ����ǰ5����Ʒ  ������
	 * @param sql
	 * @return
	 */
	public static List salesTop(){
		String sql = "select top 5 id,name,price,proPic from t_goods  order by sales desc";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<goodsBean> list = new ArrayList<goodsBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				String proPic = rs.getString("proPic");
				System.out.println("�����Ʒ: id="+id+"    name="+name+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, name, price, proPic);
				list.add(g);
				System.out.println("����ҳǰ5��Ʒ��ѯ���");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	/**
	 * ��ҳ��ѯ  ����С���ѯ
	 * @param name ��Ʒ���� 
	 * @param p ����ҳ��
	 * @return
	 */
	public static PageBean sidPageSel(String sid,int p,String order){
		Connection conn = Conn.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PageBean pb = new PageBean();
		int count = 0; //��ѯ��������
		String sql = "select count(*) count from t_goods where smallTypeId ="+sid;
		count = count(sql); //��ȡ����
		System.out.println("��ȡ����ѯ������Ϊ��"+count);
		pb.setCount(count); //����������
		pb.setP(p); //���뵱ǰҳ��
		System.out.println("pb.getP()="+pb.getP());
		String sql2 = null;
		if(order.equals("1")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
				+"from t_goods "
				+"where smallTypeId ="+sid
				+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where smallTypeId ="+sid+" order by views desc) "
				+"order by views desc";
		}else if(order.equals("2")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
					+"from t_goods "
					+"where smallTypeId ="+sid
					+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where smallTypeId ="+sid+" order by id desc) "
					+"order by id desc";
		}else if(order.equals("3")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
					+"from t_goods "
					+"where smallTypeId ="+sid
					+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where smallTypeId ="+sid+" order by price desc) "
					+"order by price desc";
		}else if(order.equals("4")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
					+"from t_goods "
					+"where smallTypeId ="+sid
					+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where smallTypeId ="+sid+" order by sales desc) "
					+"order by sales desc";
		}
		System.out.println("��ҳ��ѯ���Ϊ��"+sql2);
		List<goodsBean> list = new ArrayList<goodsBean>(); //ʵ����һ����Ʒ����
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				//ͼƬ ���� �۸� ID
				int id = rs.getInt("id");
				String sname = rs.getString("name");
				double price = rs.getDouble("price");
				String proPic = rs.getString("proPic");
				System.out.println("�����Ʒ: id="+id+"    sname="+sname+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, sname, price, proPic);
				list.add(g);
			}
			pb.setData(list);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return pb;
	}
	/**
	 * ��ҳ��ѯ  ���ݴ����ѯ
	 * @param name ��Ʒ���� 
	 * @param p ����ҳ��
	 * @return
	 */
	public static PageBean bidPageSel(String bid,int p,String order){
		Connection conn = Conn.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PageBean pb = new PageBean();
		int count = 0; //��ѯ��������
		String sql = "select count(*) count from t_goods where bigTypeId ="+bid;
		count = count(sql); //��ȡ����
		System.out.println("��ȡ����ѯ������Ϊ��"+count);
		pb.setCount(count); //����������
		pb.setP(p); //���뵱ǰҳ��
		System.out.println("pb.getP()="+pb.getP());
		String sql2 = null;
		if(order.equals("1")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
				+"from t_goods "
				+"where bigTypeId ="+bid
				+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where bigTypeId ="+bid+" order by views desc) "
				+"order by views desc";
		}else if(order.equals("2")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
					+"from t_goods "
					+"where bigTypeId ="+bid
					+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where bigTypeId ="+bid+" order by id desc) "
					+"order by id desc";
		}else if(order.equals("3")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
					+"from t_goods "
					+"where bigTypeId ="+bid
					+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where bigTypeId ="+bid+" order by price desc) "
					+"order by price desc";
		}else if(order.equals("4")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
					+"from t_goods "
					+"where bigTypeId ="+bid
					+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where bigTypeId ="+bid+" order by sales desc) "
					+"order by sales desc";
		}
		System.out.println("��ҳ��ѯ���Ϊ��"+sql2);
		List<goodsBean> list = new ArrayList<goodsBean>(); //ʵ����һ����Ʒ����
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				//ͼƬ ���� �۸� ID
				int id = rs.getInt("id");
				String sname = rs.getString("name");
				double price = rs.getDouble("price");
				String proPic = rs.getString("proPic");
				System.out.println("�����Ʒ: id="+id+"    sname="+sname+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, sname, price, proPic);
				list.add(g);
			}
			pb.setData(list);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return pb;
	}
	/**
	 * ��ҳ��ѯ  ������Ʒ���Ʋ�ѯ
	 * @param name ��Ʒ���� 
	 * @param p ����ҳ��
	 * @return
	 */
	public static PageBean pageSel(String name,int p,String order){
		Connection conn = Conn.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PageBean pb = new PageBean();
		int count = 0; //��ѯ��������
		String sql = "select count(*) count from t_goods where name like '%"+name+"%'";
		count = count(sql); //��ȡ����
		System.out.println("��ȡ����ѯ������Ϊ��"+count);
		pb.setCount(count); //����������
		pb.setP(p); //���뵱ǰҳ��
		System.out.println("pb.getP()="+pb.getP());
		String sql2 = null;
		if(order.equals("1")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
				+"from t_goods "
				+"where name like '%"+name+"%' "
				+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where name like '%"+name+"%' order by views desc) "
				+"order by views desc";
		}else if(order.equals("2")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
					+"from t_goods "
					+"where name like '%"+name+"%' "
					+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where name like '%"+name+"%' order by id desc) "
					+"order by id desc";
		}else if(order.equals("3")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
					+"from t_goods "
					+"where name like '%"+name+"%' "
					+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where name like '%"+name+"%' order by price desc) "
					+"order by price desc";
		}else if(order.equals("4")){
			sql2 = "select top "+pb.getPagesize()+" id,name,price,proPic "
					+"from t_goods "
					+"where name like '%"+name+"%' "
					+" and name not in(select top "+(pb.getP()-1)*pb.getPagesize()+" name from t_goods where name like '%"+name+"%' order by sales desc) "
					+"order by sales desc";
		}
		System.out.println("��ҳ��ѯ���Ϊ��"+sql2);
		List<goodsBean> list = new ArrayList<goodsBean>(); //ʵ����һ����Ʒ����
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				//ͼƬ ���� �۸� ID
				int id = rs.getInt("id");
				String sname = rs.getString("name");
				double price = rs.getDouble("price");
				String proPic = rs.getString("proPic");
				System.out.println("�����Ʒ: id="+id+"    sname="+name+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, sname, price, proPic);
				list.add(g);
			}
			pb.setData(list);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return pb;
	}
	/**
	 * �������1
	 * @param id
	 */
	public static void addViews(int id){
		String sql = "update t_goods set views=views+1 where id = ?";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * ��������
	 * @param id
	 */
	public static void addSales(int id,int num){
		String sql = "update t_goods set sales=sales+? where id = ?";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setInt(2, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * ��ȥ���
	 * @param id
	 */
	public static void stockJian(int id,int num){
		String sql = "update t_goods set stock=stock-? where id = ?";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setInt(2, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * �����Ʒ
	 * ���ݴ���id��ѯ ����ǰ6����Ʒ  ������
	 * @param sql
	 * @return
	 */
	public static List bigTypeIdSelxg(int bigTypeId){
		String sql = "select top 6 id,name,price,proPic from t_goods where bigTypeId="+bigTypeId+"  order by sales desc";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<goodsBean> list = new ArrayList<goodsBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				String proPic = rs.getString("proPic");
				System.out.println("�����Ʒ: id="+id+"    name="+name+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, name, price, proPic);
				list.add(g);
				System.out.println("��Ʒҳ�����Ʒ6����ѯ���");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		 
	}
/**
 * ����ID������Ʒ����
 * @param id
 * @return
 */
public static goodsBean goodsIdSel(int id){
		
		String sql = "select *,t_bigType.name bigTypeName,t_smallType.name smallTypeName from t_goods,t_bigType,t_smallType where t_smallType.id = t_goods.smallTypeId and t_bigType.id = t_goods.bigTypeId and t_goods.id=?";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		goodsBean goods = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");  //��Ʒ����
				double price = rs.getDouble("price");  //��Ʒ�۸�
				String proPic = rs.getString("proPic");  //��ƷͼƬ
				String brand = rs.getString("brand");  //��ƷƷ��
				int sales = rs.getInt("sales");  //��Ʒ����
				int views = rs.getInt("views");  //��Ʒ�����
				int stock =  rs.getInt("stock");  //��Ʒ���
				String contents = rs.getString("contents");  //��Ʒ����
				int bigTypeId = rs.getInt("bigTypeId");  //����ID
				String bigTypeName = rs.getString("bigTypeName");  //��������
				int smallTypeId = rs.getInt("smallTypeId");  //С��ID
				String smallTypeName = rs.getString("smallTypeName");  //С������
				goods = new goodsBean(name, price, proPic, brand, sales, views, stock, contents, bigTypeId, smallTypeId, null);
				goods.setBigTypeName(bigTypeName);
				goods.setSmallTypeName(smallTypeName);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return goods;
	}
/**
 * ����ID������Ʒ����
 * @param id
 * @return
 */
public static goodsBean gwcGoodsIdSel(int id){
		//����  ����  ID ͼƬ
		String sql = "select name,price,proPic from t_goods where id=?";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		goodsBean goods = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");  //��Ʒ����
				double price = rs.getDouble("price");  //��Ʒ�۸�
				String proPic = rs.getString("proPic");  //��ƷͼƬ
				goods = new goodsBean();
				goods.setId(id);
				goods.setName(name);
				goods.setPrice(price);
				goods.setProPic(proPic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return goods;
	}
	/**
	 * ���ݴ���id��ѯ ����ǰʮ����Ʒ  �����·���
	 * @param sql
	 * @return
	 */
	public static List bigTypeIdSel(int bigTypeId){
		String sql = "select top 10 id,name,price,proPic from t_goods where bigTypeId="+bigTypeId+"  order by id desc";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<goodsBean> list = new ArrayList<goodsBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				String proPic = rs.getString("proPic");
				System.out.println("id="+id+"    name="+name+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, name, price, proPic);
				list.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		 
	}
	
	/**
	 * ��ѯsql ����json����
	 * @param sql
	 * @return
	 */
	public static JSONArray sel(String sql){
		System.out.println("sql��ѯ��䣺"+sql);
		Connection con = Conn.getCon();
		ResultSet rs = null;
		JSONArray jsonArray = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			jsonArray = jsonUtil.formatRsToJsonArray(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jsonArray;
		 
	}
	public static JSONArray nameSel(int p,int pageSize,String name){
		String sql = "select top "+pageSize+" t_goods.*,t_smallType.name smallTypeName,t_bigType.name bigTypeName "
				+"from t_goods,t_smallType,t_bigType where t_smallType.id = t_goods.smallTypeId and t_goods.name like '%"+name+"%' and t_bigType.id = t_goods.bigTypeId "
				+"and t_goods.id not in( select top "+(p-1)*pageSize+" t_goods.id from t_goods where name like '%"+name+"%' order by t_goods.id desc ) order by t_goods.id desc";
		return sel(sql);
	}
	public static JSONArray selAll(int p,int pageSize){
		String sql = "select top "+pageSize+" t_goods.*,t_smallType.name smallTypeName,t_bigType.name bigTypeName from t_goods,t_smallType,t_bigType "
				+"where t_smallType.id = t_goods.smallTypeId and t_bigType.id = t_goods.bigTypeId and "
				+"t_goods.id not in( "
				+"select top "+(p-1)*pageSize+" t_goods.id from t_goods order by t_goods.id desc "
				+") order by t_goods.id desc";
		return sel(sql);
	}
	/**
	 * ��ѯ������
	 * @return
	 */
	public static int count(String sql){
		Connection con = Conn.getCon();
		int i = 0;
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			if(rs!=null){
				i = rs.getInt("count");
			}else{
				i = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		System.out.println("��ѯ�����û�����Ϊ��"+i);
		return i;
	}
	/**
	 * �����Ʒ
	 * @param u
	 * @return
	 */
	public static int add(goodsBean u){
		String sql = "insert into t_goods values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setDouble(2, u.getPrice());
			ps.setString(3, u.getProPic());
			ps.setString(4, u.getBrand());
			ps.setInt(5, u.getSales());
			ps.setInt(6, u.getViews());
			ps.setInt(7, u.getStock());
			ps.setString(8, u.getContents());
			ps.setInt(9, u.getBigTypeId());
			ps.setInt(10, u.getSmallTypeId());
			ps.setString(11, u.getState());
			
			i = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	/**
	 * �޸�����
	 * @param u
	 * @return
	 */
	public static int update(goodsBean u){
		
		String sql = null;
		if(u.getProPic()==null){
			 sql = "update t_goods "
					+"set name=?,price=?,brand=?,sales=?,views=?,stock=?,contents=?,bigTypeId=?,smallTypeId=?,state=? "
					+"where id=? ";
		}else{
			 sql = "update t_goods "
					 +"set name=?,price=?,brand=?,sales=?,views=?,stock=?,contents=?,bigTypeId=?,smallTypeId=?,state=?,proPic=? "
					 +"where id=? ";
		}
		System.out.println("���ݿ�dao��sql:"+sql);
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setDouble(2, u.getPrice());
			ps.setString(3, u.getBrand());
			ps.setInt(4, u.getSales());
			ps.setInt(5, u.getViews());
			ps.setInt(6, u.getStock());
			ps.setString(7, u.getContents());
			ps.setInt(8, u.getBigTypeId());
			ps.setInt(9, u.getSmallTypeId());
			ps.setString(10, u.getState());
			if(u.getProPic()!=null){
				ps.setString(11, u.getProPic());
				ps.setInt(12, u.getId());
			}else{
				ps.setInt(11, u.getId());
			}
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	/**
	 * ɾ�����
	 * @param ids
	 * @return
	 */
	public static int manyDel(String ids){
		String sql = "delete from t_goods where id in("+ids+")";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	public static void main(String[] args) {
		//selAll(1,10);
		//goodsBean g = new goodsBean("����", 1.2, "����ͼƬ", "�߸�����", 200, 2000, 500, "<p>�������ϵ�����</p>", 1,4,"����");
		//add(g);
		//goodsBean g = goodsIdSel(64);
		//System.out.println(g.getName()+g.getPrice()+g.getProPic()+g.getBrand()+g.getSales()+g.getViews()+g.getStock()+g.getContents()+g.getBigTypeId()+g.getBigTypeName()+g.getSmallTypeId()+g.getSmallTypeName());
		//pageSel("����", 1);
		//stockJian(1, 100);
		addSales(20, 1);
	}
}
