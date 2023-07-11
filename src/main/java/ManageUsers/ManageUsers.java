package ManageUsers;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ManageUsers implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> CLM_username;
    @FXML
    private TableColumn<User, String> CLM_password;
    @FXML
    private TableColumn<User, String> CLM_name;
    @FXML
    private TextField TF_username;
    @FXML
    private TextField TF_tblPassword;
    @FXML
    private TextField TF_password;

    ObservableList<User> list;
    int index = -1;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            updateTable();


        } catch (SQLException | ClassNotFoundException e) {
            //		e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////

    @FXML
    public void getSelectedUserOnMouse(MouseEvent event) {
        getSelectedUser();
    }

    @FXML
    public void getSelectedUserOnKey(KeyEvent event) {
        getSelectedUser();
    }

    private void getSelectedUser() {
        index = table.getSelectionModel().getSelectedIndex();
        String password = CLM_password.getCellData(index);
        TF_tblPassword.setText(password);
    }

    /////////////////////////////////////////////////////////////////////////////////////

    private void updateTable() throws SQLException, ClassNotFoundException {
        list = Driver.getUsersFromDatabase();

        CLM_name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        CLM_username.setCellValueFactory(new PropertyValueFactory<User, Integer>("username"));
        CLM_password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        table.setItems(list);

    }

    /////////////////////////////////////////////////////////////////////////////////////

    public void addUser(ActionEvent event) {
        try {
            if (TF_username.getText() == "" || TF_password.getText() == "")
                throw new Exception("Missing info");

            int id = Integer.parseInt(TF_username.getText());
            String password = TF_password.getText();

            Driver.addEmployeeUser(id, password);
            updateTable();
            TF_username.setText("");
            TF_password.setText("");

        } catch (Exception e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public void updateUser(ActionEvent event) {
        try {
            index = table.getSelectionModel().getSelectedIndex();
            String newPassword = TF_tblPassword.getText();

            if (index == -1)
                throw new Exception("choose user!");
            if (newPassword == "")
                throw new Exception("enter new password");

            int id = CLM_username.getCellData(index);


            Driver.updateEmployeeUser(id, newPassword);
            updateTable();
            TF_tblPassword.setText("");


        } catch (Exception e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    public void deleteUser(ActionEvent event) {
        try {
            index = table.getSelectionModel().getSelectedIndex();

            if (index == -1)
                throw new Exception("choose user!");

            int id = CLM_username.getCellData(index);

            Driver.deleteEmployeeUser(id);
            updateTable();
            TF_tblPassword.setText("");


        } catch (Exception e) {
//			e.printStackTrace();
            showErrorMessage(e.getMessage());
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////

    public void onlyInteger(KeyEvent event) {
        TextField TF = (TextField) event.getSource();
        TF.setText(TF.getText().replaceAll("[^\\d]", ""));
        TF.positionCaret(TF.getText().length());
    }

    public void goBack(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenuSample.fxml"));

            Scene scene = new Scene(root, 600, 700);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            Main.stage.setScene(scene);


        } catch (IOException e) {
//				e.printStackTrace();
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
