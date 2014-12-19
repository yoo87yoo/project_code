package com.invoice.vo;

public class BasicsVo {
	
	private String details;
	private int unit;
	private int rate;
	private int kind;
	private String remarks;
	
	BasicsVo(){}

	public BasicsVo(String details, int unit, int rate, int kind, String remarks) {
		super();
		this.details = details;
		this.unit = unit;
		this.rate = rate;
		this.kind = kind;
		this.remarks = remarks;
	}
	
	public BasicsVo(String details, int unit, int rate, int kind) {
		super();
		this.details = details;
		this.unit = unit;
		this.rate = rate;
		this.kind = kind;
		this.remarks = remarks;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "BasicsVo [details=" + details + ", unit=" + unit + ", rate=" + rate + ", kind=" + kind + ", remarks="
				+ remarks + "]";
	}

	
	
	
	
	
}
