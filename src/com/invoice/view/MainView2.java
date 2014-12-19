package com.invoice.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.invoice.dao.CsvDao;
import com.invoice.dao.PriceDao;
import com.invoice.dao.RateDao;
import com.invoice.poi.CallData;
import com.invoice.poi.FreeData;
import com.invoice.poi.Invoice_Poi;
import com.invoice.vo.BasicsVo;
import com.invoice.vo.CallDurationVo;
import com.invoice.vo.FreeTelephone;
import com.invoice.vo.Telephone;
import java.awt.SystemColor;

public class MainView2 extends JFrame {
	ArrayList<String> csvFile_List;
	ArrayList<String> rate_List = new ArrayList<String>();
	ArrayList<Telephone> invoice_Sum;
	ArrayList<Telephone> international_call;
	ArrayList<FreeTelephone> free_call;
	ArrayList<CallDurationVo> call_duration;
	ArrayList<BasicsVo> basics_list;
	ArrayList<BasicsVo> sob_basics_list;
	String csvfile_url;
	String csv_view = "";
	String sbfile_url;
	String sobfile_url;
	String freefile_url;
	String callDuration_url;
	JTextArea txtrcsvFile = new JTextArea();
	fileOut open = new fileOut();
	RateDao rDAo = new RateDao();
	String csv_folder_date;

	public static void main(String[] args) {
		MainView2 m = new MainView2();
		m.setSize(550, 700);
		m.setVisible(true);

	}

