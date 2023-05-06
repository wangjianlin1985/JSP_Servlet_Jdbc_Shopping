package me.ilt.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.ilt.Bean.bigTypeBean;
import me.ilt.Bean.goodsBean;
import me.ilt.Bean.shoppingCart;
import me.ilt.Bean.smallTypeBean;
import me.ilt.Util.Conn;

public class shoppingCartDao {
	/**
	 * 添加购物项
	 * @param u
	 * @return
	 */
	public static int add(shoppingCart u){
		String sql = "insert into t_shoppingCart values(?,?,?,?)";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, u.getUserId());
			ps.setInt(2, u.getGoodsId());
			ps.setInt(3, u.getNum());
			ps.setDouble(4, u.getGoodsPrice());
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
	 * 根据用户ID和商品ID删除
	 * @param id
	 * @return
	 */
	public static int del(int userId,int goodsId){
		String sql = "delete t_shoppingCart where userId=? and goodsId=?";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, goodsId);
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
	 * 查询当前行数  是否存在购物项目
	 * @return
	 */
	public static int count(int userId,int goodsId){
		String sql = "select num from t_shoppingCart where userId=? and goodsId=?";
		Connection con = Conn.getCon();
		int i = 0;
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, goodsId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				i = rs.getInt("num");
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
	 * 修改购物项数量
	 * @param u
	 * @return
	 */
	public static int updateNum(shoppingCart u){
		String sql = "update t_shoppingCart set num=num+? where userId=? and goodsId=?";
		
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, u.getNum());
			ps.setInt(2, u.getUserId());
			ps.setInt(3, u.getGoodsId());
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
	 * 根据用户ID查询全部购物车项目
	 * 
	 */
	public static List selList(int userId){
		String sql = "select * from t_shoppingCart where userId=?";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<shoppingCart> list = new ArrayList<shoppingCart>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				shoppingCart s = new shoppingCart(userId, rs.getInt("goodsId"), rs.getInt("num"), rs.getDouble("goodsPrice"));
				System.out.println(s);
				list.add(s);
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
	 * 根据用户ID查询全部购物车项目
	 * 
	 */
	public static shoppingCart goodsIdSel(int userId,int goodsId){
		String sql = "select * from t_shoppingCart where userId=? and goodsId=?";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		shoppingCart s = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, goodsId);
			rs = ps.executeQuery();
			if(rs.next()){
				s = new shoppingCart(userId, rs.getInt("goodsId"), rs.getInt("num"), rs.getDouble("goodsPrice"));
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
		return s;
		
	}
	public static void main(String[] args) {
		//add(new shoppingCart(10001,12,1,12.6));
		//del(10001, 12);
		count(10001, 12);
		//updateNum(new shoppingCart(10001, 12, 2, 0));
		//selList(10001);
	}
}
