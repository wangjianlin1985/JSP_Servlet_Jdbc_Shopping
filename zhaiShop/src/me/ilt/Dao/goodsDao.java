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
	 * 销量排行
	 * 根据销量查询 返回前5个商品  按销量
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
				System.out.println("相关商品: id="+id+"    name="+name+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, name, price, proPic);
				list.add(g);
				System.out.println("搜索页前5商品查询完毕");
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
	 * 分页查询  根据小类查询
	 * @param name 商品名称 
	 * @param p 需求页码
	 * @return
	 */
	public static PageBean sidPageSel(String sid,int p,String order){
		Connection conn = Conn.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PageBean pb = new PageBean();
		int count = 0; //查询的总条数
		String sql = "select count(*) count from t_goods where smallTypeId ="+sid;
		count = count(sql); //获取条数
		System.out.println("获取到查询的条数为："+count);
		pb.setCount(count); //放入总条数
		pb.setP(p); //放入当前页码
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
		System.out.println("分页查询语句为："+sql2);
		List<goodsBean> list = new ArrayList<goodsBean>(); //实例化一个商品集合
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				//图片 名称 价格 ID
				int id = rs.getInt("id");
				String sname = rs.getString("name");
				double price = rs.getDouble("price");
				String proPic = rs.getString("proPic");
				System.out.println("相关商品: id="+id+"    sname="+sname+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, sname, price, proPic);
				list.add(g);
			}
			pb.setData(list);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
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
	 * 分页查询  根据大类查询
	 * @param name 商品名称 
	 * @param p 需求页码
	 * @return
	 */
	public static PageBean bidPageSel(String bid,int p,String order){
		Connection conn = Conn.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PageBean pb = new PageBean();
		int count = 0; //查询的总条数
		String sql = "select count(*) count from t_goods where bigTypeId ="+bid;
		count = count(sql); //获取条数
		System.out.println("获取到查询的条数为："+count);
		pb.setCount(count); //放入总条数
		pb.setP(p); //放入当前页码
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
		System.out.println("分页查询语句为："+sql2);
		List<goodsBean> list = new ArrayList<goodsBean>(); //实例化一个商品集合
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				//图片 名称 价格 ID
				int id = rs.getInt("id");
				String sname = rs.getString("name");
				double price = rs.getDouble("price");
				String proPic = rs.getString("proPic");
				System.out.println("相关商品: id="+id+"    sname="+sname+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, sname, price, proPic);
				list.add(g);
			}
			pb.setData(list);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
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
	 * 分页查询  根据商品名称查询
	 * @param name 商品名称 
	 * @param p 需求页码
	 * @return
	 */
	public static PageBean pageSel(String name,int p,String order){
		Connection conn = Conn.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PageBean pb = new PageBean();
		int count = 0; //查询的总条数
		String sql = "select count(*) count from t_goods where name like '%"+name+"%'";
		count = count(sql); //获取条数
		System.out.println("获取到查询的条数为："+count);
		pb.setCount(count); //放入总条数
		pb.setP(p); //放入当前页码
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
		System.out.println("分页查询语句为："+sql2);
		List<goodsBean> list = new ArrayList<goodsBean>(); //实例化一个商品集合
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				//图片 名称 价格 ID
				int id = rs.getInt("id");
				String sname = rs.getString("name");
				double price = rs.getDouble("price");
				String proPic = rs.getString("proPic");
				System.out.println("相关商品: id="+id+"    sname="+name+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, sname, price, proPic);
				list.add(g);
			}
			pb.setData(list);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
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
	 * 浏览量加1
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
	 * 销量增加
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
	 * 减去库存
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
	 * 相关商品
	 * 根据大类id查询 返回前6个商品  按销量
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
				System.out.println("相关商品: id="+id+"    name="+name+"    price="+price+"    proPic="+proPic);
				goodsBean g = new  goodsBean(id, name, price, proPic);
				list.add(g);
				System.out.println("商品页相关商品6个查询完毕");
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
 * 根据ID返回商品详情
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
				String name = rs.getString("name");  //商品名称
				double price = rs.getDouble("price");  //商品价格
				String proPic = rs.getString("proPic");  //商品图片
				String brand = rs.getString("brand");  //商品品牌
				int sales = rs.getInt("sales");  //商品销量
				int views = rs.getInt("views");  //商品浏览量
				int stock =  rs.getInt("stock");  //商品库存
				String contents = rs.getString("contents");  //商品描述
				int bigTypeId = rs.getInt("bigTypeId");  //大类ID
				String bigTypeName = rs.getString("bigTypeName");  //大类名称
				int smallTypeId = rs.getInt("smallTypeId");  //小类ID
				String smallTypeName = rs.getString("smallTypeName");  //小类名称
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
 * 根据ID返回商品详情
 * @param id
 * @return
 */
public static goodsBean gwcGoodsIdSel(int id){
		//名称  单价  ID 图片
		String sql = "select name,price,proPic from t_goods where id=?";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		goodsBean goods = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");  //商品名称
				double price = rs.getDouble("price");  //商品价格
				String proPic = rs.getString("proPic");  //商品图片
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
	 * 根据大类id查询 返回前十个商品  按最新发布
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
	 * 查询sql 返回json集合
	 * @param sql
	 * @return
	 */
	public static JSONArray sel(String sql){
		System.out.println("sql查询语句："+sql);
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
	 * 查询总行数
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
		System.out.println("查询到的用户行数为："+i);
		return i;
	}
	/**
	 * 添加商品
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
	 * 修改数据
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
		System.out.println("数据库dao中sql:"+sql);
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
	 * 删除多个
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
		//goodsBean g = new goodsBean("西瓜", 1.2, "暂无图片", "瓦岗西瓜", 200, 2000, 500, "<p>这是西瓜的描述</p>", 1,4,"正常");
		//add(g);
		//goodsBean g = goodsIdSel(64);
		//System.out.println(g.getName()+g.getPrice()+g.getProPic()+g.getBrand()+g.getSales()+g.getViews()+g.getStock()+g.getContents()+g.getBigTypeId()+g.getBigTypeName()+g.getSmallTypeId()+g.getSmallTypeName());
		//pageSel("西瓜", 1);
		//stockJian(1, 100);
		addSales(20, 1);
	}
}
