package com.invoice.vo;

public class FreeTelephone extends Telephone{
	
	private String call_kind;//呼種別
	private int call_count;//通話時間変換のカウント
	
	public FreeTelephone() {
		
	}
	
	public FreeTelephone(String telephone,String call_kind,int call_count,int price){
		super(telephone,price);//Telephoneクラスの変数を連結
		this.call_kind=call_kind;		
		this.call_count=call_count;		
	}

	public String getCall_kind() {
		return call_kind;
	}

	public void setCall_kind(String call_kind) {
		this.call_kind = call_kind;
	}

	public int getCall_count() {
		return call_count;
	}

	public void setCall_count(int call_count) {
		this.call_count = call_count;
	}

	@Override
	public String toString() {
		return "FreeTelephone [telephone=" + super.getTelephone()+",price=" + super.getPrice()+",call_kind=" + call_kind + ",call_count=" + call_count + "]";
	}
	
	
	
}