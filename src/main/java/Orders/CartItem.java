package Orders;

public class CartItem implements Cloneable {
    int id;
    String model;
    String descriptin;

    int quantity;
    double unitPrice;
    double totalPrice;

    public CartItem(int id, String model, String descriptin, double unitPrice) {
        this.id = id;
        this.model = model;
        this.descriptin = descriptin;

        this.unitPrice = unitPrice;
    }

    public CartItem(int id, String model, String descriptin, int quantity, double unitPrice, double totalPrice) {
        this.id = id;
        this.model = model;
        this.descriptin = descriptin;

        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
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

    public String getDescriptin() {
        return descriptin;
    }

    public void setDescriptin(String descriptin) {
        this.descriptin = descriptin;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateTotalPrice();
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        updateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void updateTotalPrice() {
        this.totalPrice = this.quantity * this.unitPrice;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
