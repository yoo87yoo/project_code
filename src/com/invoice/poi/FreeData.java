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

import com.invoice.vo.FreeTelephone;

public class FreeData {
	HSSFRow row;
	HSSFCell cell_1;
	HSSFCell cell_2;
	HSSFCell cell_3;
	HSSFCell cell_4;

	int row_start = 0;
	int cell_c_1 = 0;
	int cell_c_2 = 1;
	int cell_c_3 = 2;
	int cell_c_4 = 3;

	// 無料請求書データをexcelで出力する機能
	public void freedata(ArrayList<FreeTelephone> free_call) {
		System.out.println(free_call);
		
		//Dateを持ってくる
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.JAPAN);
		Date currentTime = new Date();
		String mTime = mSimpleDateFormat.format(currentTime);

		try {
			//無料請求書データを出力するためにpoiを利用する基本エクセルのファイル
			FileInputStream inputStream = new FileInputStream("C:/Invoice Project/freeData/freeData.xls");
			POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
			HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
			HSSFSheet sheet = workbook.getSheetAt(0);

			//free_callにあるデータをエクセルのセルにセーブ
			for (int i = 0; i < free_call.size(); i++) {
				row_start += 1;
				row = sheet.getRow(row_start);
				cell_1 = row.getCell(cell_c_1);
				cell_2 = row.getCell(cell_c_2);
				cell_3 = row.getCell(cell_c_3);
				cell_4 = row.getCell(cell_c_4);

				cell_1.setCellValue(free_call.get(i).getTelephone());
				cell_2.setCellValue(free_call.get(i).getCall_kind());
				cell_3.setCellValue(free_call.get(i).getCall_count());
				cell_4.setCellValue(free_call.get(i).getPrice());
			}
			//エクセルで出力
			FileOutputStream fileOut = new FileOutputStream("C:\\Invoice Project\\freeData\\free_" + mTime + ".xls");
			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
