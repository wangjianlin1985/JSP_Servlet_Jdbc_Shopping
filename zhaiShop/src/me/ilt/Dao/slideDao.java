package me.ilt.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.ilt.Bean.slideBean;
import me.ilt.Util.Conn;
import me.ilt.Util.jsonUtil;
import net.sf.json.JSONArray;

public class slideDao {
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
	/**
	 * 查询全部
	 * @param p
	 * @param pageSize
	 * @return
	 */
	public static JSONArray selAll(){
		String sql = "select * from t_slide";
		return sel(sql);
	}
	/**
	 * 修改数据
	 * @param u
	 * @return
	 */
	public static int update(int id,String name,String url,String proPic){
		String sql = null;
		if(proPic==null){
			 sql = "update t_slide "
					+"set name=?,url=? "
					+"where id=? ";
		}else{
			 sql = "update t_slide "
					+"set name=?,url=?,proPic=? "
					+"where id=? ";
		}
		System.out.println("数据库dao中sql:"+sql);
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, url);
			if(proPic!=null){
				ps.setInt(4, id);
				ps.setString(3, proPic);
			}else{
				ps.setInt(3, id);
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
	 * 查询所有幻灯
	 * @param sql
	 * @return slide 幻灯对象集合
	 */
	public static List selList(){
		String sql = "select * from t_slide";
		System.out.println("sql查询语句："+sql);
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<slideBean> list = new ArrayList<slideBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");
				String url = rs.getString("url");
				String proPic = rs.getString("proPic");
				System.out.println("name="+name+",url="+url+",proPic="+proPic);
				slideBean slide = new slideBean(name, proPic, url);
				list.add(slide);
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
	public static void main(String[] args) {
		selList();
	}
}
