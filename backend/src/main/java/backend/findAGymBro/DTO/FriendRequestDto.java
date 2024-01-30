package backend.findAGymBro.DTO;


public class FriendRequestDto {
    private int id;
    private String sender;
    private String receiver;
    private boolean accepted;
    private String sentDateTime;
    
    public FriendRequestDto(String sender, String receiver, boolean accepted, String sentDateTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.accepted = accepted;
        this.sentDateTime = sentDateTime;
    }

    public FriendRequestDto() {
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getSentDateTime() {
        return sentDateTime;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    
    public int getId() {
        return id;
    }    
}
