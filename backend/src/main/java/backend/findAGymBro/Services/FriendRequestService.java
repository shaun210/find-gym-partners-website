package backend.findAGymBro.Services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;

import backend.findAGymBro.Models.FriendRequest;
import backend.findAGymBro.Models.Member;
import backend.findAGymBro.Controllers.CustomException;
import backend.findAGymBro.DAO.FriendRequestRepository;
import backend.findAGymBro.DAO.MemberRepository;
import backend.findAGymBro.DTO.FriendRequestDto;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FriendRequestService {
    
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Transactional
    public String createFriendRequest(String sender, String receiver, boolean accepted, LocalDateTime sentDateTime) {

        // check if sender and receiver are different
        if (sender.equals(receiver)) {
            throw new IllegalArgumentException("Sender and receiver cannot be the same");
        }
        // find sender and receiver
        Member senderMember = memberRepository.findByUsername(sender);
        if (senderMember == null) {
            throw new IllegalArgumentException("Sender does not exist");
        }
        Member receiverMember = memberRepository.findByUsername(receiver);
        if (receiverMember == null) {
            throw new IllegalArgumentException("Receiver does not exist");
        }
        // check if friend request already exists
        if (friendRequestRepository.findBySenderAndReceiver(senderMember, receiverMember) != null || friendRequestRepository.findBySenderAndReceiver(receiverMember, senderMember) != null){
            throw new CustomException("Friend Request already exists", HttpStatus.BAD_REQUEST);
        }

        // check if friends
        if (memberRepository.areFriends(sender, receiver)) {
            throw new CustomException("Member already is a friend", HttpStatus.BAD_REQUEST);
        }
        FriendRequest friendRequest = new FriendRequest(senderMember, receiverMember, accepted, sentDateTime);
        friendRequestRepository.save(friendRequest);
        return "Friend request successfully created";
    }

    @Transactional
    public boolean setAcceptedStatus(String sender, String receiver, boolean accepted) {
        FriendRequest friendRequest = getFriendRequest(sender, receiver);
        if (friendRequest == null) {
            throw new IllegalArgumentException("Friend request does not exist");
        }

        if (accepted) {
            memberService.addFriend(sender, receiver);
        }
        friendRequestRepository.delete(friendRequest);
        return true;
    }

    @Transactional
    public List<FriendRequest> getAllFriendRequests(String username) {
        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            throw new IllegalArgumentException("Member does not exist");
        }
        return friendRequestRepository.findAllByReceiverAndAcceptedOrderBySentDateTime(member, false);
    }

    public FriendRequest getFriendRequest(String sender, String receiver) {
        Member senderMember = memberRepository.findByUsername(sender);
        Member receiverMember = memberRepository.findByUsername(receiver);
        if (senderMember == null || receiverMember == null) {
            throw new IllegalArgumentException("Sender or receiver does not exist");
        }
        return friendRequestRepository.findBySenderAndReceiver(senderMember, receiverMember);
    }

    public FriendRequestDto convertFriendRequestDto(FriendRequest friendRequest) {
        FriendRequestDto friendRequestDto = new FriendRequestDto(friendRequest.getSender().getUsername(), friendRequest.getReceiver().getUsername(), friendRequest.getAccepted(), friendRequest.getSentDateTime().toString());
        return friendRequestDto;
    }

    public boolean checkIfFriends(String username1, String username2) {
        return memberRepository.areFriends(username1, username2);
    }
}