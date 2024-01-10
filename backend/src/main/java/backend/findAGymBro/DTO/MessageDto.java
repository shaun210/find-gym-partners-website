package backend.findAGymBro.DTO;

// create dto for message
import java.sql.Timestamp;

import backend.findAGymBro.Models.Message;

public class MessageDto {
    private int messageId;
    private String message;
    private int chatId;
    private String sender;
    private String receiver;
    private Timestamp date;

    public MessageDto() {
    }

    public MessageDto(Message message) {
        this.messageId = message.getMessageId();
        this.message = message.getMessage();
        this.chatId = message.getChat().getChatId();
        this.sender = message.getSender();
        this.receiver = message.getReceiver();
        this.date = message.getDate();
    }

    public int getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public int getChatId() {
        return chatId;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
