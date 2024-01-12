package fhtw.chatroom_client.socketMessage;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class SocketMessage<T> {
    private final String method;

    private final T Object;

    public SocketMessage(String method, T object) {
        this.method = method;
        Object = object;
    }

    public SocketMessage fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, SocketMessage.class);
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
