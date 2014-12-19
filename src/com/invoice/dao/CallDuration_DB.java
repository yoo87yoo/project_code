package com.invoice.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.invoice.vo.CallDurationVo;

public class CallDuration_DB {
	Connection con = null;
	Statement stat = null;
	String sql = null;
	
	//通話情報データをデータベースにセーブ
	public void callDuration_db(ArrayList<CallDurationVo> call_duration) {	
	
		try {
			//DBConnに連結
			con = DBConn.getConnection();
			stat = con.createStatement();
			
			//CallDurationVoのArrayListの中である内容をデータベースにinsert
			for (int i = 0; i < call_duration.size(); i++) {
				String call_details = call_duration.get(i).getTelephone();
				int call_count = call_duration.get(i).getCall_count();
				int call_price = call_duration.get(i).getPrice();
				String call_date = call_duration.get(i).getCall_date();
				//insertクエリー
				sql = "insert mydb.call_duration values ('" + call_details + "','" + call_count + "','" + call_price + "','" + call_date + "')";
				stat.executeUpdate(sql);
			}	
			stat.close();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

}
