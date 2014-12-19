package com.invoice.vo;

public class InternationalVo {

	private String international_details;
	private String international_price;
	private String international_date;
	public InternationalVo(String international_details, String international_price, String international_date) {
		super();
		this.international_details = international_details;
		this.international_price = international_price;
		this.international_date = international_date;
	}
	public String getInternational_details() {
		return international_details;
	}
	public void setInternational_details(String international_details) {
		this.international_details = international_details;
	}
	public String getInternational_price() {
		return international_price;
	}
	public void setInternational_price(String international_price) {
		this.international_price = international_price;
	}
	public String getInternational_date() {
		return international_date;
	}
	public void setInternational_date(String international_date) {
		this.international_date = international_date;
	}
	@Override
	public String toString() {
		return "InternationalVo [international_details=" + international_details + ", international_price="
				+ international_price + ", international_date=" + international_date + "]";
	}
	
	

	
}
