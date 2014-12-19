package com.invoice.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class fileOut {
	JFileChooser fileChooser;
	public void fileOpen(String filename){

		if(filename.equals("sbcyberpass")){
			fileChooser = new JFileChooser("C:/Invoice Project/InvoiceSave/sb");// 経路設定
		}
		else if(filename.equals("softbay")){
			fileChooser = new JFileChooser("C:/Invoice Project/InvoiceSave/sob");// 経路設定
		}
		else if(filename.equals("freefile")){
			fileChooser = new JFileChooser("C:/Invoice Project/freeData");// 経路設定
		}
		else if(filename.equals("callduration")){
			fileChooser = new JFileChooser("C:/Invoice Project/CallDuration");// 経路設定
		}


		fileChooser.setMultiSelectionEnabled(true);
		int status = fileChooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			File selectedFiles[] = fileChooser.getSelectedFiles();
			for (int i = 0, n = selectedFiles.length; i < n; i++) {
				String file_url;
				Desktop dt = Desktop.getDesktop();	

				// file urlを盛り込む変数
				file_url = selectedFiles[i].getParent() + "/" + selectedFiles[i].getName();

				// [\]を正しい経路の[/]で変換
				file_url = file_url.replaceAll("\\\\", "/");

				try {
					dt.open(new File(file_url));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} 
	}		
}			

