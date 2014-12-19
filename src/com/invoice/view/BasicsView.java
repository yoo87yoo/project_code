package com.invoice.view;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.invoice.dao.RateDao;
import com.invoice.vo.BasicsVo;

import javax.swing.UIManager;

import org.apache.poi.hssf.util.HSSFColor.BLACK;

public class BasicsView extends JFrame {
	private static final Color WHITE = null;
	DefaultTableModel model;
	DefaultTableModel model_2;
	JTable table;
	JTable table_2;
	JScrollPane scroll;
	JScrollPane scroll_2;
	JTextArea textArea;
	JTextArea textArea_2;
	String st_kind;
	int kind;
	ArrayList<BasicsVo> sob_basics_list;
	ArrayList<BasicsVo> basic_list;
	RateDao rDao = new RateDao();
	BasicsVo bVo;
	JEditorPane editorPane_1 = new JEditorPane();

	public BasicsView() {
		setTitle("Invoice Option");
		setResizable(false);

		getContentPane().setFont(new Font("MS UI Gothic", Font.BOLD, 17));

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
	
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 785, 221);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setForeground(Color.BLACK);
	
		
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("CyberPass", null, panel, null);
		panel.setLayout(null);
		tabbedPane.addChangeListener(new ChangeListener() {

		// タブページ区分する機能。
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane aa = (JTabbedPane) e.getSource();
				int tab = aa.getSelectedIndex();
				editorPane_1.setText(String.valueOf(tab));
			}
		});// タブページ区分する機能。

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 780, 202);
		panel.add(panel_1);

		//sbcyberpass header
		String[] header = { "Details", "Unit", "Rate", "Amount", "Tax Ind.", "Sub Amount", "Remarks", "kind" };
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.setBackground(Color.WHITE);
		textArea = new JTextArea(5, 30);

		//softbay header
		String[] header_2 = { "Details", "Unit", "Rate", "Amount", "Tax Ind.", "Sub Amount", "Remarks", "kind" };
		model_2 = new DefaultTableModel(header_2, 0);
		table_2 = new JTable(model_2);
		table_2.setBackground(Color.WHITE);
		textArea_2 = new JTextArea(5, 30);

		table.setRowHeight(30);
		table_2.setRowHeight(30);

		table.getColumn("Details").setWidth(250);
		table.getColumn("Details").setMinWidth(250);
		table.getColumn("Details").setMaxWidth(250);
		table_2.getColumn("Details").setWidth(250);
		table_2.getColumn("Details").setMinWidth(250);
		table_2.getColumn("Details").setMaxWidth(250);

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("Unit").setWidth(50);
		table.getColumn("Unit").setMinWidth(50);
		table.getColumn("Unit").setMaxWidth(50);
		table.getColumn("Unit").setCellRenderer(celAlignCenter);
		table_2.getColumn("Unit").setWidth(50);
		table_2.getColumn("Unit").setMinWidth(50);
		table_2.getColumn("Unit").setMaxWidth(50);
		table_2.getColumn("Unit").setCellRenderer(celAlignCenter);

		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumn("Rate").setWidth(70);
		table.getColumn("Rate").setMinWidth(70);
		table.getColumn("Rate").setMaxWidth(70);
		table.getColumn("Rate").setCellRenderer(celAlignRight);
		table_2.getColumn("Rate").setWidth(70);
		table_2.getColumn("Rate").setMinWidth(70);
		table_2.getColumn("Rate").setMaxWidth(70);
		table_2.getColumn("Rate").setCellRenderer(celAlignRight);

		table.getColumn("Amount").setWidth(100);
		table.getColumn("Amount").setMinWidth(100);
		table.getColumn("Amount").setMaxWidth(100);
		table.getColumn("Amount").setCellRenderer(celAlignRight);
		table_2.getColumn("Amount").setWidth(100);
		table_2.getColumn("Amount").setMinWidth(100);
		table_2.getColumn("Amount").setMaxWidth(100);
		table_2.getColumn("Amount").setCellRenderer(celAlignRight);

		table.getColumn("Tax Ind.").setWidth(50);
		table.getColumn("Tax Ind.").setMinWidth(50);
		table.getColumn("Tax Ind.").setMaxWidth(50);
		table.getColumn("Tax Ind.").setCellRenderer(celAlignCenter);
		table_2.getColumn("Tax Ind.").setWidth(50);
		table_2.getColumn("Tax Ind.").setMinWidth(50);
		table_2.getColumn("Tax Ind.").setMaxWidth(50);
		table_2.getColumn("Tax Ind.").setCellRenderer(celAlignCenter);

		table.getColumn("Sub Amount").setWidth(100);
		table.getColumn("Sub Amount").setMinWidth(100);
		table.getColumn("Sub Amount").setMaxWidth(100);
		table.getColumn("Sub Amount").setCellRenderer(celAlignRight);
		table_2.getColumn("Sub Amount").setWidth(100);
		table_2.getColumn("Sub Amount").setMinWidth(100);
		table_2.getColumn("Sub Amount").setMaxWidth(100);
		table_2.getColumn("Sub Amount").setCellRenderer(celAlignRight);

		table.getColumn("kind").setWidth(0);
		table.getColumn("kind").setMinWidth(0);
		table.getColumn("kind").setMaxWidth(0);
		table_2.getColumn("kind").setWidth(0);
		table_2.getColumn("kind").setMinWidth(0);
		table_2.getColumn("kind").setMaxWidth(0);

		scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 780, 202);
		scroll.setViewportBorder(null);
	
		scroll.setBackground(Color.WHITE);

		scroll_2 = new JScrollPane(table_2);
		scroll_2.setBounds(0, 0, 780, 202);
		scroll_2.setViewportBorder(null);
		scroll_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.setLayout(null);

		panel_1.add(scroll);
		

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("SoftBay", null, panel_2, null);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 0, 780, 202);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		panel_3.add(scroll_2);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 221, 785, 100);
		getContentPane().add(panel_4);
		panel_4.setBackground(Color.WHITE);
		panel_4.setLayout(null);

		JButton btnNewButton_2 = new JButton("EXIT");
		btnNewButton_2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
			}
		});
		btnNewButton_2.setFont(new Font("Segoe Print", Font.BOLD, 20));
		btnNewButton_2.setBounds(458, 30, 99, 51);
		panel_4.add(btnNewButton_2);

		// 修正する機能
		JButton btnNewButton_3 = new JButton("SAVE");
		btnNewButton_3.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				st_kind = editorPane_1.getText();
				kind = Integer.parseInt(st_kind);
				basic_list = new ArrayList<BasicsVo>();
				try {
					if (kind == 0) {
						int row = model.getRowCount();
						for (int i = 0; i < row; i++) {
							//jTableのデータを一つずつString変数に入れる。
							String details = (String) table.getValueAt(i, 0);
							String srtUnit = (String) table.getValueAt(i, 1);
							String srtRate = (String) table.getValueAt(i, 2);
							int unit = Integer.parseInt(srtUnit);
							int rate = Integer.parseInt(srtRate.replaceAll(",", ""));// 「,」を消しる。
							String remarks = (String) table.getValueAt(i, 6);
							
							//basic_listに入れる。
							bVo = new BasicsVo(details, unit, rate, kind, remarks);
							bVo.setDetails(details);
							bVo.setUnit(unit);
							bVo.setRate(rate);
							bVo.setKind(kind);
							bVo.setRemarks(remarks);
							basic_list.add(bVo);
						}
						rDao.basics_insert(basic_list);
					} else {
						int row = model_2.getRowCount();
						for (int i = 0; i < row; i++) {
							//jTableのデータを一つずつString変数に入れる。	
							String details = (String) table_2.getValueAt(i, 0);
							String srtUnit = (String) table_2.getValueAt(i, 1);
							String srtRate = (String) table_2.getValueAt(i, 2);
							int unit = Integer.parseInt(srtUnit);
							int rate = Integer.parseInt(srtRate.replaceAll(",", ""));
							String remarks = (String) table_2.getValueAt(i, 6);
							
							//basic_listに入れる。
							bVo = new BasicsVo(details, unit, rate, kind, remarks);
							bVo.setDetails(details);
							bVo.setUnit(unit);
							bVo.setRate(rate);
							bVo.setKind(kind);
							bVo.setRemarks(remarks);
							basic_list.add(bVo);
						}
						rDao.basics_insert(basic_list);

					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "データの入力を確認してください。");//数字じゃない場合。

				} catch(NullPointerException e2){
					JOptionPane.showMessageDialog(null, "データの入力を確認してください。");//データがない場合。
				}

				// 再出力
				reView();

			}
		});// 修正する機能

		btnNewButton_3.setFont(new Font("Segoe Print", Font.BOLD, 20));
		btnNewButton_3.setBounds(261, 30, 99, 51);
		panel_4.add(btnNewButton_3);

		// 削除機能
		JButton btnNewButton_4 = new JButton("DELETE");
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				st_kind = editorPane_1.getText();
				kind = Integer.parseInt(st_kind);

				if (kind == 0) {
					int selectRow = table.getSelectedRow();
					model.removeRow(selectRow);
				} else {
					int selectRow = table_2.getSelectedRow();
					model_2.removeRow(selectRow);
				}

			}
		});// 削除機能

		btnNewButton_4.setFont(new Font("Segoe Print", Font.BOLD, 20));
		btnNewButton_4.setBounds(359, 30, 99, 51);
		panel_4.add(btnNewButton_4);
		editorPane_1.setText("0");
		editorPane_1.setForeground(Color.WHITE);

		editorPane_1.setBackground(Color.WHITE);
		editorPane_1.setEditable(false);
		editorPane_1.setEnabled(false);
		editorPane_1.setBounds(136, 80, 0, 19);
		panel_4.add(editorPane_1);

		// plus button
		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] temp = new String[8];

				st_kind = editorPane_1.getText();
				kind = Integer.parseInt(st_kind);
				
				//sbcyberpass jTable row plus
				if (kind == 0) {
					model.insertRow(model.getRowCount(), temp);
				
				//softbay jTable row plus
				} else {
					model_2.insertRow(model_2.getRowCount(), temp);
				}

			}
		});// plus button

		btnNewButton_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 50));
		btnNewButton_1.setBounds(25, 10, 44, 34);
		panel_4.add(btnNewButton_1);

		// minus button
		JButton btnNewButton_5 = new JButton("-");
		btnNewButton_5.setBackground(UIManager.getColor("Button.disabledShadow"));
		btnNewButton_5.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				st_kind = editorPane_1.getText();
				kind = Integer.parseInt(st_kind);

				//sbcyberpass jTable row minus
				if (kind == 0) {
					int reRow = model.getRowCount();
					model.removeRow(reRow - 1);
				
					//softbay jTable row plus
				} else {
					int reRow = model_2.getRowCount();
					model_2.removeRow(reRow - 1);
				}

			}
		});// minus button

		btnNewButton_5.setFont(new Font("MS UI Gothic", Font.PLAIN, 50));
		btnNewButton_5.setBounds(81, 10, 44, 34);
		panel_4.add(btnNewButton_5);

		final String solve = "Solve";
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, solve);
		table.getActionMap().put(solve, new EnterAction());
		table_2.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, solve);
		table_2.getActionMap().put(solve, new EnterAction());
	}

	// enter action
	private class EnterAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {

			st_kind = editorPane_1.getText();
			kind = Integer.parseInt(st_kind);
			sob_basics_list = new ArrayList<BasicsVo>();
			basic_list = new ArrayList<BasicsVo>();

			//sbcyberpass enter action
			if (kind == 0) {

				int row = model.getRowCount();//jTable全体列データ。
				
				//jTableにあるデータを持ち込んで新しくデータをjTableに出力。
				for (int i = 0; i < row; i++) {

					String details = (String) table.getValueAt(i, 0);
					String srtUnit = (String) table.getValueAt(i, 1);
					String srtRate = (String) table.getValueAt(i, 2);
					int unit = Integer.parseInt(srtUnit);
					int rate = Integer.parseInt(srtRate.replaceAll(",", ""));
					String remarks = (String) table.getValueAt(i, 6);

					bVo = new BasicsVo(details, unit, rate, kind, remarks);
					bVo.setDetails(details);
					bVo.setUnit(unit);
					bVo.setRate(rate);
					bVo.setKind(kind);
					bVo.setRemarks(remarks);
					basic_list.add(bVo);

				}
				basics_list(basic_list, sob_basics_list);
			
			//softbay enter action
			} else {

				int row = model_2.getRowCount();//jTable全体列データ。

				//jTableにあるデータを持ち込んで新しくデータをjTableに出力。	
				for (int i = 0; i < row; i++) {

					String details = (String) table_2.getValueAt(i, 0);
					String srtUnit = (String) table_2.getValueAt(i, 1);
					String srtRate = (String) table_2.getValueAt(i, 2);
					int unit = Integer.parseInt(srtUnit);
					int rate = Integer.parseInt(srtRate.replaceAll(",", ""));
					String remarks = (String) table_2.getValueAt(i, 6);

					bVo = new BasicsVo(details, unit, rate, kind);
					bVo.setDetails(details);
					bVo.setUnit(unit);
					bVo.setRate(rate);
					bVo.setKind(kind);
					bVo.setRemarks(remarks);
					sob_basics_list.add(bVo);

				}
				basics_list(basic_list, sob_basics_list);
			}
		}
	}// enter action

	// 테이블 출력 부분
	public void basics_list(ArrayList<BasicsVo> basics_list, ArrayList<BasicsVo> sob_basics_list) {
		// TODO Auto-generated method stub

		// sbcyberpass
		if (basics_list.size() != 0) {
			model.fireTableDataChanged();
			int a = model.getRowCount();
			for (int i = 0; i < a; i++) {
				model.removeRow(0);
			}
			for (BasicsVo vo : basics_list) {
				String rate = String.format("%,d", vo.getRate());
				int amount = vo.getUnit() * vo.getRate();
				double subAmountDouble = amount * 1.08;
				int subAmount = (int) subAmountDouble;

				String[] temp = new String[8];
				temp[0] = vo.getDetails();
				temp[1] = String.valueOf(vo.getUnit());
				temp[2] = rate;
				temp[3] = String.format("%,d", amount);
				temp[4] = "個別";
				temp[5] = String.format("%,d", subAmount);
				System.out.println(vo.getRemarks());
				if (vo.getRemarks() == null || vo.getRemarks().equals("null")) {
					temp[6] = "";
				} else {
					temp[6] = vo.getRemarks();
					
				}
				temp[7] = String.valueOf(vo.getKind());
				model.insertRow(model.getRowCount(), temp);
			}
		}

		// softbay
		if (sob_basics_list.size() != 0) {
			model_2.fireTableDataChanged();
			int b = model_2.getRowCount();
			for (int i = 0; i < b; i++) {
				model_2.removeRow(0);
			}

			for (BasicsVo svo : sob_basics_list) {
				String rate = String.format("%,d", svo.getRate());
				int amount = svo.getUnit() * svo.getRate();

				String[] temp_2 = new String[8];
				temp_2[0] = svo.getDetails();
				temp_2[1] = String.valueOf(svo.getUnit());
				temp_2[2] = rate;
				temp_2[3] = String.format("%,d", amount);
				temp_2[4] = "合算";
				temp_2[5] = String.format("%,d", amount);
				if (svo.getRemarks() == null || svo.getRemarks().equals("null")) {
					temp_2[6] = "";
				} else {
					temp_2[6] = svo.getRemarks();
					
				}
				temp_2[7] = String.valueOf(svo.getKind());
				model_2.insertRow(model_2.getRowCount(), temp_2);
			}
		}
	}

	// 再出力
	public void reView() {
		basic_list = rDao.basics();
		sob_basics_list = rDao.sob_basics();
		basics_list(basic_list, sob_basics_list);
	}
}
