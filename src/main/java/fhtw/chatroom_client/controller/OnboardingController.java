package fhtw.chatroom_client.controller;

import fhtw.chatroom_client.CommunicationService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class OnboardingController {

    @FXML
    public TextField username;

    @FXML
    public TextField password;


    @FXML
    public void register() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (CommunicationService.register(username.getText(), "asdf123", 'm')) {
            alert.setContentText("Registrer successfull");
        } else {
            alert.setContentText("Register failed");
        }
        alert.show();
    }
}