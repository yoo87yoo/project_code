package com.invoice.vo;

public class Telephone {
	
	private String telephone;//ご利用電話番号
	private int price;//通話時間変換の合計(分数)


	
	
	public Telephone(){}

	public Telephone(String telephone,int price){
		this.telephone=telephone;
		this.price=price;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Telephone [telephone=" + telephone
				+ ", price=" + price + "]";
	}	
	
	
	
	
}
