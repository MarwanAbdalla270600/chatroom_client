package fhtw.chatroom_client;

import com.fasterxml.jackson.databind.ObjectMapper;
import fhtw.chatroom_client.socketMessage.SocketMessage;
import fhtw.chatroom_client.user.User;

import java.io.IOException;
import java.io.Serializable;

import static fhtw.chatroom_client.MainApplication.in;
import static fhtw.chatroom_client.MainApplication.out;

public class CommunicationService implements Serializable {
    public static boolean register(String username, String password, Character gender) {

        String response;
        try {
            SocketMessage socketMessage = new SocketMessage<>("register", new User(username, password, gender));
            String json = socketMessage.toJson();
            out.writeObject(json);
            response = (String)in.readObject();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return response.equalsIgnoreCase("register");
    }
}
