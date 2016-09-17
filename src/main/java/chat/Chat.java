package chat;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import chat.exceptions.ChatBoxException;
import chat.exceptions.ChatException;
import chat.exceptions.MessageException;
import user.User;

public class Chat {

	private LocalDateTime timeOfLastMessage;
	private List<Message> messages = new LinkedList<Message>();
	private int id;
	
	public Chat() {
		this.timeOfLastMessage = LocalDateTime.now();
	}


	public Message addMessage(String text) throws ChatException, MessageException {
		if(text != null && text.trim().length() > 0){
			Message newMessage = new Message(text);
			this.messages.add(newMessage);
			this.timeOfLastMessage = newMessage.getTimeOfTheMessage();
			return newMessage;
		}else{
			throw new ChatException("Invalid message! ");
		}
	}

	public void printChat(){
		for (Message message : messages) {
			System.out.println(message);
		}
	}

	public LocalDateTime getTimeOfLastMessage() {
		return timeOfLastMessage;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
