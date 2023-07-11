package Manage;

import java.time.LocalDate;

public class Item implements Cloneable {
	int id;
	String model;
	double purchasePrice;
	double sellingPrice;
	LocalDate dateOfAdding;
	String description;
	String supplierName;
	String type;

	int quantity;

	public Item(int id, String model, double purchasePrice, double sellingPrice, String dateOfAdding, String description, String supplierName, String type, int quantity) {
		super();
		this.id = id;
		this.model = model;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
		this.dateOfAdding = LocalDate.parse(dateOfAdding);
		this.description = description;
		this.supplierName = supplierName;
		this.type = type;

		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getDateOfAdding() {
		return dateOfAdding.toString();
	}

	public void setDateOfAdding(String dateOfAdding) {
		this.dateOfAdding = LocalDate.parse(dateOfAdding);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
