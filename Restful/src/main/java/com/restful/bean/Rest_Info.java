package com.restful.bean;

public class Rest_Info {
	private String company;
	private String category;
	private String name;
	
	public Rest_Info(){
		
	}
	
	public Rest_Info(String company, String category, String name) {
		super();
		this.company = company;
		this.category = category;
		this.name = name;
	}
	
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
