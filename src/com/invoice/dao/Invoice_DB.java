package com.invoice.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.invoice.vo.InternationalVo;

public class Invoice_DB {
	Connection con = null;
	Statement stat = null;
	String sql = null;

	//国際請求書データをデータベースにセーブ	
	public void invoice_db(ArrayList<InternationalVo> international_list) {

		try {
			con = DBConn.getConnection();
			stat = con.createStatement();

			//InternationalVoのArrayListの中である内容をデータベースにinsert
			for (int i = 0; i < international_list.size(); i++) {
				String international_details = international_list.get(i).getInternational_details();
				String international_price = international_list.get(i).getInternational_price();
				String international_date = international_list.get(i).getInternational_date();
				//insertクエリー
				sql = "insert mydb.international_save values ('" + international_details + "','" + international_price
						+ "','" + international_date + "')";
				stat.executeUpdate(sql);
			}

			stat.close();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

}
