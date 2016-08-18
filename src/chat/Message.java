package chat;

import java.time.LocalDateTime;

import chat.exceptions.MessageException;

public class Message {
	
	private LocalDateTime timeOfTheMessage;
	private String message;
	
	public Message(String message) throws MessageException {
		
		if(message != null && message.trim().length() > 0){
			this.message = message;
		}else{
			throw new MessageException("Invalid message! ");
		}
		
		this.timeOfTheMessage = LocalDateTime.now();
	}
}
