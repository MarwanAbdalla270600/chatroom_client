package fhtw.chatroom_client;

import fhtw.chatroom_client.chat.PrivateChat;
import fhtw.chatroom_client.message.PrivateChatMessage;
import fhtw.chatroom_client.user.Profile;
import fhtw.chatroom_client.user.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class MainApplication extends Application {
    public static Socket socket;
    public static ObjectOutputStream out;

    public static ObjectInputStream in;

    public static Profile profile = new Profile("marwan", 'm', "1234");


    @Override
    public void start(Stage stage) throws IOException {
        initialize();
        Platform.runLater(() -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("onboarding.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 700, 700);
                stage.setTitle("Onboarding");
                stage.setTitle("onboarding");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void openMainStage() throws IOException {
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


    public static void initialize() {
        User thomas = new User("thomas", "abcd123", 'm');
        User manuel = new User("manuel", "abcd123", 'm');
        User merkel = new User("merkel", "abcd123", 'f');
        User anna = new User("anna","abcd123",  'f');
        User frank = new User("frank", "abcd123", 'm');
        User max = new User("maxine", "abcd123", 'f');
        User moritz = new User("moritz", "abcd123", 'm');
        User niko = new User("niko", "abcd123", 'm');
        User favour = new User("nikolette","abcd123",  'f');

        PrivateChat a = new PrivateChat(thomas);
        PrivateChat b = new PrivateChat(manuel);
        PrivateChat c = new PrivateChat(merkel);
        PrivateChat d = new PrivateChat(anna);
        PrivateChat e = new PrivateChat(frank);
        PrivateChat f = new PrivateChat(max);
        PrivateChat g = new PrivateChat(moritz);
        PrivateChat h = new PrivateChat(niko);
        PrivateChat i = new PrivateChat(favour);

        a.addMessage(new PrivateChatMessage("hallo", false));
        a.addMessage(new PrivateChatMessage("wie", false));
        a.addMessage(new PrivateChatMessage("geht", false));
        a.addMessage(new PrivateChatMessage("es", false));


        profile.addPrivateChat(a);
        profile.addPrivateChat(b);
        profile.addPrivateChat(c);
        profile.addPrivateChat(d);
        profile.addPrivateChat(e);
        profile.addPrivateChat(f);
        profile.addPrivateChat(g);
        profile.addPrivateChat(h);
        profile.addPrivateChat(i);

    }
}