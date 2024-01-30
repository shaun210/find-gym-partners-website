package backend.findAGymBro.Controllers;

import org.springframework.web.bind.annotation.RestController;

import backend.findAGymBro.Services.FriendRequestService;

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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/friendRequest")
public class FriendRequestController {
    
    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping(value = {"/", ""})
    public String createFriendRequest(@RequestParam(value = "sender") String sender,
                                @RequestParam(value = "receiver") String receiver
                                ) {
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            friendRequestService.createFriendRequest(sender, receiver, false, localDateTime);
            return "Friend request successfully created at " + localDateTime; 
        } catch (Exception e) {
            throw new IllegalArgumentException("error in creating friend request" + e.getMessage());
        } 
    }
    @PutMapping(value = {"/changeAcceptedStatus", "/changeAcceptedStatus/"})
    public String changeAcceptedStatus(@RequestParam(value = "sender") String sender,
                                        @RequestParam(value = "receiver") String receiver, 
                                        @RequestParam(value = "accepted") boolean accepted) {
        try { 
            return "Friend request successfully updated to: " + friendRequestService.setAcceptedStatus(sender, receiver, accepted); 
        } catch (Exception e) {
            throw new IllegalArgumentException("error in accepting friend request" + e.getMessage());
        } 
    }
    // working
    @GetMapping(value = {"/getAllFriendRequests", "/getAllFriendRequests/"})
    public Iterable<FriendRequestDto> getAllFriendRequests(@RequestParam(value = "username") String username) {
        try {
            return friendRequestService.getAllFriendRequests(username).stream().map(friendRequest -> friendRequestService.convertFriendRequestDto(friendRequest)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("error in getting all friend requests" + e.getMessage());
        } 
    }
    
}
