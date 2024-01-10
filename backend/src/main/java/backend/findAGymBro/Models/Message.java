package backend.findAGymBro.Models;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    private String message;
    @ManyToOne
    private Chat chat;
    private String sender;
    private String receiver;
    private Timestamp date;

    public Message() {
    }

    public Message(String message, Chat chat, Timestamp date, String sender, String receiver) {
        this.message = message;
        this.chat = chat;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public Chat getChat() {
        return chat;
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


    public void setMessage(String message) {
        this.message = message;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void setMember1(String member1) {
        this.sender = sender;
    }

    public void setMember2(String receiver) {
        this.receiver = receiver;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
