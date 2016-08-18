package chat;

import java.util.Set;
import java.util.TreeSet;

import chat.exceptions.ChatBoxException;
import chat.exceptions.ChatException;
import user.User;

public class Chat {

	private String nameOfCreator;
	private User friend;
<<<<<<< HEAD
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
>>>>>>> 0d96b3b75c679fba6970ef2535d5b5c4d5d159d9

	public void sendMessage(String meassage) {
		
	}

}
