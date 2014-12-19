package com.invoice.vo;

public class CallDurationVo extends Telephone {
	
	private int call_count;//通話時間変換のカウント
	private String call_date;//セーブのdate
	public CallDurationVo() {
		
	}
	
	public CallDurationVo(String telephone,int call_count,int price,String call_date){
		super(telephone,price);//Telephoneクラスの変数を連結		
		this.call_count=call_count;
		this.call_date=call_date;
	}

	public int getCall_count() {
		return call_count;
	}

	public void setCall_count(int call_count) {
		this.call_count = call_count;
	}

	public String getCall_date() {
		return call_date;
	}

	public void setCall_date(String call_date) {
		this.call_date = call_date;
	}

	@Override
	public String toString() {
		return "CallDuration [telephone=" + super.getTelephone()+",call_count=" + call_count + ",price=" + super.getPrice()+",call_date=" + call_date + "]";
	}
	
	
	
	


	
}
