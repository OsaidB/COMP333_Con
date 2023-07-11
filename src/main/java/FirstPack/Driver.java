package FirstPack;

import Manage.Customer;
import Manage.Employee;
import Manage.Item;
import Manage.Supplier;
import Orders.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class Driver {

    public static Connection con;


    //login pack
    public static int checkUser(String username, String password) throws SQLException, SQLException {
        String STR_admins = "SELECT uPassword FROM SystemUsers WHERE Username = '" + username + "'";
        Statement STT_admins = con.createStatement();

        ResultSet RS_admins = STT_admins.executeQuery(STR_admins);

        if (RS_admins.next()) {
            if (RS_admins.getString(1).equals(password))
                return 1;
        }

        String STR_employees = "SELECT uPassword FROM EmployeeUsers WHERE eID = " + username;

        if (isInteger(username)) {

            Statement STT_employees = con.createStatement();
            ResultSet RS_employees = STT_employees.executeQuery(STR_employees);

            if (RS_employees.next()) {
                if (RS_employees.getString(1).equals(password))
                    return 2;
            }

        }

        return 0;
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);

        } catch (Exception e) {
            return false;

        }

        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////Orders.OrdersController
    //Orders pack (OrdersController)

    //called to initialize OrdersController
    public static ObservableList<Item> getItemsFromDatabase() throws SQLException {//called to initialize OrdersController
        ObservableList<Item> list = FXCollections.observableArrayList();

        String STR = "SELECT I.ID, I.ModelNumber, I.PurchasePrice, I.SellingPrice, I.DateOfAdding, I.iDescription, S.sName, T.tName,  I.Quantity"
                + "	FROM Item I, Supplier S, ItemType T WHERE I.ItemType = T.ID AND I.SupplierID = S.ID";

        Statement STT = con.createStatement();
        ResultSet RS = STT.executeQuery(STR);

        while (RS.next()) {
            list.add(new Item(RS.getInt("ID"), RS.getString("ModelNumber"), RS.getDouble("PurchasePrice"), RS.getDouble("SellingPrice"), RS.getDate("DateOfAdding").toString(),
                    RS.getString("iDescription"), RS.getString("sName"), RS.getString("tName"), RS.getInt("Quantity")));
        }

        return list;
    }

    public static ObservableList<CartItem> itemsToReturn(int orderID) throws SQLException {
        ObservableList<CartItem> items = FXCollections.observableArrayList();

        String STR = "SELECT I.ID, I.ModelNumber, I.iDescription,  D.Quantity, D.Price, D.Quantity*D.Price AS TotalPrice " + "FROM OrderDetails D, Item I WHERE D.iID = I.ID AND D.oID = " + orderID;
        Statement STT = con.createStatement();
        ResultSet RS = STT.executeQuery(STR);

        while (RS.next()) {
            items.add(new CartItem(RS.getInt(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getDouble(5), RS.getShort(6)));
        }

        return items;
    }

    public static void returnItem(int itemID, int quantity, int orderID) throws SQLException {
        String STR_updateItemQuantity = String.format("UPDATE Item SET Quantity = Quantity+%d WHERE ID = %d", quantity, itemID);
        Statement STT_updateItemQuantity = con.createStatement();
        STT_updateItemQuantity.execute(STR_updateItemQuantity);

        String STR_deleteOrderDetail = String.format("DELETE FROM OrderDetails WHERE oID = %d AND iID = %d", orderID, itemID);
        Statement STT_deleteOrderDetail = con.createStatement();
        STT_deleteOrderDetail.execute(STR_deleteOrderDetail);
    }

    public static int insertOrderData(ObservableList<Item> iList, ObservableList<CartItem> cList, int employeeID, int customerID) throws SQLException {
        for (Item item : iList) {
            String STR_updateItems = String.format("UPDATE Item SET Quantity = %d WHERE ID = %d", item.getQuantity(), item.getId());
            Statement STT_updateItems = con.createStatement();
            STT_updateItems.execute(STR_updateItems);
        }

        String STR_insertOrder = String.format("INSERT INTO Orders (cID, eID) VALUE (%d, %d)", customerID, employeeID);
        Statement STT_insertOrder = con.createStatement();
        STT_insertOrder.execute(STR_insertOrder);

        String STR_getOrderID = "SELECT ID FROM Orders ORDER BY ID DESC LIMIT 1";
        Statement STT_getOrderID = con.createStatement();
        ResultSet RS = STT_getOrderID.executeQuery(STR_getOrderID);
        RS.next();
        int orderID = RS.getInt(1);

        for (CartItem item : cList) {
            String STR_insertOrderDetails = String.format("INSERT INTO OrderDetails (oID, iID, Price, Quantity) VALUE (%d, %d, %f, %d)", orderID, item.getId(), item.getUnitPrice(),
                    item.getQuantity());
            Statement STT_insertOrderDetails = con.createStatement();
            STT_insertOrderDetails.execute(STR_insertOrderDetails);
        }

        return orderID;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////Manage.ManageController
    //Manage pack (ManageController)

    ////////////////////////////////////////////////////////////////////////////////////////////////////ADD

    public static void addNewEmployeeToTheDatabase(String name, int ssn, int phoneNumber, String address, double salary) throws SQLException {
        String STR = String.format("INSERT INTO Employee(eName, SSN, Phone, Address, Salary) VALUE ('%s', %d, %d, '%s', %f)", name, ssn, phoneNumber, address, salary);
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    public static void addNewSupplierToTheDatabase(String name, int phoneNumber, String info, String address) throws SQLException {
        String STR = String.format("INSERT INTO Supplier(sName, Phone, MoreInfo, Address) VALUE ('%s', %d,  '%s', '%s')", name, phoneNumber, info, address);
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    public static void addNewCustomerToTheDatabase(String name, int phoneNumber, String address) throws SQLException {
        String STR = String.format("INSERT INTO Customer(cName, PhoneNumber, Address) VALUE ('%s', %d, '%s')", name, phoneNumber, address);
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    public static void addNewItemToTheDatabase(String TF_iModelNumber, double TF_iPurchasePrice, double TF_iSellingPrice, String TF_iDescription, int TF_Type1, int TF_isupplier1, int TF_iQuantity) throws SQLException {
        String STR = String.format("INSERT INTO Item(ModelNumber, PurchasePrice, SellingPrice, ItemType, SupplierID, iDescription,  Quantity) VALUE ('%s', %f, %f, %d,'%s','%s',%d)", TF_iModelNumber,
                TF_iPurchasePrice, TF_iSellingPrice, TF_Type1, TF_isupplier1, TF_iDescription, TF_iQuantity);
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////Get --> UPDATE
    //=====================Employees=====================//

    public static ObservableList<Employee> getEmployeesFromDatabase() throws SQLException {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        String STR = "SELECT * FROM Employee;";
        Statement STT = con.createStatement();
        ResultSet RS = STT.executeQuery(STR);

        while (RS.next()) {
            Date grabbedDate = RS.getDate("EndDate");////
            String endDate = (grabbedDate == null) ? "Still Working" : grabbedDate.toString();
            list.add(new Employee(RS.getInt("ID"), RS.getString("eName"), RS.getInt("SSN"), RS.getInt("Phone"), RS.getString("Address"), RS.getDate("StartDate").toString(), endDate,
                    RS.getDouble("Salary")));
        }

        return list;
    }

    public static void updateEmployee(int id, String name, int ssn, int phoneNumber, String address, double salary, String startDate, String endDate) throws SQLException {

        String STR = String.format("UPDATE Employee SET eName = '%s', SSN =  %d, Phone = %d, Address = '%s', Salary = %f, StartDate = '%s'," + "EndDate = %s WHERE ID = %d", name, ssn, phoneNumber,
                address, salary, startDate, endDate, id);
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    //=====================Suppliers=====================//

    public static ObservableList<Supplier> getSuppliersFromDatabase() throws SQLException {
        ObservableList<Supplier> list = FXCollections.observableArrayList();
        String STR = "SELECT * FROM Supplier;";
        Statement STT = con.createStatement();
        ResultSet RS = STT.executeQuery(STR);

        while (RS.next()) {
            list.add(new Supplier(RS.getInt("ID"), RS.getString("sName"), RS.getInt("Phone"), RS.getString("Address"), RS.getDate("DateOfAdding").toString(), RS.getString("MoreInfo")));
        }

        return list;
    }

    public static void updateSupplier(int id, String name, int phoneNumber, String info, String address) throws SQLException {
        String STR = String.format("UPDATE Supplier SET sName = '%s', Phone =  %d,  MoreInfo = '%s', Address = '%s' WHERE ID = %d", name, phoneNumber, info, address, id);
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    //=====================Customers=====================//

    public static ObservableList<Customer> getCustomersFromDatabase() throws SQLException {
        ObservableList<Customer> list = FXCollections.observableArrayList();
        String STR = "SELECT * FROM Customer";
        Statement STT = con.createStatement();
        ResultSet RS = STT.executeQuery(STR);

        while (RS.next()) {
            list.add(new Customer(RS.getInt("ID"), RS.getString("cName"), RS.getInt("PhoneNumber"), RS.getDate("DateOfAdding").toString(), RS.getString("Address")));
        }

        return list;
    }

    public static void updateCustomer(int id, String name, int phoneNumber, String address) throws SQLException {
        String STR = String.format("UPDATE Customer SET cName = '%s', PhoneNumber =  %d, Address = '%s' WHERE ID = %d", name, phoneNumber, address, id);
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    //=====================Items=====================//

//    public static ObservableList<Item> getItemsFromDatabase() throws SQLException {
//       @  Line 63 From this Class   @
//    }

    //////////////////////////////////////////////////////////////////////////////////////////////////Delete
    public static void deleteEmployee(int ID) throws SQLException {
        String STR = "DELETE FROM Employee WHERE ID = " + ID;
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    public static void deleteSupplier(int ID) throws SQLException {
        String STR = "DELETE FROM Supplier WHERE ID = " + ID;
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    public static void deleteCustomer(int ID) throws SQLException {
        String STR = "DELETE FROM Customer WHERE ID = " + ID;
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    public static void deleteItems(int ID) throws SQLException {
        String STR = "DELETE FROM Item WHERE ID = " + ID;
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void fireEmployee(int ID) throws SQLException {
        String STR = "UPDATE Employee SET endDate = (NOW()) WHERE ID = " + ID + "  AND EndDate IS NULL";
        Statement STT = con.createStatement();
        STT.execute(STR);
    }

    //////////////////////////////////////////////////////////////////////////////////////Manage.SupplierTypesController

    public static ArrayList<ArrayList<String>> getTypesOfSupplier(int ID) throws SQLException {
        ArrayList<ArrayList<String>> twoLists = new ArrayList<>();
        ArrayList<String> allTypes = new ArrayList<>();
        ArrayList<String> supplierTypes = new ArrayList<>();

        String STR_getAllTypesSQL = "SELECT tName FROM ItemType";
        Statement STT_getAllTypes = con.createStatement();
        ResultSet RS_allTypes = STT_getAllTypes.executeQuery(STR_getAllTypesSQL);
        while (RS_allTypes.next())
            allTypes.add(RS_allTypes.getString(1));

        String STR_getSuppTypesSQL = "SELECT T.tName FROM ItemType T, Supplier_Type ST WHERE T.ID = tID AND sID = " + ID;
        Statement STT_getSuppTypes = con.createStatement();
        ResultSet RS_suppTypes = STT_getSuppTypes.executeQuery(STR_getSuppTypesSQL);
        while (RS_suppTypes.next())
            supplierTypes.add(RS_suppTypes.getString(1));

        for (String str : supplierTypes)
            allTypes.remove(str);

        twoLists.add(allTypes);
        twoLists.add(supplierTypes);
        return twoLists;
    }

    public static void updateSupplierTypes(int ID, ArrayList<String> supplierTypes) throws SQLException {
        String STR_removeAllTypesOfSupplier = "DELETE FROM Supplier_Type WHERE sID = " + ID;
        Statement STT_removeAllTypesOfSupplier = con.createStatement();
        STT_removeAllTypesOfSupplier.execute(STR_removeAllTypesOfSupplier);

        for (String str : supplierTypes) {
            String STR_getItemID = "SELECT ID FROM ItemType WHERE tName = '" + str + "'";
            Statement STT_getItemID = con.createStatement();
            ResultSet RS_itemID = STT_getItemID.executeQuery(STR_getItemID);
            RS_itemID.next();
            int ItemID = RS_itemID.getInt(1);

            String STR_InsertSupplierItem = String.format("INSERT INTO Supplier_Type(sID, tID) VALUE (%d, %d)", ID, ItemID);
            Statement STT_InsertSupplierItem = con.createStatement();
            STT_InsertSupplierItem.execute(STR_InsertSupplierItem);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////smth.smth

}
