package Manage;

import FirstPack.Driver;
import FirstPack.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class SupplierTypesController implements Initializable {

    @FXML
    private Label LBL_sName;
    @FXML
    private ComboBox<String> CBox_allTypes;
    @FXML
    private TextArea TXTArea;
    @FXML
    private ComboBox<String> CBox_sTypes;

    ////////////////////////////////////////////////////////////////////////

    static int ID;
    static String suppName;
    ArrayList<String> supplierTypes = new ArrayList<>();
    ArrayList<String> availableTypes = new ArrayList<>();

    ////////////////////////////////////////////////////////////////////////Methods

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            LBL_sName.setText(suppName);

            ArrayList<ArrayList<String>> twoLists = Driver.getTypesOfSupplier(ID);
            availableTypes = twoLists.get(0);
            supplierTypes = twoLists.get(1);

            refresh();

        } catch (SQLException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public static void initializeID(int id, String name) {
        ID = id;
        suppName = name;
    }

    public void addSelected(ActionEvent event) {
        String selected = CBox_allTypes.getValue();
        supplierTypes.add(selected);
        availableTypes.remove(selected);
        refresh();
    }

    public void deleteSelected(ActionEvent event) {
        String selected = CBox_sTypes.getValue();
        supplierTypes.remove(selected);
        availableTypes.add(selected);
        refresh();
    }

    ////////////////////////////////////////////////////////////////////////

    private void refresh() {
        TXTArea.setText("");
        CBox_sTypes.getItems().clear();
        CBox_allTypes.getItems().clear();

        for (String str : supplierTypes) {
            CBox_sTypes.getItems().add(str);
            TXTArea.setText(TXTArea.getText() + str + "\n");
        }

        for (String str : availableTypes) {
            CBox_allTypes.getItems().add(str);
        }
    }

    public void save(ActionEvent event) {
        try {
            Driver.updateSupplierTypes(ID, supplierTypes);

            back(event);

        } catch (SQLException e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public void back(ActionEvent event) {
        try {
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
