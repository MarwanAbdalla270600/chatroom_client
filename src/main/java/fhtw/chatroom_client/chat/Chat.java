package fhtw.chatroom_client.chat;

import fhtw.chatroom_client.message.PrivateChatMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Chat {

    protected LocalDateTime date;
    protected ObservableList<PrivateChatMessage> messages = FXCollections.observableArrayList();


    public Chat() {
        this.date = LocalDateTime.now();
    }
}