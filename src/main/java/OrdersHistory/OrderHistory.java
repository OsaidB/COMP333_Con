package OrdersHistory;

public class OrderHistory {
	String dateAndTime;
	String item;
	double price;
	int quantity;
	String customerName;
	String EmployeeName;
	
	
	public OrderHistory(String dateAndTime, String item, double price, int quantity, String customerName,
			String employeeName) {
		super();
		this.dateAndTime = dateAndTime;
		this.item = item;
		this.price = price;
		this.quantity = quantity;
		this.customerName = customerName;
		EmployeeName = employeeName;
	}
	public String getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmployeeName() {
		return EmployeeName;
	}
	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}
	
	
}
