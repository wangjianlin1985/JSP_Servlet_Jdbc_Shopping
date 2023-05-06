package me.ilt.Util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class jsonUtil {

	/**
	 * 把ResultSet集合转换成JsonArray数组
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
		ResultSetMetaData md=rs.getMetaData();
		int num=md.getColumnCount();
		JSONArray array=new JSONArray();
		while(rs.next()){
			JSONObject mapOfColValues=new JSONObject();
			for(int i=1;i<=num;i++){
				if(md.getColumnName(i).equals("birthday")||md.getColumnName(i).equals("time")){
					mapOfColValues.put(md.getColumnName(i), rs.getString(i)); //获取列名和值放进JSON传输对象中
				}else{
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i)); //获取列名和值放进JSON传输对象中
				}
			}
			array.add(mapOfColValues);//放到JSON集合中
		}
		return array;
	}
}
