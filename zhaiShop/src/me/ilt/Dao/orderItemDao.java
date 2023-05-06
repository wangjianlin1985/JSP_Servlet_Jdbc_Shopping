package me.ilt.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import me.ilt.Bean.goodsBean;
import me.ilt.Bean.orderItemBean;
import me.ilt.Util.Conn;
import me.ilt.Util.jsonUtil;

public class orderItemDao {
	/**
	 * 添加订单项目商品
	 * @param u
	 * @return
	 */
	public static int add(orderItemBean u){
		goodsDao.addSales(u.getGoodsId(), u.getSum()); //增加销量
		String sql = "insert into t_orderItem values(?,?,?,?,?,?,?)";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, u.getGoodsId());
			ps.setString(2, u.getGoodsName());
			ps.setString(3, u.getProPic());
			ps.setDouble(4, u.getGoodsPrice());
			ps.setInt(5, u.getSum());
			ps.setDouble(6, u.getSubTotal());
			ps.setString(7, u.getOrderId());
			
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
	 * 查询sql 返回json集合
	 * @param sql
	 * @return
	 */
	public static JSONArray orderIdSel(String orderId){
		String sql = "select * from t_orderItem where orderId=?";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		JSONArray jsonArray = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, orderId);
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
	 * 查询sql 返回goodsBean list集合
	 * @param sql
	 * @return
	 */
	public static List orderIdSelItem(String orderId){
		String sql = "select * from t_orderItem where orderId=?";
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<orderItemBean> list = new ArrayList<orderItemBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, orderId);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("goodsId");
				int sum = rs.getInt("sum");
				String proPic = rs.getString("proPic");
				String goodsName = rs.getString("goodsName");
				double price = rs.getDouble("goodsPrice");
				
				orderItemBean g = new orderItemBean();
				g.setGoodsId(id);g.setSum(sum);g.setGoodsPrice(price);g.setProPic(proPic);g.setGoodsName(goodsName);
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
	public static void main(String[] args) {
		orderItemBean o = new orderItemBean(1, "西瓜", "image", 1.2, 2, 2.4, "20151111");
		add(o);
	}
}
