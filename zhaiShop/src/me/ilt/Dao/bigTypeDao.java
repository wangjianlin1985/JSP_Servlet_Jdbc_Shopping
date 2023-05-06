package me.ilt.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.ilt.Bean.bigTypeBean;
import me.ilt.Bean.goodsBean;
import me.ilt.Bean.slideBean;
import me.ilt.Bean.smallTypeBean;
import me.ilt.Util.Conn;
import me.ilt.Util.jsonUtil;
import net.sf.json.JSONArray;

public class bigTypeDao {
	/**
	 * ���ݴ���ID��ѯ��������
	 * @return
	 */
	public static String bidIsName(String bid){
		String sql = "select name from t_bigType where id = "+bid;
		Connection con = Conn.getCon();
		ResultSet rs = null;
		String name = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			name = rs.getString("name");
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
		return name;
	}
	/**
	 * ��ѯȫ��
	 * @param p
	 * @param pageSize
	 * @return
	 */
	public static JSONArray selAll(int p,int pageSize){
		String sql = "select top "+pageSize+" * from t_bigType where id not in(select top "+(p-1)*pageSize+" id from t_bigType)";
		return sel(sql);
	}
	/**
	 * �����ǳƲ�ѯ
	 * @param p
	 * @param pageSize
	 * @param username
	 * @return
	 */
	public static JSONArray nameSel(int p,int pageSize,String name){
		String sql = "select top "+pageSize+" * from t_bigType where id not in(select top "+(p-1)*pageSize+" id from t_bigType where userName like '%"+name+"%') and userName like '%"+name+"%'";
		return sel(sql);
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
	 * ����û�
	 * @param u
	 * @return
	 */
	public static int add(bigTypeBean u){
		String sql = "insert into t_bigType values(?,?,?)";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getRemarks());
			ps.setString(3, u.getImgUrl());
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
	 * ����IDɾ��
	 * @param id
	 * @return
	 */
	public static int del(int id){
		System.out.println("���ܵ�Ҫɾ����ID��"+id);
		String sql = "delete t_bigType where id=?";
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
	 * ɾ�����
	 * @param ids
	 * @return
	 */
	public static int manyDel(String ids){
		String sql = "delete from t_bigType where id in("+ids+")";
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
	/**
	 * �޸�����
	 * @param u
	 * @return
	 */
	public static int update(bigTypeBean u){
		String sql = null;
		if(u.getImgUrl()==null){
			 sql = "update t_bigType "
					+"set name=?,remarks=? "
					+"where id=? ";
		}else{
			 sql = "update t_bigType "
					+"set name=?,remarks=?,proPic=? "
					+"where id=? ";
		}
		System.out.println("���ݿ�dao��sql:"+sql);
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getRemarks());
			if(u.getImgUrl()!=null){
				ps.setInt(4, u.getId());
				ps.setString(3, u.getImgUrl());
			}else{
				ps.setInt(3, u.getId());
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
	 * ��ѯ���д���  ��С�� ������ǰʮ��Ʒ
	 * @return bigTypeʵ�弯��
	 */
	public static List selList(){
		String sql = "select * from t_bigType";
		System.out.println("sql��ѯ��䣺"+sql);
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<bigTypeBean> list = new ArrayList<bigTypeBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String proPic = rs.getString("proPic");
				System.out.println("id="+id+",name="+name+"proPic="+proPic);
				ArrayList<smallTypeBean> smallTypeList = (ArrayList<smallTypeBean>) smallTypeDao.bigTypeIdselList(id);
				ArrayList<goodsBean> goodsList = (ArrayList<goodsBean>) goodsDao.bigTypeIdSel(id);
				
				bigTypeBean bigType = new bigTypeBean();
				bigType.setId(id);
				bigType.setName(name);
				bigType.setImgUrl(proPic);
				bigType.setSmallTypeList(smallTypeList);  //����С�༯��
				bigType.setGoods(goodsList);  //����ǰʮ��Ʒ
				
				list.add(bigType);
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
	 * ��ѯ���д���  ��С��  ����ҳ�����
	 * @return bigTypeʵ�弯��
	 */
	public static List bigselList(){
		String sql = "select * from t_bigType";
		System.out.println("sql��ѯ��䣺"+sql);
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<bigTypeBean> list = new ArrayList<bigTypeBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println("id="+id+",name="+name);
				ArrayList<smallTypeBean> smallTypeList = (ArrayList<smallTypeBean>) smallTypeDao.bigTypeIdselList(id);
				bigTypeBean bigType = new bigTypeBean();
				bigType.setId(id);
				bigType.setName(name);
				bigType.setSmallTypeList(smallTypeList);  //����С�༯��
				list.add(bigType);
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
		//selList();
		//System.out.println(bidIsName("1"));
		
	}
}
