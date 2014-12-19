package com.invoice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.invoice.vo.CallDurationVo;
import com.invoice.vo.FreeTelephone;
import com.invoice.vo.Telephone;

public class PriceDao {

	Connection con;
	String sql;
	PreparedStatement ps;
	ResultSet rs;

	// 請求書の値で料率適用前の値
	public ArrayList<Telephone> invoice_Sum() {
		//ArrayList生成
		ArrayList<Telephone> invoice_Sum;
		invoice_Sum = new ArrayList<Telephone>();
		try {
			//DBConnに連結
			con = DBConn.getConnection();
			//料率適用前の値を計算するクエリー
			sql = "select call_number as telephone, ceil(sum(f.a)/60) as price from (select call_number,case  when substring(call_time,-1,1) = '0'  then time_to_sec(call_time)  else time_to_sec(call_time)+1 end a , call_kind from mydb.invoice_db) f where call_kind='ＩＰ間呼' or call_kind='国内呼' group by f.call_number";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			//クエリーで計算されたtelephone、priceの値を縛ってリストに追加
			while (rs.next()) {
				String telephone = rs.getString("telephone");
				int price = (rs.getInt("price"));
				Telephone tel = new Telephone(telephone, price);
				invoice_Sum.add(tel);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return invoice_Sum;

	}

	// 国際通話料の値
	public ArrayList<Telephone> international_call() {
		//ArrayList生成		
		ArrayList<Telephone> international_call;
		international_call = new ArrayList<Telephone>();

		try {
			//DBConnに連結
			con = DBConn.getConnection();
			//国際通話料の値を計算するクエリー
			sql = "select call_number as telephone ,floor(sum(call_account)) as price " + "from mydb.invoice_db "
					+ "where call_kind='国際呼' " + "group by call_number";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			//クエリーで計算されたtelephone、priceの値を縛ってリストに追加
			while (rs.next()) {
				String telephone = rs.getString("telephone");
				int price = rs.getInt("price");
				Telephone tel = new Telephone(telephone, price);
				international_call.add(tel);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("fail");
		}

		return international_call;
	}

	// 無料通話料の値
	public ArrayList<FreeTelephone> free_call() {
		//ArrayList生成	
		ArrayList<FreeTelephone> free_call;
		free_call = new ArrayList<FreeTelephone>();

		try {
			//DBConnに連結			
			con = DBConn.getConnection();
			//無料通話料の値を計算するクエリー
			sql = "select call_number as telephone ,call_kind,count(*) as call_count, sum(f.a) as price " + "from ("
					+ "select call_number, case " + "when substring(call_time,-1,1) = '0' "
					+ "then time_to_sec(call_time) " + "else time_to_sec(call_time)+1 " + "end a "
					+ ", call_kind,call_account from mydb.invoice_db) f "
					+ "group by f.call_number,call_kind,f.call_account " + "having (f.call_account=0)";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			//queryで計算されたtelephone、call_kind、call_count、priceの値を縛ってリストに追加
			while (rs.next()) {
				String telephone = rs.getString("telephone");
				String call_kind = rs.getString("call_kind");
				int call_count = rs.getInt("call_count");
				int price = rs.getInt("price");
				FreeTelephone tel = new FreeTelephone(telephone, call_kind, call_count, price);
				free_call.add(tel);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		return free_call;
	}

	//通話情報データをデータベースにセーブするために作るメソッド
	public ArrayList<CallDurationVo> call_duration() {
		//ArrayList生成	
		ArrayList<CallDurationVo> call_duration;
		call_duration = new ArrayList<CallDurationVo>();

		try {
			//DBConnに連結			
			con = DBConn.getConnection();
			//通話情報データをデータベースにセーブするために作るクエリー
			sql = "select call_number as telephone ,count(*) as call_count, sum(f.a) as price " + "from ("
					+ "select call_number, case " + "when substring(call_time,-1,1) = '0' "
					+ "then time_to_sec(call_time) " + "else time_to_sec(call_time)+1 " + "end a "
					+ ", call_kind,call_account from mydb.invoice_db) f "
					+ "group by f.call_number";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			//queryで計算されたtelephone、call_kind、call_count、priceの値を縛ってリストに追加
			while (rs.next()) {
				String telephone = rs.getString("telephone");
				int call_count = rs.getInt("call_count");
				int price = rs.getInt("price");
				SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd");
				String call_date =sd.format(new Date());
				CallDurationVo call = new CallDurationVo(telephone, call_count, price,call_date);
				call_duration.add(call);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		return call_duration;
	}	
}
