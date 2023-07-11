package Manage;

import java.time.LocalDate;

public class Employee {
	private int id;
	private String name;
	private int ssn;
	private int phoneNumber;
	private String address;
	private LocalDate startDate;
	private LocalDate endDate;
	private double salary;

	
	
	
	public Employee(int id, String name, int ssn, int phoneNumber, String address, String startDate,
			String endDate, double salary) {
		this.id = id;
		this.name = name;
		this.ssn = ssn;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.startDate = LocalDate.parse(startDate);
		this.salary = salary;
		
		if (endDate.equals("Still Working"))
			this.endDate = null;
		else
			this.endDate = LocalDate.parse(endDate);
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

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStartDate() {
		return startDate.toString();
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		if (endDate == null)
			return "Still Working";
		else
			return endDate.toString();
	}

	public void setEndDate(String endDate) {
		if (endDate.equals("Still Working"))
			this.endDate = null;
		else 
			this.endDate = LocalDate.parse(endDate);
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
