package backend.findAGymBro;


public class HelloMessage {

  private String receiver;
  private String sender;
  private String message;
  private String chatID;

  public HelloMessage() {
  }

  public HelloMessage(String receiver, String sender, String message, String chatID) {
    this.receiver = receiver;
    this.sender = sender;
    this.message = message;
    this.chatID = chatID;
  }

  public String getReceiver() {
    return receiver;
  }

  public String getSender() {
    return sender;
  }

  public String getMessage() {
    return message;
  }

  public String getChatID() {
    return chatID;
  }
}