package com.example.comp333_con;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {

    @FXML
    private Label showUserNameLabel;
    public void connectButton(ActionEvent event){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery= "select connectivity from stat";

        try{
           Statement statement=connectDB.createStatement();
            ResultSet queryOutput=statement.executeQuery(connectQuery);

            while(queryOutput.next()){
                showUserNameLabel.setText(queryOutput.getString("connectivity"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}