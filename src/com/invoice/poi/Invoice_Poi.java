package com.invoice.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.invoice.dao.Invoice_DB;
import com.invoice.vo.BasicsVo;
import com.invoice.vo.InternationalVo;
import com.invoice.vo.Telephone;

public class Invoice_Poi {
	HSSFRow row_setting_1;
	HSSFRow row_setting_2;
	HSSFCell cell_details;
	HSSFCell cell_details_2;
	HSSFCell cell_tax;
	HSSFCell cell_remarks;
	HSSFCell cell_remarks_2;
	HSSFCell cell_amount;
	HSSFCell cell_amount_2;
	HSSFCell cell_unit;
	HSSFCell cell_rate;
	String details;
	String details2;
	String remarks;
	String remarks_2;

	ArrayList<InternationalVo> international_list = new ArrayList<InternationalVo>();

	public void invoice_poi(ArrayList<Telephone> invoice_Sum, ArrayList<Telephone> international_call,
			ArrayList<BasicsVo> basics_list, ArrayList<String> rate_List, ArrayList<BasicsVo> sob_basics_list,
			String csv_folder_date) {

		// 請求書を作る日付データ
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.JAPAN);
		Date currentTime = new Date();
		String mTime = mSimpleDateFormat.format(currentTime);

		// 1回目のcycle : sbcyberpass請求書
		// 2回目のcycle : softbay請求書
		for (int cycle = 0; cycle < 2; cycle++) {

			int row_start = 19; // 始まるの列設定。
			int cell_details_data = 1; // detailsの行設定。
			int cell_unit_data = 8; // unitの行設定。
			int cell_rate_data = 10; // rateの行設定。
			int cell_amount_data = 12; // amountの行設定。
			int cell_cell_remarks = 16; // remarksの行設定。
			int cell_tax_cell = 13; // taxの行設定。
			int next_row = 2; // 下がる列設定。
			int price2 = 0; // 国際通話料がある場合の変数。
			double rate = 0; // rate_listのデータを入れる変数。

			FileInputStream inputStream = null;

			try {

				// sbcyberpass invoiceの請求書様式を持ち込む。
				if (cycle == 0) {
					inputStream = new FileInputStream("C:/Invoice Project/Invoice/sb.xls");
				}

				// softbay invoiceの請求書様式を持ち込む。
				if (cycle == 1) {
					inputStream = new FileInputStream("C:/Invoice Project/Invoice/sob.xls");
				}

				POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
				HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
				HSSFSheet sheet = workbook.getSheetAt(0);
				workbook.setForceFormulaRecalculation(true);

				// sbcyberpass　rate。
				if (cycle == 0) {
					rate = Double.parseDouble(rate_List.get(0));
				}
				// softbay　rate。
				if (cycle == 1) {
					rate = Double.parseDouble(rate_List.get(1));
				}

				// invoice_Sum : 請求書に出力するリスト。
				for (int r = 0; r < invoice_Sum.size(); r++) {
					row_start += next_row; // 始めるの行からnext_rowほどプラス。

					row_setting_1 = sheet.getRow(row_start); // 通話料列設定。
					row_setting_2 = sheet.getRow(row_start + 1); // 国際通話料列設定。（通話料の下で設定）

					// 列と行設定。
					cell_details = row_setting_1.getCell(cell_details_data);
					cell_amount = row_setting_1.getCell(cell_amount_data);
					cell_details_2 = row_setting_2.getCell(cell_details_data);
					cell_amount_2 = row_setting_2.getCell(cell_amount_data);
					// 列と行設定。

					// 通話料detailsデータ請求書に出力。
					details = "光D 通話料　（" + invoice_Sum.get(r).getTelephone() + ")";
					cell_details.setCellValue(details);

					// 通話料amountデータ請求書に出力。
					double p = (double) (invoice_Sum.get(r).getPrice());
					int price = (int) Math.floor(p * rate);
					cell_amount.setCellValue(price);

					// 国際通話料detailsデータ請求書に出力。
					details2 = "光D 国際通話料 (" + invoice_Sum.get(r).getTelephone() + ")";
					cell_details_2.setCellValue(details2);

					// 国際通話料amountデータ請求書に出力。
					if (international_call.size() == 0) {
						cell_amount_2.setCellValue(0);
					} else {
						cell_amount_2.setCellValue(international_call.get(r).getPrice());
						price2 = international_call.get(r).getPrice();
					}

					// 国際通話料データをデータベースに入れるための機能。
					// 国際通話料はrateの計算が必要ないので、1回だけ実行。
					if (cycle == 0) {
						String international_details = details2;
						String international_price;
						if (international_call.size() == 0) {
							international_price = "0";
						} else {
							international_price = String.valueOf(price2);
						}
						String international_date = mTime;
						InternationalVo iVo = new InternationalVo(international_details, international_price,
								international_date);
						international_list.add(iVo);
					}

				}// invoice_Sum for end

				// invoice_Sumデータの下にbasicsデータ出力。
				row_start += 1; // 列設定。

				// sbcyberpass basics出力。
				if (cycle == 0) {
					for (int i = 0; i < basics_list.size(); i++) {
						row_start += 1;
						// 列と行設定。
						row_setting_1 = sheet.getRow(row_start);
						cell_details = row_setting_1.getCell(cell_details_data);
						cell_unit = row_setting_1.getCell(cell_unit_data);
						cell_rate = row_setting_1.getCell(cell_rate_data);
						cell_remarks = row_setting_1.getCell(cell_cell_remarks);
						cell_tax = row_setting_1.getCell(cell_tax_cell);
						// 列と行設定。

						// basicsデータを請求書に出力。
						cell_details.setCellValue(basics_list.get(i).getDetails());
						cell_unit.setCellValue(basics_list.get(i).getUnit());
						cell_rate.setCellValue(basics_list.get(i).getRate());
						cell_tax.setCellValue("個別");
						if (basics_list.get(i).getRemarks().equals("null")) {

							cell_remarks.setCellValue("");
						} else {
							cell_remarks.setCellValue(basics_list.get(i).getRemarks());
						}

					}// basics_list for end
				}// sbcyberpass basics if end

				// softbay basics出力。
				if (cycle == 1) {
					for (int i = 0; i < sob_basics_list.size(); i++) {
						row_start += 1;
						// 列と行設定。
						row_setting_1 = sheet.getRow(row_start);
						cell_details = row_setting_1.getCell(cell_details_data);
						cell_unit = row_setting_1.getCell(cell_unit_data);
						cell_rate = row_setting_1.getCell(cell_rate_data);
						cell_remarks_2 = row_setting_1.getCell(cell_cell_remarks);
						cell_tax = row_setting_1.getCell(cell_tax_cell);
						// 列と行設定。

						// basicsデータを請求書に出力。
						cell_details.setCellValue(sob_basics_list.get(i).getDetails());
						cell_unit.setCellValue(sob_basics_list.get(i).getUnit());
						cell_rate.setCellValue(sob_basics_list.get(i).getRate());
						cell_tax.setCellValue("合算");

						if (sob_basics_list.get(i).getRemarks().equals("null")) {

							cell_remarks_2.setCellValue("");
						} else {
							cell_remarks_2.setCellValue(sob_basics_list.get(i).getRemarks());
						}
					}// basics_list for end
				}// softbay basics if end

				// sbcyberpass 請求書出力。
				if (cycle == 0) {
					FileOutputStream fileOut = new FileOutputStream("C:\\Invoice Project\\InvoiceSave\\sb\\sb_" + mTime
							+ ".xls");
					workbook.write(fileOut);
					fileOut.close();

				}

				// softbay 請求書出力。
				if (cycle == 1) {
					FileOutputStream fileOut = new FileOutputStream("C:\\Invoice Project\\InvoiceSave\\sob\\sob_"
							+ mTime + ".xls");
					workbook.write(fileOut);
					fileOut.close();

				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}// cycle for end

		// 国際通話料データをデータベースに入れる機能。
		Invoice_DB iDB = new Invoice_DB();
		iDB.invoice_db(international_list);

	}
}
