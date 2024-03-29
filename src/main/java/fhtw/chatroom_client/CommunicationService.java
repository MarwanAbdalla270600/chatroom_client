package fhtw.chatroom_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import fhtw.chatroom_client.chat.PrivateChat;

import fhtw.chatroom_client.controller.MainController;
import fhtw.chatroom_client.message.PrivateChatMessage;
import fhtw.chatroom_client.user.User;


import java.io.*;
import java.util.List;

import static fhtw.chatroom_client.MainApplication.*;

public class CommunicationService implements Serializable {
    public static boolean register(String username, String password, Character gender) {
        boolean response;
        try {
            User user = new User(username, password, gender);
            String json = "register;" + user.toJson();
            out.writeObject(json);
            response =  (boolean)in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response);
        return response;
    }

    public static boolean login(String username, String password, Character gender) {
        boolean response;
        try {
            User user = new User(username, password, gender);
            String json = "login;" + user.toJson();
            out.writeObject(json);
            response =  (boolean)in.readObject();
            System.out.println(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response);
        return response;
    }

    public static void sendMessage(String messageText, PrivateChat chat) throws IOException {

        PrivateChatMessage newMessage = new PrivateChatMessage(profile.getUsername(), messageText, chat.getChatId());
        String json = "sendMessage;" + newMessage.toJson();
        out.writeObject(json);

    }


    public static void initData() throws IOException, ClassNotFoundException {
        try {
            String json = "initData;";
            out.writeObject(json);
            String response = (String) in.readObject();
            //System.out.println(response); //for testing
            List<PrivateChat> privateChats = PrivateChat.fromJsonToList(response);
            MainApplication.profile.setPrivateChats(privateChats);
            //System.out.println(privateChats);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static boolean addFriend(String username) {

       boolean response;
        try {
            String json =  "addFriend;" + username;
            out.writeObject(json);
            response =  (boolean)in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        return response;
    }


}
