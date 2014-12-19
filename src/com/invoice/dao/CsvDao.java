package com.invoice.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class CsvDao {

	Connection con = null;
	Statement stat = null;
	String sql = null;

	//csvファイルをデータベースにセーブ
	public void insertCsv(ArrayList<String> csvFile_List) {

		try {
			//DBConnに連結
			con = DBConn.getConnection();
			stat = con.createStatement();
			//前にcsvファイルの資料を上書きするクエリー
			sql = "truncate mydb.invoice_db";
			stat.executeUpdate(sql);
			
			//csvファイルの個数分をdatabaseにセーブするfor文			
			for (int i = 0; i < csvFile_List.size(); i++) {
				String csvlist = csvFile_List.get(i);
				//csvファイルをdatabaseにセーブするクエリー	
				sql = "LOAD DATA INFILE '" + csvlist
						+ "' INTO TABLE mydb.invoice_db FIELDS TERMINATED BY ','IGNORE 1 LINES";

				stat.executeQuery(sql);

			}
			stat.close();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

}
