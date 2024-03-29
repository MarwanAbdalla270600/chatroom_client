package fhtw.chatroom_client.controller;

import fhtw.chatroom_client.CommunicationService;
import fhtw.chatroom_client.MainApplication;
import fhtw.chatroom_client.user.Profile;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    public ToggleGroup gender;

    public boolean validateRegister() {
        Toggle selectedToggle = gender.getSelectedToggle();
        String radio = ((ToggleButton) selectedToggle).getText();
        return (radio != null && !registerUsername.getText().isEmpty() && !registerPassword.getText().isEmpty());
    }

    @FXML
    public void register(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            if (!validateRegister()) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Data invalid\n Pls fill the form correctly");
                alert.show();
                return;
            }
        } catch (Exception e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Data invalid\nPls fill the form correctly");
            alert.show();
            return;
        }
        Toggle selectedToggle = gender.getSelectedToggle();
        Character gender = ((ToggleButton) selectedToggle).getText().equals("Female") ? 'f' : 'm';

        if (CommunicationService.register(registerUsername.getText(), registerPassword.getText(), gender)) {
            alert.setContentText("Register successfully");
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Register failed");
        }
        registerUsername.clear();
        registerPassword.clear();
        alert.show();
    }

    @FXML
    public void login() throws IOException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (CommunicationService.login(loginUsername.getText(), loginPassword.getText(), 'm')) {
            MainApplication.profile = new Profile(loginUsername.getText(), loginPassword.getText());
            MainApplication.fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            MainApplication.fiveSecondsWonder.play();
            MainApplication.openMainStage();
            CommunicationService.initData();
        } else {
            alert.setContentText("Login failed");
            alert.show();
        }
        loginUsername.clear();
        loginPassword.clear();
    }


}