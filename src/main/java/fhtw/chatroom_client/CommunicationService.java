package fhtw.chatroom_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fhtw.chatroom_client.user.User;

import java.io.*;

import static fhtw.chatroom_client.MainApplication.in;
import static fhtw.chatroom_client.MainApplication.out;

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

    }

    public static void sendMessage() {
        System.out.println("Send data to Server...");
    }

    public static boolean addFriend(String username) throws IOException {
       return true;
    }


}
