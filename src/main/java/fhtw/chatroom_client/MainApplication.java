package fhtw.chatroom_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class MainApplication extends Application {
    public static Socket socket;
    public static ObjectOutputStream out;

    public static ObjectInputStream in;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("onboarding.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Thread socketThread = new Thread(() -> {
            try {
                socket = new Socket("localhost", 12345);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                // Now you can read from 'in' or perform other socket-related operations

            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        });
        socketThread.start();

        try {
            socketThread.join(); // Wait for the socketThread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        launch();
    }
}