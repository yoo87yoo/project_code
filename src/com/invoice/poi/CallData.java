package com.invoice.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.invoice.dao.CallDuration_DB;
import com.invoice.vo.CallDurationVo;



public class CallData {
	HSSFRow row;
	HSSFCell cell_1;
	HSSFCell cell_2;
	HSSFCell cell_3;

	int row_start = 0;
	int cell_c_1 = 0;
	int cell_c_2 = 1;
	int cell_c_3 = 2;

	// 通話情報データをexcelで出力する機能
	public void calldata(ArrayList<CallDurationVo> call_duration) {
		
		//Dateを持ってくる
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.JAPAN);
		Date currentTime = new Date();
		String mTime = mSimpleDateFormat.format(currentTime);

		try {
			//通話情報データを出力するためにpoiを利用する基本エクセルのファイル
			FileInputStream inputStream = new FileInputStream("C:/Invoice Project/CallDuration/callDuration.xls");
			POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
			HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			//call_durationにあるデータをエクセルのセルにセーブ
			for (int i = 0; i < call_duration.size(); i++) {
				row_start += 1;
				row = sheet.getRow(row_start);
				cell_1 = row.getCell(cell_c_1);
				cell_2 = row.getCell(cell_c_2);
				cell_3 = row.getCell(cell_c_3);

				cell_1.setCellValue(call_duration.get(i).getTelephone());
				cell_2.setCellValue(call_duration.get(i).getCall_count());
				cell_3.setCellValue(call_duration.get(i).getPrice());
			}
			//エクセルで出力
			FileOutputStream fileOut = new FileOutputStream("C:\\Invoice Project\\CallDuration\\call_" + mTime + ".xls");
			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		//通話情報データをデータベースにセーブするメソッドを実行
		CallDuration_DB cDB = new CallDuration_DB();
		cDB.callDuration_db(call_duration);

	}

}
