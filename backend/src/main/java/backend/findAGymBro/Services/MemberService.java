package backend.findAGymBro.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.findAGymBro.Models.Member;
import backend.findAGymBro.DAO.MemberRepository;
import backend.findAGymBro.Models.GymLevel;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Member createMember(String username, String password, String email, String firstName, 
    String lastName, String personalDescription, GymLevel gymLevel, int age, int yearsOfExperience, String facebookLink, String instagramLink, String snapchatLink, String tiktokLink) {

        // check if username unique
        if (memberRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        // check if email valid
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        Member member = new Member(username, password, email, firstName, lastName, personalDescription, gymLevel, age, yearsOfExperience, facebookLink, instagramLink, snapchatLink, tiktokLink);
        memberRepository.save(member);
        return member;
    }

    @Transactional
    public Member login(String username, String password) {
        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            throw new IllegalArgumentException("Member does not exist");
        }
        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password");
        }
        return member;
    }

    @Transactional
    public Member getMember(String username) {
        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            throw new IllegalArgumentException("Member does not exist");
        }
        return member;
    }

    @Transactional
    public List<Member> getFriends(String username) {
        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            throw new IllegalArgumentException("Member does not exist");
        }
        return member.getFriends();
    }

    @Transactional
    public Boolean addFriend(String username, String friendUsername) {
        Member member = memberRepository.findByUsername(username);
        Member friend = memberRepository.findByUsername(friendUsername);
        if (member == null || friend == null) {
            throw new IllegalArgumentException("Member does not exist");
        }
        if (member.getFriends().contains(friend) || friend.getFriends().contains(member)) {
            throw new IllegalArgumentException("Friend already exists");
        }
        if (member.equals(friend)) {
            throw new IllegalArgumentException("Cannot add self as friend");
        }

        member.getFriends().add(friend);
        friend.getFriends().add(member);
        memberRepository.save(member);
        memberRepository.save(friend);
        return true;
    }
}