package fhtw.chatroom_client;

import com.fasterxml.jackson.databind.ObjectMapper;
import fhtw.chatroom_client.chat.PrivateChat;
import fhtw.chatroom_client.controller.MainController;
import fhtw.chatroom_client.message.PrivateChatMessage;
import fhtw.chatroom_client.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static fhtw.chatroom_client.MainApplication.*;

public class CommunicationService implements Serializable {
    public static boolean register(String username, String password, Character gender) {
        boolean response;
        try {
            User user = new User(username, password, gender);
            String json = "register;" + user.toJson();
            out.writeObject(json);
            response =  (boolean)in.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        return response;
    }

    public static boolean login(String username, String password, Character gender) throws IOException, ClassNotFoundException {
        boolean response;
        try {
            User user = new User(username, password, gender);
            String json = "login;" + user.toJson();
            out.writeObject(json);
            response =  (boolean)in.readObject();
            System.out.println(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //ystem.out.println("fehler");
        }
       //
        return true;
    }

    public static void initData() throws IOException, ClassNotFoundException {
        try {
            String json = "initData;";
            out.writeObject(json);
            String response = (String) in.readObject();
            System.out.println(response);
            List<PrivateChat> privateChats = PrivateChat.fromJsonToList(response);
            MainApplication.profile.setPrivateChats(privateChats);

            System.out.println(privateChats);


           // profile.setPrivateChats(observablePrivateChats);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public static void sendMessage() {
        System.out.println("Send data to Server...");
    }

    public static boolean addFriend(String username) throws IOException, ClassNotFoundException {

       boolean response;
        try {
            String json =  "addFriend;" + username;
            out.writeObject(json);
            response =  (boolean)in.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        return response;
    }


}
