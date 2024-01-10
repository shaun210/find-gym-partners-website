// write a chat dto for me:

package backend.findAGymBro.DTO;

import backend.findAGymBro.Models.Chat;

public class ChatDto {
    private int chatId;
    private String member1Username;
    private String member2Username;

    public ChatDto() {
    }

    public ChatDto(Chat chat) {
        this.chatId = chat.getChatId();
        this.member1Username = chat.getMember1().getUsername();
        this.member2Username = chat.getMember2().getUsername();
    }

    public int getChatId() {
        return chatId;
    }

    public String getMember1Username() {
        return member1Username;
    }

    public String getMember2Username() {
        return member2Username;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setMember1Username(String member1Username) {
        this.member1Username = member1Username;
    }

    public void setMember2Username(String member2Username) {
        this.member2Username = member2Username;
    }
}