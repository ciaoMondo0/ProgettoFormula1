package it.unicam.cs.mp.formula1.view;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class JavaFXMain extends  Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Race.fxml")));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Formula1");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
