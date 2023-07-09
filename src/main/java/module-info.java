module com.example.comp333_con {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.comp333_con to javafx.fxml;
    exports com.example.comp333_con;
}