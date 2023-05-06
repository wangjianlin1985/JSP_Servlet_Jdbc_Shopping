package me.ilt.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.ilt.Bean.userBean;
import me.ilt.Util.Conn;
import me.ilt.Util.jsonUtil;
import net.sf.json.JSONArray;



public class usersDao {
	public static String adminLogin(String userName){
		String sql = "select * from t_user where userName=? and userType=0";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		String password = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
			password = rs.getString("password");
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
		return password;
	}
	public static String login(String userName){
		String sql = "select * from t_user where userName=?";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		String password = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
			password = rs.getString("password");
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
		return password;
	}
	public static int nameIsId(String userName){
		String sql = "select id from t_user where userName=?";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int id = 0;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
			id = rs.getInt("id");
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
	 * ��ѯȫ��
	 * @param p
	 * @param pageSize
	 * @return
	 */
	public static JSONArray selAll(int p,int pageSize){
		String sql = "select top "+pageSize+" * from t_user where id not in(select top "+(p-1)*pageSize+" id from t_user)";
		return sel(sql);
	}
	/**
	 * �����ǳƲ�ѯ
	 * @param p
	 * @param pageSize
	 * @param username
	 * @return
	 */
	public static JSONArray nameSel(int p,int pageSize,String username){
		String sql = "select top "+pageSize+" * from t_user where id not in(select top "+(p-1)*pageSize+" id from t_user where userName like '%"+username+"%') and userName like '%"+username+"%'";
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
	 * ����û�
	 * @param u
	 * @return
	 */
	public static int add(userBean u){
		String sql = "insert into t_user values(?,?,?,?,?,?,?,?,?,?)";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getTrueName());
			ps.setString(3, u.getSex());
			ps.setString(4, u.getBirthday());
			ps.setString(5, u.getStatusID());
			ps.setString(6, u.getPhone());
			ps.setString(7, "");
			ps.setString(8, u.getEmail());
			ps.setInt(9, Integer.parseInt(u.getUserType()));
			ps.setString(10, u.getPassword());
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
	 * �������û�
	 * @param u
	 * @return
	 */
	public static int adduser(userBean u){
		String sql = "insert into t_user (userName,password)values(?,?)";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getPassword());
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
	 * ����ID��ѯ
	 * @param id
	 * @return
	 */
	public static userBean idSel(int id){
		String sql = "select * from t_user where id=?";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		userBean u = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				u = new userBean(rs.getString("userName"), rs.getString("trueName"),
						rs.getString("sex"), rs.getString("birthday"), rs.getString("statusID"), 
						rs.getString("phone"), null, rs.getString("email"), null, null);
				u.setId(rs.getInt("id"));
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
		return u;
	}
	/**
	 * ����IDɾ��
	 * @param id
	 * @return
	 */
	public static int del(int id){
		System.out.println("���ܵ�Ҫɾ����ID��"+id);
		String sql = "delete t_user where id=?";
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
		String sql = "delete from t_user where id in("+ids+")";
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
	 * ��̨�޸�����
	 * @param u
	 * @return
	 */
	public static int update(userBean u){
		String sql = "update t_user "
			+"set userName=?,trueName=?,sex=?,birthday=?,statusID=?,phone=?,address=?,email=?,password=? "
			+"where id=? ";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getTrueName());
			ps.setString(3, u.getSex());
			ps.setString(4, u.getBirthday());
			ps.setString(5, u.getStatusID());
			ps.setString(6, u.getPhone());
			ps.setString(7, u.getAddress());
			ps.setString(8, u.getEmail());
			ps.setString(9, u.getPassword());
			ps.setInt(10, u.getId());
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
	 * �û��޸�����
	 * 
	 */
	public static int changePassword(int userId,String Oldpassword,String password ){
		String sql = "update t_user "
			+"set password=? "
			+"where id=? and password=? ";
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setInt(2, userId);
			ps.setString(3, Oldpassword);
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
	 * �û��޸�����
	 * @param u
	 * @return
	 */
	public static int userUpdate(userBean u){
		String sql = "update t_user "
			+"set trueName=?,sex=?,birthday=?,statusID=?,phone=?,email=? "
			+"where id=? ";
		System.out.println("Dao�д�ӡ��"+u);
		Connection con = Conn.getCon();
		PreparedStatement ps = null;
		int i = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getTrueName());
			ps.setString(2, u.getSex());
			ps.setString(3, u.getBirthday());
			ps.setString(4, u.getStatusID());
			ps.setString(5, u.getPhone());
			ps.setString(6, u.getEmail());
			ps.setInt(7, u.getId());
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
	public static void main(String[] args) {
		//System.out.println(login("admin"));
		//selAll(1, 10);
		//System.out.println(nameIsId("admin"));
		//idSel(10006);
		//System.out.println(changePassword(10027, "123456", "654321"));
	}
}
