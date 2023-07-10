package MainMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import Manage.ManageController;
import FirstPack.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class MainMenuController implements Initializable {

    public static int permission;
    public static String username;


    @FXML
    private Button BTN_manage;
    @FXML
    private Button BTN_buySell;
    @FXML
    private Button BTN_manageOrders;
    @FXML
    private Button BTN_statistics;
    @FXML
    private Button BTN_mngUsers;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (permission == 1) {
            BTN_manage.setVisible(true);
            BTN_buySell.setVisible(false);
            BTN_manageOrders.setVisible(true);
            BTN_statistics.setVisible(true);
            BTN_mngUsers.setVisible(true);
        } else {
            BTN_manage.setVisible(false);
            BTN_buySell.setVisible(true);
            BTN_manageOrders.setVisible(false);
            BTN_statistics.setVisible(false);
            BTN_mngUsers.setVisible(false);
        }
    }

    public void logOut(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Login/loginSample.fxml"));

//			Scene scene = new Scene(root, 600, 600);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            Main.stage.setScene(scene);

        } catch (IOException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public void toOrderWindow(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Orders/Orders.fxml"));
            Scene scene = new Scene(root, 900, 800);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            Main.stage.setScene(scene);

        } catch (IOException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public void toManageOrdersWindow(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/OrdersHistory/OrdersHistory.fxml"));
            Scene scene = new Scene(root, 900, 800);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            Main.stage.setScene(scene);

        } catch (IOException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public void toStatistics(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Statistics/Statistics.fxml"));
            Scene scene = new Scene(root, 900, 700);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            Main.stage.setScene(scene);

        } catch (IOException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public void manageUsers(ActionEvent event) {
        try {
//			ManageController.fromMainMenu();
            Parent root = FXMLLoader.load(getClass().getResource("/ManageUsers/ManageUsers.fxml"));

            Scene scene = new Scene(root, 900, 700);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            Main.stage.setScene(scene);

        } catch (IOException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public void manage(ActionEvent event) {
        try {
//			ManageController.fromMainMenu();
            Parent root = FXMLLoader.load(getClass().getResource("/Manage/Manage.fxml"));

            Scene scene = new Scene(root, 900, 700);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            Main.stage.setScene(scene);

        } catch (IOException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
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
