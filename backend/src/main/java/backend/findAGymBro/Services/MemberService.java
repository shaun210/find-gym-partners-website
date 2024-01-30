package backend.findAGymBro.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import backend.findAGymBro.Models.Member;
import jakarta.persistence.EntityNotFoundException;
import backend.findAGymBro.DAO.MemberRepository;
import backend.findAGymBro.Models.GymLevel;

import java.io.IOException;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ChatService chatService;

    @Transactional
    public Member createMember(String username, String password, String email, String firstName, 
    String lastName, String personalDescription, GymLevel gymLevel, int age, int yearsOfExperience, String facebookLink, String instagramLink, String snapchatLink, String tiktokLink, 
    String addressTown, String addressCountry, MultipartFile profilePicFile) {

        // check if username unique
        if (memberRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        // check if email valid
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (profilePicFile != null && !profilePicFile.getContentType().equals("image/jpeg")) {
            throw new IllegalArgumentException("Invalid file type");
        }
        byte[] pictureBytes = null;
        try {
            // Handle the IOException here
            pictureBytes = profilePicFile.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("Error processing profile picture file", e);
        }

        Member member = new Member(username, password, email, firstName, lastName, personalDescription, gymLevel, age, yearsOfExperience, facebookLink, instagramLink, snapchatLink, tiktokLink, addressTown, addressCountry, pictureBytes);
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

        // there is a dependency on chat service
        // workaround: make two calls from frontend, one to create chat and one to add friend?
        chatService.createChat(username, friendUsername);
        return true;
    }

    @Transactional
    public Member findPeopleByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Invalid argument: username cannot be null");
        }
        Member foundMember = memberRepository.findByUsername(username);
        if (foundMember == null) {
            throw new EntityNotFoundException("Member not found with username: " + username);
        }
        return foundMember;
    }

    // @Transactional
    // public List<Member> findPeopleByAddress(String addressTown, String addressCountry) {
    //     if (addressTown == null || addressCountry == null) {
    //         throw new IllegalArgumentException("Invalid arguments: addressTown and addressCountry cannot be null");
    //     }
    //     List<Member> foundMembers = memberRepository.findByAddressTownAndAddressCountry(addressTown, addressCountry);
    //     if (foundMembers.isEmpty()) {
    //         throw new EntityNotFoundException("Members not found with address: " + addressTown + ", " + addressCountry);
    //     }
    //     return foundMembers;
    // }

    @Transactional
    public List<Member> findPeopleByGymLevelAndAddress(GymLevel gymLevel, String addressTown) {
        if (gymLevel == null) {
            throw new IllegalArgumentException("Invalid argument: gymLevel cannot be null");
        }
        List<Member> foundMembers = memberRepository.findByGymLevelAndAddressTown(gymLevel, addressTown);
        if (foundMembers.isEmpty()) {
            throw new EntityNotFoundException("Members not found with gym level: " + gymLevel +
                    " and address: ");
        }
        return foundMembers;
    }

}