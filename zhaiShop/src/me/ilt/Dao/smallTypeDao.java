package me.ilt.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.ilt.Bean.bigTypeBean;
import me.ilt.Bean.smallTypeBean;
import me.ilt.Util.Conn;
import me.ilt.Util.jsonUtil;
import net.sf.json.JSONArray;

public class smallTypeDao {
	/**
	 * ����С��ID��ѯС������
	 * @return
	 */
	public static String sidIsName(String sid){
		String sql = "select name from t_smallType where id = "+sid;
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
	 * ������ID��ѯ ����json
	 * @param bigTypeId
	 * @return
	 */
	public static  JSONArray bigTypeIdsel(int bigTypeId){
		String sql = "select * from t_smallType where bigTypeId = "+bigTypeId;
		return sel(sql);
	}
	/**
	 * ��ѯȫ��
	 * @param p
	 * @param pageSize
	 * @return
	 */
	public static JSONArray selAll(int p,int pageSize){
		String sql = "select top "+pageSize+" t_smallType.*,t_bigType.name bigTypeName from t_smallType,t_bigType "
			+"where t_smallType.bigTypeId=t_bigType.id and "
			+"t_smallType.id not in(select top "+(p-1)*pageSize+" t_smallType.id from t_smallType,t_bigType "
			+"where t_smallType.bigTypeId=t_bigType.id)";
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
				
		String sql = "select top "+pageSize+" t_smallType.*,t_bigType.name bigTypeName from t_smallType,t_bigType "
				+"where t_smallType.bigTypeId=t_bigType.id and t_smallType.name like '%"+name+"%' and "
				+"t_smallType.id not in(select top "+(p-1)*pageSize+" t_smallType.id from t_smallType,t_bigType "
				+"where t_smallType.bigTypeId=t_bigType.id and t_smallType.name like '%"+name+"%')";

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
	 * ��ѯsql ����list����
	 * @param sql
	 * @return
	 */
	public static List bigTypeIdselList(int bigTypeId){
		String sql = "select * from t_smallType where bigTypeId = "+bigTypeId;
		Connection con = Conn.getCon();
		ResultSet rs = null;
		List<smallTypeBean> list = new ArrayList<smallTypeBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				smallTypeBean s = new smallTypeBean();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				System.out.println("С��ID��"+s.getId()+"    С�����ƣ�"+s.getName());
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
	public static int add(smallTypeBean u){
		String sql = "insert into t_smallType values(?,?,?)";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setInt(2, u.getBigTypeId());
			ps.setString(3, u.getRemarks());
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
		String sql = "delete t_smallType where id=?";
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
		String sql = "delete from t_smallType where id in("+ids+")";
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
	public static int update(smallTypeBean u){
		String sql = "update t_smallType "
			+"set name=?,remarks=?,bigTypeId=? "
			+"where id=? ";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getRemarks());
			ps.setInt(3, u.getBigTypeId());
			ps.setInt(4, u.getId());
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
	 * ��ѯ������
	 * @return
	 */
	public static List bigTypeIdIsSel(int bigTypeId){
		Connection con = Conn.getCon();
		String sql = "select * from t_smallType where bigTypeId = ?";
		ResultSet rs = null;
		List<smallTypeBean> list = new ArrayList<smallTypeBean>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bigTypeId);
			rs = ps.executeQuery();
			while(rs.next()){
				 int id = rs.getInt("id");
				 String name = rs.getString("name");
				 String remarks = rs.getString("remarks");
				 smallTypeBean s = new smallTypeBean(name, bigTypeId, remarks);
				 s.setId(id);
				 list.add(s);
				 System.out.println("id="+id+",name="+name+",remarks="+remarks+",bigTypeId="+bigTypeId);
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
		return list;
	}
	public static void main(String[] args) {
		//System.out.println(bidIsName("22"));
		//bigTypeIdIsSel(6);
	}
}
