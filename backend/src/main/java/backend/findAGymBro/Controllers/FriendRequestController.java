package backend.findAGymBro.Controllers;

import org.springframework.web.bind.annotation.RestController;

import backend.findAGymBro.Services.FriendRequestService;
import backend.findAGymBro.Services.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import backend.findAGymBro.DTO.FriendRequestDto;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3050", "http://find-gym-partner-env.eba-8tda8qu8.us-east-1.elasticbeanstalk.com"})
@RestController
@RequestMapping("/friendRequest")
public class FriendRequestController {
    
    @Autowired
    private FriendRequestService friendRequestService;

    @Autowired
    private MemberService memberService;

    @PostMapping(value = {"/", ""})
    public String createFriendRequest(@RequestParam(value = "sender") String sender,
                                @RequestParam(value = "receiver") String receiver
                                ) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return friendRequestService.createFriendRequest(sender, receiver, false, localDateTime);
    }
    @PutMapping(value = {"/changeAcceptedStatus", "/changeAcceptedStatus/"})
    public String changeAcceptedStatus(@RequestParam(value = "sender") String sender,
                                        @RequestParam(value = "receiver") String receiver, 
                                        @RequestParam(value = "accepted") boolean accepted) {
        try { 
            return "Friend request successfully updated to: " + friendRequestService.setAcceptedStatus(sender, receiver, accepted); 
        } catch (Exception e) {
            throw new IllegalArgumentException("error in accepting friend request:" + e.getMessage());
        } 
    }
    // working
    @GetMapping(value = {"/getAllFriendRequests", "/getAllFriendRequests/"})
    public Iterable<FriendRequestDto> getAllFriendRequests(@RequestParam(value = "username") String username) {
        try {
            return friendRequestService.getAllFriendRequests(username).stream().map(friendRequest -> friendRequestService.convertFriendRequestDto(friendRequest)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("error in getting all friend requests:" + e.getMessage());
        } 
    }

    @PutMapping(value = {"/deleteFriendRequest", "/deleteFriendRequest/"})
    public String deleteFriendRequest(@RequestParam(value = "sender") String sender,
                                        @RequestParam(value = "receiver") String receiver) {
        try {
            memberService.removeFriend(sender, receiver);
            return "Friend request successfully deleted";
        } catch (Exception e) {
            throw new IllegalArgumentException("error in deleting friend request:" + e.getMessage());
        } 
    }
    
}
