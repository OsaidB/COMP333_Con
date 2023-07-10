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
}