package com.lazy.st.entity;

import java.util.Date;

public class StCode {

	private String code; // 股票编码
	private String name; // 股票名称
	private String type; // sh , sz 
	private  Date lastDate; // last change
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
}
