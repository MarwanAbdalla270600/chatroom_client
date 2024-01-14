package fhtw.chatroom_client.controller;

import fhtw.chatroom_client.CommunicationService;
import fhtw.chatroom_client.MainApplication;
import fhtw.chatroom_client.user.Profile;
import javafx.animation.Timeline;
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
    public TextField registerUsername;

    @FXML
    public TextField registerPassword;

    @FXML
    public TextField loginUsername;
    @FXML
    public TextField loginPassword;


    @FXML
    public void register() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (CommunicationService.register(registerUsername.getText(), registerPassword.getText(), 'm')) {
            alert.setContentText("Registrer successfull");
        } else {
            alert.setContentText("Register failed");
        }
        alert.show();
    }

    @FXML
    public void login() throws IOException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (CommunicationService.login(loginUsername.getText(), loginPassword.getText(), 'm')) {
            alert.setContentText("Login successfull");
            MainApplication.profile = new Profile(loginUsername.getText(), loginPassword.getText(), 'm');
            CommunicationService.initData();
            MainApplication.fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            MainApplication.fiveSecondsWonder.play();
            Stage primaryStage = new Stage();

            MainApplication.openMainStage();
        } else {
            alert.setContentText("Login failed");
        }
        alert.show();
    }



}