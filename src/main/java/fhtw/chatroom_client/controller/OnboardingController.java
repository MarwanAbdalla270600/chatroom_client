package fhtw.chatroom_client.controller;

import fhtw.chatroom_client.CommunicationService;
import fhtw.chatroom_client.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OnboardingController {

    @FXML
    public TextField username;

    @FXML
    public TextField password;


    @FXML
    public void register() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (CommunicationService.register(username.getText(), password.getText(), 'm')) {
            alert.setContentText("Registrer successfull");
        } else {
            alert.setContentText("Register failed");
        }
        alert.show();
    }

    @FXML
    public void login() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (CommunicationService.login(username.getText(), password.getText(), 'm')) {
            alert.setContentText("Login successfull");
            //open new view
            Stage primaryStage = new Stage();

            MainApplication.openMainStage();
        } else {
            alert.setContentText("Login failed");
        }
        alert.show();
    }



}