package Statistics;

import FirstPack.Driver;
import FirstPack.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class StatisticsController implements Initializable {

    @FXML
    private Pane soldPane;
    @FXML
    private DatePicker TF_start;
    @FXML
    private DatePicker TF_end;
    @FXML
    private Label LBL_sales;
    @FXML
    private Label LBL_profit;
    @FXML
    private Pane allPane;

    ObservableList<PieChart.Data> soldList;
    ObservableList<PieChart.Data> allList;

    public void getProfitAndSales(ActionEvent event) {

        try {
            LocalDate startDate = TF_start.getValue();
            LocalDate endDate = TF_end.getValue();

            if (startDate.compareTo(endDate) >= 0)
                throw new Exception("Start date should be before end date!");

            double two[] = Driver.getProfitAndSales(startDate.toString(), endDate.toString());
            LBL_profit.setText("Total Profit: " + two[1]);
            LBL_sales.setText("Total Sales: " + two[0]);

        } catch (Exception e) {
//			e.printStackTrace();
            showErrorMesaage(e.getMessage());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            soldList = Driver.getPieChartSoldData();

            PieChart Soldchart = new PieChart(soldList);
            Soldchart.setTitle("Sold Items - Types Distribution");
            Soldchart.setStartAngle(180);
            Soldchart.setLegendVisible(false);
            soldPane.getChildren().add(Soldchart);

            allList = Driver.getPieChartAllData();

            PieChart Allchart = new PieChart(allList);
            Allchart.setTitle("All Items - Types Distribution");
            Allchart.setStartAngle(180);
            Allchart.setLegendVisible(false);
            allPane.getChildren().add(Allchart);

        } catch (SQLException e) {
//			e.printStackTrace();
            showErrorMesaage(e.getMessage());
        }
    }

    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenuSample.fxml"));
            Scene scene = new Scene(root, 600, 700);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            Main.stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
            Alert ALRT_databaseError = new Alert(AlertType.ERROR);
            ALRT_databaseError.setTitle("Error!!");
            ALRT_databaseError.setHeaderText("Error Message: " + e.getMessage());
            ALRT_databaseError.setContentText(null);
            ALRT_databaseError.show();
        }
    }

    private void showErrorMesaage(String str) {
        Alert ALRT_databaseError = new Alert(AlertType.ERROR);
        ALRT_databaseError.setTitle("Error!!");
        ALRT_databaseError.setHeaderText("Error Message: " + str);
        ALRT_databaseError.setContentText(null);
        ALRT_databaseError.show();
    }
}
