package com.application.beans;

public class Customer {
	private  static int customerID;
	private static String name;
	private static String surname;
	private String phoneNumber;
	private String user;
	private String password;
	
	public static int getCustomerID() {
		return customerID;
	}
	 public void setCustomerID(int customerID) {
		 this.customerID = customerID;
	}
	public static String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
