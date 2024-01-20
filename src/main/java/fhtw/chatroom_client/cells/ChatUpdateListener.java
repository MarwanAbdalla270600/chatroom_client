package fhtw.chatroom_client.cells;

import fhtw.chatroom_client.chat.PrivateChat;

import java.util.List;

public interface ChatUpdateListener {
    void onChatListUpdated(List<PrivateChat> chats);
}
