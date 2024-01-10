package backend.findAGymBro.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;
    @ManyToOne
    private Member member1;
    @ManyToOne
    private Member member2;
    public Chat() {
    }

    public Chat(Member member1, Member member2) {
        this.member1 = member1;
        this.member2 = member2;
    }

    public int getChatId() {
        return chatId;
    }

    public Member getMember1() {
        return member1;
    }

    public Member getMember2() {
        return member2;
    }

    public void setMember1(Member member1) {
        this.member1 = member1;
    }

    public void setMember2(Member member2) {
        this.member2 = member2;
    }
    
}
