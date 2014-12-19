package com.invoice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.invoice.vo.BasicsVo;

public class RateDao {
	Connection con = null;
	Statement stat = null;
	String sql = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	//rateの情報を持ち込む。
	public ArrayList<String> rateList_Dao() {
		ArrayList<String> rate_List = new ArrayList<String>(); // rateリスト変数。

		try {
			con = DBConn.getConnection();
			sql = "select * from mydb.rate_list";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String sb_rate = String.valueOf(rs.getDouble("sb_rate"));
				String sob_rate = String.valueOf(rs.getDouble("sob_rate"));
				String phone_rate = String.valueOf(rs.getDouble("phone_rate"));

				rate_List.add(sb_rate);
				rate_List.add(sob_rate);
				rate_List.add(phone_rate);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return rate_List;
	}

	// rateを修正。
	public void rate_update(ArrayList<String> rateList) {

		try {
			double sb_rate = Double.parseDouble(rateList.get(0));
			double sob_rate = Double.parseDouble(rateList.get(1));
			double phone_rate = Double.parseDouble(rateList.get(2));

			con = DBConn.getConnection();
			stat = con.createStatement();
			sql = "update mydb.rate_list set sb_rate='" + sb_rate + "',sob_rate ='" + sob_rate + "',phone_rate='"
					+ phone_rate + "'";
			stat.executeUpdate(sql);

			stat.close();
			JOptionPane.showMessageDialog(null, "rateの情報が修正完了しました。");
	
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "正しく入力してください。");
		}
	}

	// cyberpass invoice basicsデータを持ち込む
	public ArrayList<BasicsVo> basics() {
		ArrayList<BasicsVo> basics_list = new ArrayList<BasicsVo>();
		try {
			con = DBConn.getConnection();
			sql = "select * from mydb.invoice_basics where kind = 0";
			// System.out.println(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String details = rs.getString("details");
				int unit = rs.getInt("unit");
				int rate = rs.getInt("rate");
				int kind = rs.getInt("kind");
				String remarks = rs.getString("remarks");
				BasicsVo bVo = new BasicsVo(details, unit, rate, kind, remarks);

				basics_list.add(bVo);

			}
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return basics_list;
	}

	// softbay invoice　basicsデータを持ち込む
	public ArrayList<BasicsVo> sob_basics() {
		ArrayList<BasicsVo> sob_basics_list = new ArrayList<BasicsVo>();
		try {
			con = DBConn.getConnection();
			sql = "select * from mydb.invoice_basics where kind = 1";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				String details = rs.getString("details");
				int unit = rs.getInt("unit");
				int rate = rs.getInt("rate");
				int kind = rs.getInt("kind");
				String remarks = rs.getString("remarks");

				BasicsVo sobVo = new BasicsVo(details, unit, rate, kind, remarks);

				sob_basics_list.add(sobVo);

			}
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return sob_basics_list;
	}

	// basicsデータをデータベースに入れる機能
	public void basics_insert(ArrayList<BasicsVo> basic_list) {
		try {

			con = DBConn.getConnection();
			stat = con.createStatement();
			
			//１．該当kind numberを持っている（0:sbcyberpass, 1:softbay）データを削除。
			sql = "delete from mydb.invoice_basics where kind = " + basic_list.get(0).getKind() + "";
			stat.executeUpdate(sql);

			//2.持ってきたbasic_listを入れる。
			for (int i = 0; i < basic_list.size(); i++) {
				sql = "insert mydb.invoice_basics (details,unit,rate,kind,remarks) " + "values('"
						+ basic_list.get(i).getDetails() + "'," + basic_list.get(i).getUnit() + ","
						+ basic_list.get(i).getRate() + "," + basic_list.get(i).getKind() + ",'"
						+ basic_list.get(i).getRemarks() + "')";
				stat.executeUpdate(sql);
			}

			stat.close();
			JOptionPane.showMessageDialog(null, "修正完了しました。");
			// con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
