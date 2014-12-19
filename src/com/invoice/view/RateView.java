package com.invoice.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.poi.hssf.util.HSSFColor.BLACK;

import com.invoice.dao.RateDao;

import java.awt.SystemColor;

public class RateView extends JFrame {
	private JTextField txtCyberpass;
	private JTextField txtSoftbay;
	private JTextField txtPhone;
	private JTextField textField  = new JTextField();;
	private JTextField textField_1 = new JTextField();
	private JTextField textField_2 = new JTextField();
	public RateView() {
		setTitle("Rate Option");
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		txtCyberpass = new JTextField();
		txtCyberpass.setForeground(SystemColor.textText);
		txtCyberpass.setBackground(SystemColor.textHighlightText);
		txtCyberpass.setEditable(false);
		txtCyberpass.setFont(new Font("Segoe Print", Font.BOLD, 18));
		txtCyberpass.setHorizontalAlignment(SwingConstants.CENTER);
		txtCyberpass.setText("SBCyberpass");
		txtCyberpass.setBounds(0, 32, 148, 54);
		getContentPane().add(txtCyberpass);
		txtCyberpass.setColumns(10);
		txtCyberpass.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		txtSoftbay = new JTextField();
		txtSoftbay.setBackground(Color.WHITE);
		txtSoftbay.setEditable(false);
		txtSoftbay.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoftbay.setFont(new Font("Segoe Print", Font.BOLD, 18));
		txtSoftbay.setText("Softbay");
		txtSoftbay.setBounds(146, 32, 148, 54);
		getContentPane().add(txtSoftbay);
		txtSoftbay.setColumns(10);
		txtSoftbay.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		txtPhone = new JTextField();
		txtPhone.setBackground(Color.WHITE);
		txtPhone.setEditable(false);
		txtPhone.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		txtPhone.setText("携帯");
		txtPhone.setHorizontalAlignment(SwingConstants.CENTER);
		txtPhone.setBounds(313, 31, 96, 55);
		getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		txtPhone.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		textField.setBackground(new Color(255, 250, 205));
		
		textField.setBorder(BorderFactory.createLineBorder(Color.black));
		//textField.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 25));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(31, 88, 96, 35);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField_1.setBackground(new Color(255, 250, 205));
		
		textField_1.setBorder(BorderFactory.createLineBorder(Color.black));
		//textField_1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 25));
		textField_1.setBounds(173, 87, 96, 36);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_2.setBackground(new Color(255, 250, 205));
		
		textField_2.setBorder(BorderFactory.createLineBorder(Color.black));
		//textField_2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("MS UI Gothic", Font.PLAIN, 25));
		textField_2.setBounds(313, 87, 96, 36);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		//rate 修正
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> rateList = new ArrayList<String>() ; 
				String sbRate = textField.getText();
				String sobRate = textField_1.getText();
				String phoneRate = textField_2.getText();
				
		
				rateList.add(sbRate);
				rateList.add(sobRate);
				rateList.add(phoneRate);
				
				RateDao rDAo = new RateDao();
				rDAo.rate_update(rateList);
				
			}
		});//rate 修正
		
		
		btnNewButton.setFont(new Font("Segoe Print", Font.BOLD, 20));
		btnNewButton.setBounds(134, 175, 91, 54);
		getContentPane().add(btnNewButton);
		
		//終了
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});//終了
		
		
		btnNewButton_1.setFont(new Font("Segoe Print", Font.BOLD, 20));
		btnNewButton_1.setBounds(225, 175, 91, 54);
		getContentPane().add(btnNewButton_1);
	}
	
	//DBで持ち込んだデータを画面に出力
	public void rate_list(ArrayList<String> rate_List) {
		// TODO Auto-generated method stub
		textField.setText(rate_List.get(0));
		textField_1.setText(rate_List.get(1));
		textField_2.setText(rate_List.get(2));
	}
}
