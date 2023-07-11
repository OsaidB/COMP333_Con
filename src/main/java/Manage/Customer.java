package Manage;

import java.time.LocalDate;

public class Customer {
	int id;
	String name;
	int phone;
	LocalDate dateOfAdding;
	String address;
	
	
	public Customer(int id, String name, int phone, String dateOfAdding, String address) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.dateOfAdding = LocalDate.parse(dateOfAdding);
		this.address = address;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public String getDateOfAdding() {
		return dateOfAdding.toString();
	}


	public void setDateOfAdding(String dateOfAdding) {
		this.dateOfAdding = LocalDate.parse(dateOfAdding);
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
