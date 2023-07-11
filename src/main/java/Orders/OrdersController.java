package Orders;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import MainMenu.MainMenuController;
import Manage.Item;
import Manage.ManageController;
import FirstPack.Driver;
import FirstPack.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OrdersController implements Initializable {

    @FXML
    private TextField TF_search;

    ////////////////////////////UpperTable////////////////////////////
    //      A table contains "products available for selling"       //
    //  and you can select one item of them and ADD it to the cart  //
    @FXML
    private TableView<Item> iTable;

    @FXML
    private TableColumn<Item, Integer> CLM_iID;
    @FXML
    private TableColumn<Item, String> CLM_iModel;
    @FXML
    private TableColumn<Item, Double> CLM_iPurchase;
    @FXML
    private TableColumn<Item, Double> CLM_iSelling;
    @FXML
    private TableColumn<Item, String> CLM_iDescription;
    @FXML
    private TableColumn<Item, String> CLM_iSupplier;
    @FXML
    private TableColumn<Item, String> CLM_iType;
    @FXML
    private TableColumn<Item, Integer> CLM_iQuantity;
    ////////////////////////////UpperTable////////////////////////////

    /////////////////////////////LowerTable//////////////////////////////
    //   A table contains "all products that existing in your cart"   //
    //and you can select one item of them and REMOVE it from the cart//
    @FXML
    private TableView<CartItem> cartTable;

    @FXML
    private TableColumn<CartItem, Integer> cID;
    @FXML
    private TableColumn<CartItem, String> cModel;
    @FXML
    private TableColumn<CartItem, String> cDescription;
    @FXML
    private TableColumn<CartItem, Integer> cQuantity;
    @FXML
    private TableColumn<CartItem, Double> cUnitPrice;
    @FXML
    private TableColumn<CartItem, Double> cTotalPrice;
    /////////////////////////////LowerTable//////////////////////////////

    @FXML
    private TextField TF_quantity;
    @FXML
    private TextField TF_unitPrice;

    ////////////////////////////////////////////////////////////////////

    @FXML
    private Label LBL_totalPrice;

    ////////////////////////////////////////////////////////////////////

    @FXML
    private TextField TF_employeeID;
    @FXML
    private TextField TF_customerID;

    ////////////////////////////////////////////////////////////////////

    @FXML
    private TextField TF_orderID;
    /////////////////////////////ReturnTable/////////////////////////////
    @FXML
    private TableView<CartItem> cartTable1;
    @FXML
    private TableColumn<CartItem, Integer> cID1;
    @FXML
    private TableColumn<CartItem, String> cModel1;
    @FXML
    private TableColumn<CartItem, String> cDescription1;
    @FXML
    private TableColumn<CartItem, Integer> cQuantity1;
    @FXML
    private TableColumn<CartItem, Double> cUnitPrice1;
    @FXML
    private TableColumn<CartItem, Double> cTotalPrice1;
    //////////////////////////////\ReturnTable\//////////////////////////////

//    @FXML
//    private TextField TF_employeeID1;

    ObservableList<Item> iList;
    int iIndex = -1;
    ObservableList<CartItem> cList;
    int cIndex = -1;
    ObservableList<CartItem> cList1;
    int cIndex1 = -1;

    int orderId;
    ////////////////////////////\\ReturnTable\\////////////////////////////
    //Methods
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            TF_employeeID.setText(MainMenuController.username);
            iList = Driver.getItemsFromDatabase();

            cList = FXCollections.observableArrayList();
            cList1 = FXCollections.observableArrayList();

            updateItemsTable();

        } catch (SQLException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    ////////////////////<Buttons>////////////////////
    public void addItemToCart(ActionEvent event) {
        iIndex = iTable.getSelectionModel().getSelectedIndex();
        CartItem item = new CartItem(CLM_iID.getCellData(iIndex), CLM_iModel.getCellData(iIndex), CLM_iDescription.getCellData(iIndex), CLM_iSelling.getCellData(iIndex));

        boolean flag = true;
        for (CartItem cItem : cList) {
            if (cItem.getId() == item.getId())
                flag = false;
        }

        if (flag) {
            cList.add(item);
            updateCartTable();
        }
    }

    public void updateQuantityAndPrice(ActionEvent event) {
        try {
            cIndex = cartTable.getSelectionModel().getSelectedIndex();

            if (cIndex > -1) {
                if (TF_quantity.getText() != "" && TF_unitPrice.getText() != "") {

                    int quantity = Integer.parseInt(TF_quantity.getText());
                    double unitPrice = Double.parseDouble(TF_unitPrice.getText());

                    if (unitPrice < 0)
                        throw new Exception("Price cannot be less than zero");

                    ///////////////////////////////////
                    int id = cID.getCellData(cIndex);
                    int firstTableIndex = -1;

                    for (int i = 0; i < iList.size(); i++) {
                        if (iList.get(i).getId() == id) {
                            firstTableIndex = i;

                            break;
                        }
                    }
                    ///////////////////////////////////

                    int itemQuantity = iList.get(firstTableIndex).getQuantity();

                    if (quantity > itemQuantity)
                        throw new Exception("Available Quantity Doesn\'t Enough");

                    int def = quantity - cList.get(cIndex).getQuantity();

                    Item item = (Item) iList.get(firstTableIndex).clone();
                    item.setQuantity(itemQuantity - def);

                    iList.add(firstTableIndex, item);
                    iList.remove(firstTableIndex + 1);

                    CartItem cItem = (CartItem) cList.get(cIndex).clone();
                    cItem.setQuantity(quantity);
                    cItem.setUnitPrice(unitPrice);

                    cList.add(cIndex, cItem);
                    cList.remove(cIndex + 1);

                    updateItemsTable();
                    updateCartTable();

                    double totalPrice = 0;
                    for (CartItem itemC : cList)
                        totalPrice += itemC.getTotalPrice();
                    LBL_totalPrice.setText("Total Price: " + totalPrice);
                } else {
                    showErrorMessage("You Should Enter Quantity And Unit Price");
                }
            } else {
                showErrorMessage("Select Item From The Cart");
            }
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    public void removeCartItem(ActionEvent event) {
        try {
            cIndex = cartTable.getSelectionModel().getSelectedIndex();

            int quantity = cList.get(cIndex).getQuantity();

            int id = cID.getCellData(cIndex);
            int firstTableIndex = -1;
            for (int i = 0; i < iList.size(); i++) {
                if (iList.get(i).getId() == id) {
                    firstTableIndex = i;
                    break;
                }
            }

            Item item = (Item) iList.get(firstTableIndex).clone();
            item.setQuantity(item.getQuantity() + quantity);
            iList.add(firstTableIndex, item);
            iList.remove(firstTableIndex + 1);

            cList.remove(cIndex);

            updateItemsTable();
            updateCartTable();

        } catch (CloneNotSupportedException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenuSample.fxml"));
            Scene scene = new Scene(root, 600, 700);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            FirstPack.Main.stage.setScene(scene);

        } catch (IOException e) {
//			e.printStackTrace();
            Alert ALRT_databaseError = new Alert(AlertType.ERROR);
            ALRT_databaseError.setTitle("Error!!");
            ALRT_databaseError.setHeaderText("Error Message: " + e.getMessage());
            ALRT_databaseError.setContentText(null);
            ALRT_databaseError.show();
        }
    }

    //<Driver>//
    public void checkOut(ActionEvent event) {
        try {
            if (cList.size() == 0)
                throw new Exception("Add items to the cart!!");

            for (CartItem item : cList) {
                if (item.getQuantity() == 0)
                    throw new Exception("Make sure you determine quantity of all items in cart!");

                if (item.getTotalPrice() < 0)
                    throw new Exception("Error in prices");
            }

            if (TF_employeeID.getText() == "" || TF_customerID.getText() == "")
                throw new Exception("Please Select Customer ID");

            int idd = FirstPack.Driver.insertOrderData(iList, cList, Integer.parseInt(TF_employeeID.getText()), Integer.parseInt(TF_customerID.getText()));

            Alert ALRT_databaseError = new Alert(AlertType.INFORMATION);
            ALRT_databaseError.setTitle("Done");
            ALRT_databaseError.setHeaderText(LBL_totalPrice.getText());
            ALRT_databaseError.setContentText("OrderID: " + idd);
            ALRT_databaseError.show();

        } catch (Exception e) {
//    		e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public void showCustomers(ActionEvent event) {
        try {
            ManageController.fromOrders();

            Parent root = FXMLLoader.load(getClass().getResource("/Manage/Manage.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            Stage stg = new Stage();
            stg.setScene(scene);
            stg.show();

        } catch (IOException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }
    //</Driver/>//
    ////////////////////</Buttons/>////////////////////

    public void getSelectedItemOnMouse(MouseEvent event) {
        getSelectedCartitem();
    }

    @FXML
    public void getSelectedItemOnKey(KeyEvent event) {
        getSelectedCartitem();
    }

    private void getSelectedCartitem() {
        cIndex = cartTable.getSelectionModel().getSelectedIndex();

        if (cIndex > -1) {
            TF_quantity.setText(cQuantity.getCellData(cIndex).toString());
            TF_unitPrice.setText(cUnitPrice.getCellData(cIndex).toString());
        }
    }

    //////////////////////////////////////////////////

    private void updateItemsTable() {
        CLM_iID.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        CLM_iModel.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
        CLM_iPurchase.setCellValueFactory(new PropertyValueFactory<Item, Double>("purchasePrice"));
        CLM_iSelling.setCellValueFactory(new PropertyValueFactory<Item, Double>("sellingPrice"));
        CLM_iDescription.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        CLM_iSupplier.setCellValueFactory(new PropertyValueFactory<Item, String>("supplierName"));
        CLM_iType.setCellValueFactory(new PropertyValueFactory<Item, String>("type"));

        CLM_iQuantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("Quantity"));

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Item> filteredData = new FilteredList<>(iList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        TF_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                String description = (item.getDescription() == null) ? "" : item.getDescription();

                if (item.getModel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (description.toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(item.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else
                    return false; // Does not match.
            });
        });

        SortedList<Item> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(iTable.comparatorProperty());
        iTable.setItems(sortedData);
    }

    private void updateCartTable() {
        cID.setCellValueFactory(new PropertyValueFactory<CartItem, Integer>("id"));
        cModel.setCellValueFactory(new PropertyValueFactory<CartItem, String>("model"));
        cDescription.setCellValueFactory(new PropertyValueFactory<CartItem, String>("description"));

        cQuantity.setCellValueFactory(new PropertyValueFactory<CartItem, Integer>("quantity"));
        cUnitPrice.setCellValueFactory(new PropertyValueFactory<CartItem, Double>("unitPrice"));
        cTotalPrice.setCellValueFactory(new PropertyValueFactory<CartItem, Double>("totalPrice"));

        cartTable.setItems(cList);
    }

    public void updateCartTable1() {
        cID1.setCellValueFactory(new PropertyValueFactory<CartItem, Integer>("id"));
        cModel1.setCellValueFactory(new PropertyValueFactory<CartItem, String>("model"));
        cDescription1.setCellValueFactory(new PropertyValueFactory<CartItem, String>("description"));

        cQuantity1.setCellValueFactory(new PropertyValueFactory<CartItem, Integer>("quantity"));
        cUnitPrice1.setCellValueFactory(new PropertyValueFactory<CartItem, Double>("unitPrice"));
        cTotalPrice1.setCellValueFactory(new PropertyValueFactory<CartItem, Double>("totalPrice"));

        cartTable1.setItems(cList1);
    }

    //////////////////////////////////////////////////

    public void onlyInteger(KeyEvent event) {
        TextField TF = (TextField) event.getSource();
        TF.setText(TF.getText().replaceAll("[^\\d]", ""));
        TF.positionCaret(TF.getText().length());
    }

    private void showErrorMessage(String str) {
        Alert ALRT_databaseError = new Alert(AlertType.ERROR);
        ALRT_databaseError.setTitle("Error!!");
        ALRT_databaseError.setHeaderText("Error Message: " + str);
        ALRT_databaseError.setContentText(null);
        ALRT_databaseError.show();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////Driver

    //return tab
    public void getItemsToReturn(ActionEvent event) {
        try {
            if (TF_orderID.getText() == "")
                throw new Exception("Write orderId to get items!!");

            orderId = Integer.parseInt(TF_orderID.getText());
            cList1 = Driver.itemsToReturn(orderId);

            updateCartTable1();

            if (cList1.size() == 0)
                throw new Exception("Check Another ID");
        } catch (Exception e) {
//    		e.printStackTrace();
            showErrorMessage(e.getMessage());
        }

    }

    public void returnItem(ActionEvent event) throws ClassNotFoundException {
        try {
            cIndex1 = cartTable1.getSelectionModel().getSelectedIndex();

            int id = cList1.get(cIndex1).getId();
            int quantity = cList1.get(cIndex1).getQuantity();

            Driver.returnItem(id, quantity, orderId);

            cList1 = Driver.itemsToReturn(orderId);
            updateCartTable1();
            iList = Driver.getItemsFromDatabase();
            updateItemsTable();

        } catch (SQLException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }



}