	public MainView2() {
		setTitle("INVOICE PROGRAM");
		setResizable(false);
		rate_List = rDAo.rateList_Dao();
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(70, 312, 4, 22);
		getContentPane().add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(70, 312, 4, 22);
		getContentPane().add(textArea_1);
		txtrcsvFile.setRows(20);

		txtrcsvFile.setLineWrap(true);
		txtrcsvFile.setBackground(Color.WHITE);
		txtrcsvFile.setEditable(false);
		txtrcsvFile.setFont(new Font("Monospaced", Font.BOLD, 18));
		txtrcsvFile.setBounds(0, 58, 572, 499);
		getContentPane().add(txtrcsvFile);

		// start button action
		JButton btnNewButton_3 = new JButton("START");
		btnNewButton_3.setForeground(SystemColor.textHighlightText);
		btnNewButton_3.setBackground(SystemColor.infoText);
		btnNewButton_3.setFont(new Font("Segoe Print", Font.BOLD, 20));
		// btnNewButton_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
		// null, null, null, null));
		btnNewButton_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					// 1. csv file 保存
					CsvDao csvdao = new CsvDao();
					csvdao.insertCsv(csvFile_List);

					// 2. db data 出力
					PriceDao pricedao = new PriceDao();
					invoice_Sum = pricedao.invoice_Sum(); // 通話料請求書データ
					international_call = pricedao.international_call();// 　国際通話料請求書データ
					basics_list = rDAo.basics();// cyberpass invoice basics
					sob_basics_list = rDAo.sob_basics();// softbay
					// invoice　basics

					// 3. 請求書出力
					rate_List = rDAo.rateList_Dao();
					Invoice_Poi poi = new Invoice_Poi();
					poi.invoice_poi(invoice_Sum, international_call, basics_list, rate_List, sob_basics_list,
							csv_folder_date);

					// 4. 無料情報出力
					free_call = pricedao.free_call();
					FreeData fDao = new FreeData();
					// System.out.println(free_call);
					fDao.freedata(free_call);

					// 5. 通話情報出力
					call_duration = pricedao.call_duration();
					CallData cDao = new CallData();
					cDao.calldata(call_duration);

					JOptionPane.showMessageDialog(null, "完了しました。");

				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "CSV FILEを検索してください。");
				}
			}
		});// start button action

		btnNewButton_3.setBounds(357, 578, 127, 57);
		getContentPane().add(btnNewButton_3);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(SystemColor.desktop);
		menuBar.setBounds(0, 0, 572, 57);
		getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("OPEN");
		mnNewMenu.setFont(new Font("メイリオ", Font.BOLD, 18));
		mnNewMenu.setForeground(SystemColor.textHighlightText);
		mnNewMenu.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnNewMenu);

		// csv file open
		JMenuItem mntmNewMenuItem = new JMenuItem("CSV FILE OPEN");
		mntmNewMenuItem.setBackground(SystemColor.control);
		mntmNewMenuItem.setFont(new Font("メイリオ", Font.BOLD, 15));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				csv_view = "";
				csvFile_List = new ArrayList<String>();
				JFileChooser fileChooser = new JFileChooser("C:/Invoice Project/csvFile");// 経路設定
				fileChooser.setMultiSelectionEnabled(true);
				int status = fileChooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {

					File selectedFiles[] = fileChooser.getSelectedFiles();
					for (int i = 0, n = selectedFiles.length; i < n; i++) {

						// file urlを盛り込む変数
						csvfile_url = selectedFiles[i].getParent() + "/" + selectedFiles[i].getName();

						// [\]を正しい経路の[/]で変換
						csvfile_url = csvfile_url.replaceAll("\\\\", "/");

						// csv File Listの情報を盛り込む
						csvFile_List.add(csvfile_url);

						csv_view += selectedFiles[i].getName() + "\n";
						if (i == selectedFiles.length - 1) {
							csv_view += "\n" + "csv fileは[" + csvFile_List.size() + "]fileがあります。";
						}

					}

					// 画面に持ってきたｃｓｖ情報を出力
					txtrcsvFile.setText(csv_view);
				}

			}
		});// csv file open

		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("OPTION");
		mnNewMenu_1.setFont(new Font("メイリオ", Font.BOLD, 18));
		mnNewMenu_1.setForeground(SystemColor.textHighlightText);
		menuBar.add(mnNewMenu_1);

		// rate option
		JMenuItem mntmGcvFileOpen = new JMenuItem("RATE OPTION");
		mntmGcvFileOpen.setFont(new Font("メイリオ", Font.BOLD, 15));
		mntmGcvFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rate_List = rDAo.rateList_Dao();

				RateView r = new RateView();

				r.rate_list(rate_List);
				r.setSize(453, 303);
				r.setVisible(true);

			}
		});// rate option

		mnNewMenu_1.add(mntmGcvFileOpen);

		// invoice option
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("INVOICE OPTION");
		mntmNewMenuItem_1.setFont(new Font("メイリオ", Font.BOLD, 15));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basics_list = rDAo.basics();
				sob_basics_list = rDAo.sob_basics();
				BasicsView bView = new BasicsView();

				bView.basics_list(basics_list, sob_basics_list);

				bView.setSize(790, 350);
				bView.setVisible(true);
			}
		});// invoice option

		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_2 = new JMenu("INVOICE");
		mnNewMenu_2.setFont(new Font("メイリオ", Font.BOLD, 18));
		mnNewMenu_2.setForeground(SystemColor.textHighlightText);
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("SBCYBERPASS INVOICE");
		mntmNewMenuItem_3.setFont(new Font("メイリオ", Font.BOLD, 15));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open.fileOpen("sbcyberpass");
			}
		});// sb file open
		mnNewMenu_2.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("SOFTBAY INVOICE");
		mntmNewMenuItem_5.setFont(new Font("メイリオ", Font.BOLD, 15));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open.fileOpen("softbay");
			}
		});// sob file open
		mnNewMenu_2.add(mntmNewMenuItem_5);

		JMenu mnNewMenu_4 = new JMenu("CHECK");
		mnNewMenu_4.setFont(new Font("メイリオ", Font.BOLD, 18));
		mnNewMenu_4.setForeground(SystemColor.textHighlightText);
		menuBar.add(mnNewMenu_4);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("FREE DATA");
		mntmNewMenuItem_4.setFont(new Font("メイリオ", Font.BOLD, 15));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open.fileOpen("freefile");
			}
		});// free file open
		mnNewMenu_4.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("CALL DURATION");
		mntmNewMenuItem_2.setFont(new Font("メイリオ", Font.BOLD, 15));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open.fileOpen("callduration");
			}
		});// call duration file open
		mnNewMenu_4.add(mntmNewMenuItem_2);

		JMenu mnNewMenu_3 = new JMenu("EXIT");
		mnNewMenu_3.setFont(new Font("メイリオ", Font.BOLD, 18));
		mnNewMenu_3.setForeground(SystemColor.textHighlightText);
		menuBar.add(mnNewMenu_3);

		// exit
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("EXIT");
		mntmNewMenuItem_6.setFont(new Font("メイリオ", Font.BOLD, 15));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});// exit
		mnNewMenu_3.add(mntmNewMenuItem_6);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
