package fhtw.chatroom_client;

import fhtw.chatroom_client.chat.PrivateChat;
import fhtw.chatroom_client.message.PrivateChatMessage;
import fhtw.chatroom_client.user.Profile;
import fhtw.chatroom_client.user.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.logging.Logger;

import java.io.*;
import java.net.Socket;

public class MainApplication extends Application {
    private static final Logger logger = Logger.getLogger(MainApplication.class.getName());


    static String serverAddress = "20.23.1.111";
    private static Stage primaryStage;  // Store the primary stage

    public static Socket socket;
    public static ObjectOutputStream out;

    public static ObjectInputStream in;

    public static Profile profile;
    public static Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
        try {
            CommunicationService.initData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }));



    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;  // Store the primary stage
        Platform.runLater(() -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("onboarding.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 700, 500);
                stage.setTitle("Onboarding");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void openMainStage() throws IOException {
        // Close the current stage
        primaryStage.close();

        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Parent root = loader.load();

        // Create a new stage and set its properties
        Stage newStage = new Stage();
        newStage.setTitle("MVP Chat");
        newStage.setScene(new Scene(root));
        newStage.show();
    }



    public static void main(String[] args) {
        Thread socketThread = new Thread(() -> {
            try {
                socket = new Socket(serverAddress, 80);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                // Now you can read from 'in' or perform other socket-related operations

            } catch (IOException e) {
                logger.severe("Connection failed: " + e.getMessage());
                e.printStackTrace(); // Handle the exception appropriately
            }
        });
        socketThread.start();

        try {
            socketThread.join(); // Wait for the socketThread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /*pollingThread.start();*/

        launch();
    }
}