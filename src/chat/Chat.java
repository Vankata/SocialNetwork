package chat;

import java.util.Set;
import java.util.TreeSet;

import chat.exceptions.ChatBoxException;
import chat.exceptions.ChatException;
import user.User;

public class Chat {

	private String nameOfCreator;
	private User friend;
	private Set<Message> massages = new TreeSet<Message>();
	
	public Chat(User friend, String nameOfCreator) throws ChatException, ChatBoxException {
		//proverkata dali tozi user e v priqtelite na choveka 
		//pravim kogato izvikvame sendMessage na usera
		if(friend != null && nameOfCreator != null && nameOfCreator.trim().length() > 0){
			this.friend = friend;
			this.friend.getChatBox().addNewChat(nameOfCreator, this);
		}else{
			throw new ChatException("Invalid friend! ");
		}
	}

	public void sendMessage(String meassage) {
		
	}

}
