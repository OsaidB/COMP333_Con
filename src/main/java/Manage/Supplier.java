package Manage;

import java.time.LocalDate;

import javafx.scene.control.Button;

public class Supplier {
	int id;
	String name;
	int phoneNumber;
	
	String address;
	LocalDate dateOfAdding;
	String info;
	Button BTN_types;
	
	
	public Supplier(int id, String name, int phoneNumber,  String address, String dateOfAdding,
			String info) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
	
		this.address = address;
		this.dateOfAdding = LocalDate.parse(dateOfAdding);
		this.info = info;
		
		BTN_types = new Button("Types");
		BTN_types.setOnAction(e -> {
			ManageController.showTypes(id, name);
		});
	}


	
	public Button getBTN_types() {
		return BTN_types;
	}

	public void setBTN_types(Button bTN_types) {
		BTN_types = bTN_types;
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

	public String getDateOfAdding() {
		return dateOfAdding.toString();
	}

	public void setDateOfAdding(String dateOfAdding) {
		this.dateOfAdding = LocalDate.parse(dateOfAdding);
	}

	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
}
