package chat;

import java.util.HashMap;
import java.util.Map;

import chat.exceptions.ChatBoxException;

public class ChatBox {

	private Map<String, Chat> chats = new HashMap<String, Chat>();
	
	public void addNewChat(String name, Chat newChat) throws ChatBoxException{
		
		if(name != null && name.trim().length() > 0 &&
				newChat != null && !this.chats.containsValue(newChat)){
			this.chats.put(name, newChat);
		}else{
			throw new ChatBoxException("Invalid chat! ");
		}
	}
}
