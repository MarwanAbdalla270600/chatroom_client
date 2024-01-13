package fhtw.chatroom_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fhtw.chatroom_client.chat.Chat;
import fhtw.chatroom_client.chat.PrivateChat;
import fhtw.chatroom_client.user.User;

import java.io.*;
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

    public static boolean login(String username, String password, Character gender) {
        boolean response;
        try {
            User user = new User(username, password, gender);
            String json = "login;" + user.toJson();
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

    public static void initData() throws IOException, ClassNotFoundException {
        try {
            String json = "getChats;" + profile.toJson();  //brauchts den request ueberhaupt?
            out.writeObject(json);
            Set<PrivateChat> chats = (Set<PrivateChat>) in.readObject();
            // TODO:
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMessage() {
        System.out.println("Send data to Server...");
    }

    public static boolean addFriend(String username) throws IOException {
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
