package FirstPack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Login/loginSample.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Login/loginSample.fxml"));

        URL resource = Main.class.getResource("/Login/loginSample.fxml");
//        FXMLLoader.load(getClass().getResource("/Login/loginSample.fxml"));

        if (resource == null) {
            System.out.println("Resource is null");
        } else {
            System.out.println("Resource found!!");
        }


//        Parent root = FXMLLoader.load(getClass().getResource("/Login/loginSample.fxml"));

//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.setTitle("Butcher Shop Management System");

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}