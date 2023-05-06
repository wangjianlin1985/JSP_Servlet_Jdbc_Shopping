package me.ilt.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.ilt.Bean.collectBean;
import me.ilt.Bean.shoppingCart;
import me.ilt.Util.Conn;

public class collectDao {
	/**
	 * 添加收藏
	 * @param u
	 * @return
	 */
	public static int add(collectBean u){
		String sql = "insert into t_collect values(?,?,?,?,?,?)";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, u.getUserId());
			ps.setInt(2, u.getGoodsId());
			ps.setString(3, u.getGoodsName());
			ps.setString(4, u.getGoodsProPic());
			ps.setDouble(5, u.getGoodsPrice());
			ps.setString(6, u.getTime());
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
	 * 查询当前行数  是否存在收藏项目
	 * @return
	 */
	public static int count(int userId,int goodsId){
		String sql = "select count(*) count from t_collect where userId=? and goodsId=?";
		Connection con = Conn.getCon();
		int i = 0;
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, goodsId);
			rs = ps.executeQuery();
			
			if(rs.next()){
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
	 * 查询用户总收藏数 用于分页
	 * @return
	 */
	public static int count(int userId){
		String sql = "select count(*) count from t_collect where userId=?";
		Connection con = Conn.getCon();
		int i = 0;
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			if(rs.next()){
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
	 * 删除收藏
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	public static int del(int userId,int goodsId){
		String sql = "delete t_collect where userId=? and goodsId=?";
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
	 * 根据用户ID查询全部购物车项目
	 * 
	 */
	public static List selList(int userId,int page){
		String sql = "select top 10 * from t_collect where userId ="+userId
				+" and id not in(select top "+(page-1)*10+" id from t_collect where userId ="+userId+" order by time desc) "
				+" order by time desc";
		//String sql = "select * from t_collect where userId=?";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<collectBean> list = new ArrayList<collectBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				collectBean b = new collectBean(userId, rs.getInt("goodsId"), rs.getString("goodsName"), rs.getString("goodsProPic"), rs.getDouble("goodsPrice"), rs.getString("time"));
				System.out.println(b);
				list.add(b);
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
		//add(new collectBean(1, 1, "1", "1", 0, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		//count(10026, 18);
		selList(10026,2);
	}
}
