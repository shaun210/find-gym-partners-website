package backend.findAGymBro.Models;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; 

@Entity
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "sender_username", nullable = false)
    private Member sender;

    @ManyToOne
    @JoinColumn(name = "receiver_username", nullable = false)
    private Member receiver;
    boolean accepted;
    LocalDateTime sentDateTime;
    
    public FriendRequest(Member sender, Member receiver, boolean accepted, LocalDateTime sentDateTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.accepted = accepted;
        this.sentDateTime = sentDateTime;
    }

    public FriendRequest() {
    }

    public Member getSender() {
        return sender;
    }

    public Member getReceiver() {
        return receiver;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    
    public int getId() {
        return id;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }

    public void setReceiver(Member receiver) {
        this.receiver = receiver;
    }

    public void setSentDateTime(LocalDateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public boolean getAccepted() {
        return accepted;
    }

    @Override
    public String toString() {
        return "FriendRequest [accepted=" + accepted + ", id=" + id + ", receiver=" + receiver + ", sender=" + sender
                + ", sentDateTime=" + sentDateTime + "]";
    }
}