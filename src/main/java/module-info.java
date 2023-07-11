module com.example.comp333_con {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
                            
    opens FirstPack to javafx.fxml;
    exports FirstPack;

    opens Login to javafx.fxml, javafx.controls;
    exports Login;

    opens MainMenu to javafx.fxml, javafx.controls;
    exports MainMenu;

    opens Manage to javafx.fxml, javafx.controls;
    exports Manage;

    opens Orders to javafx.fxml, javafx.controls;
    exports Orders;

    opens OrdersHistory to javafx.fxml, javafx.controls;
    exports OrdersHistory;

    opens ManageUsers to javafx.fxml, javafx.controls;
    exports ManageUsers;

    opens Statistics to javafx.fxml, javafx.controls;
    exports Statistics;

}