package OrdersHistory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import FirstPack.Driver;
import FirstPack.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrdersHistoryController implements Initializable {

    @FXML
    private TableView<OrderHistory> table;
    @FXML
    private TableColumn<OrderHistory, String> dateAndTime;
    @FXML
    private TableColumn<OrderHistory, String> item;
    @FXML
    private TableColumn<OrderHistory, Double> price;
    @FXML
    private TableColumn<OrderHistory, Integer> quantity;
    @FXML
    private TableColumn<OrderHistory, String> customer;
    @FXML
    private TableColumn<OrderHistory, String> employee;

    ObservableList<OrderHistory> list;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            list = Driver.getOrderHistory();

            dateAndTime.setCellValueFactory(new PropertyValueFactory<OrderHistory, String>("dateAndTime"));
            item.setCellValueFactory(new PropertyValueFactory<OrderHistory, String>("item"));
            price.setCellValueFactory(new PropertyValueFactory<OrderHistory, Double>("price"));
            quantity.setCellValueFactory(new PropertyValueFactory<OrderHistory, Integer>("quantity"));
            customer.setCellValueFactory(new PropertyValueFactory<OrderHistory, String>("customerName"));
            employee.setCellValueFactory(new PropertyValueFactory<OrderHistory, String>("employeeName"));

            table.setItems(list);

        } catch (SQLException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }

    }

    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenuSample.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            Main.stage.setScene(scene);

        } catch (IOException e) {
//			e.printStackTrace();
            Alert ALRT_databaseError = new Alert(AlertType.ERROR);
            ALRT_databaseError.setTitle("Error!!");
            ALRT_databaseError.setHeaderText("Error Message: " + e.getMessage());
            ALRT_databaseError.setContentText(null);
            ALRT_databaseError.show();
        }
    }

    private void showErrorMessage(String str) {
        Alert ALRT_databaseError = new Alert(AlertType.ERROR);
        ALRT_databaseError.setTitle("Error!!");
        ALRT_databaseError.setHeaderText("Error Message: " + str);
        ALRT_databaseError.setContentText(null);
        ALRT_databaseError.show();
    }
}
