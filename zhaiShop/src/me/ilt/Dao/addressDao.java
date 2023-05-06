package me.ilt.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import me.ilt.Bean.addressBean;
import me.ilt.Bean.goodsBean;
import me.ilt.Util.Conn;
import me.ilt.Util.jsonUtil;

public class addressDao {
	
	/**
	 * 添加收货地址
	 * @param u
	 * @return
	 */
	public static int add(addressBean u){
		String sql = "insert into t_address values(?,?,?,?,?,?,?,?)";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int id = 0;
		int i = 0;
		try {
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, u.getProvince());
			ps.setString(2, u.getCity());
			ps.setString(3, u.getArea());
			ps.setString(4, u.getAddress());
			ps.setString(5, u.getPhone());
			ps.setString(6, u.getUsername());
			ps.setInt(7, u.getMsg());
			ps.setInt(8, u.getUserId());
			
			i = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();//这一句代码就是得到插入的记录的id
			   while(rs.next()){
			    id=rs.getInt(1);
			   }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	/**
	 * 设置默认地址
	 * @param userId
	 * @param addressId
	 * @return
	 */
	public static int setDefeat(int userId,int addressId){
		String sql = "update t_address "
				+"set msg=? "
				+"where id=? and userId=? ";
			Connection con = Conn.getCon();
			PreparedStatement ps = null;
			int i = 0;
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, 1);
				ps.setInt(2, addressId);
				ps.setInt(3, userId);
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
	 * 去除其他的默认值
	 * @param userId
	 */
	public static void xgzt(int userId){
		String sql = "update t_address set msg=0 where userId="+userId;
		Connection con = Conn.getCon();
		Statement sta = null;
		try {
			sta = con.createStatement();
			sta.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				sta.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public static int del(int id){
		String sql = "delete t_address where id=?";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	/**
	 * 根据用户ID查询他的地址信息
	 * @param sql
	 * @return
	 */
	public static List selAll(int userId){
		String sql = "select * from t_address where userId ="+userId;
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<addressBean> list = new ArrayList<addressBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String province = rs.getString("province"); //省
				String city = rs.getString("city"); //市
				String area = rs.getString("area"); //县
				String address = rs.getString("address"); //详细地址
				String phone = rs.getString("phone");  //手机号
				String username = rs.getString("username"); //收货人姓名
				int msg = rs.getInt("msg");//是否是默认
				addressBean addre = new addressBean(province, city, area, address, phone, username, msg, userId);
				addre.setId(id);
				list.add(addre);
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
	 * 根据ID查询他的地址信息
	 * @param sql
	 * @return
	 */
	public static addressBean idSel(int id){
		String sql = "select * from t_address where id ="+id;
		Connection con = Conn.getCon();
		ResultSet rs = null;
		addressBean addre = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				String province = rs.getString("province"); //省
				String city = rs.getString("city"); //市
				String area = rs.getString("area"); //县
				String address = rs.getString("address"); //详细地址
				String phone = rs.getString("phone");  //手机号
				String username = rs.getString("username"); //收货人姓名
				int msg = rs.getInt("msg");//是否是默认
				addre = new addressBean(province, city, area, address, phone, username, msg, 0);
				System.out.println(addre);
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
		return addre;
	}
	
	public static void main(String[] args) {
		//add(new addressBean("河南省", "驻马店市", "确山县", "卧龙家园", "15638377962", "刘振兴",0,10006));
		//xgzt(10006);
		//sel(10006);
		//idSel(2);
	}
}
