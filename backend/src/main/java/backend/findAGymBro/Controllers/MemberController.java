package backend.findAGymBro.Controllers;
import backend.findAGymBro.DTO.MemberDto;
import backend.findAGymBro.Models.GymLevel;
import backend.findAGymBro.Models.Member;
import backend.findAGymBro.Services.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3050", "http://find-gym-partner-env.eba-8tda8qu8.us-east-1.elasticbeanstalk.com"})
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    // @PostMapping(value = {"/", ""})
    // public MemberDto createMember(@RequestParam(value = "username") String username,
    //                            @RequestParam(value = "password") String password,
    //                            @RequestParam(value = "email") String email,
    //                            @RequestParam(value = "firstName") String firstName,
    //                            @RequestParam(value = "lastName") String lastName,
    //                            @RequestParam(value = "personalDescription") String personalDescription,
    //                            @RequestParam(value = "gymLevel") String gymLevel,
    //                            @RequestParam(value = "age") int age,
    //                            @RequestParam(value = "yearsOfExperience") int yearsOfExperience,
    //                             @RequestParam(value = "facebookLink") String facebookLink,
    //                             @RequestParam(value = "instagramLink") String instagramLink,
    //                             @RequestParam(value = "snapchatLink") String snapchatLink,
    //                             @RequestParam(value = "tiktokLink") String tiktokLink,
    //                             @RequestParam(value = "addressTown") String addressTown,
    //                             @RequestParam(value = "addressCountry") String addressCountry,
    //                             @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture
    //                            ) {
    //     try {

    //         GymLevel enumGymLevel = GymLevel.valueOf(gymLevel.toUpperCase());
    //         return new MemberDto(memberService.createMember(username, password, email, firstName, lastName, personalDescription, enumGymLevel, age, 
    //             yearsOfExperience, facebookLink, instagramLink, snapchatLink, tiktokLink, addressTown, addressCountry, profilePicture));
    //     } catch (Exception e) {
    //         throw new IllegalArgumentException("error in creating member: " +e.getMessage());
    //     } 
    // }

    @PostMapping(value = {"/", ""})
    public MemberDto createMember(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "firstName") String firstName,
                               @RequestParam(value = "lastName") String lastName,
                               @RequestParam(value = "personalDescription") String personalDescription,
                               @RequestParam(value = "gymLevel") String gymLevel,
                               @RequestParam(value = "age") int age,
                               @RequestParam(value = "yearsOfExperience") int yearsOfExperience,
                                @RequestParam(value = "facebookLink") String facebookLink,
                                @RequestParam(value = "instagramLink") String instagramLink,
                                @RequestParam(value = "snapchatLink") String snapchatLink,
                                @RequestParam(value = "tiktokLink") String tiktokLink,
                                @RequestParam(value = "addressTown") String addressTown,
                                @RequestParam(value = "addressCountry") String addressCountry
                               ) {
        try {
            GymLevel enumGymLevel = GymLevel.valueOf(gymLevel.toUpperCase());
            return new MemberDto(memberService.createMember(username, password, email, firstName, lastName, personalDescription, enumGymLevel, age, 
                yearsOfExperience, facebookLink, instagramLink, snapchatLink, tiktokLink, addressTown, addressCountry));
        } catch (Exception e) {
            throw new IllegalArgumentException("error in creating member: " +e.getMessage());
        } 
    }
    @PostMapping(value = {"/login", "/login/"})
    public MemberDto login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        return new MemberDto(memberService.login(username, password));
    }

    @RequestMapping(value = "/friendList", method = RequestMethod.GET)
    public List<MemberDto> getFriends(@RequestParam(value = "username") String username) {
        return memberService.getFriends(username).stream().map(member -> new MemberDto(member)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public boolean addFriend(@RequestParam(value = "username") String username,
                            @RequestParam(value = "friendUsername") String friendUsername) {
        return memberService.addFriend(username, friendUsername);
    }

    // look for people controller, all 3
    @RequestMapping(value = "/findPeopleByUsername", method = RequestMethod.GET)
    public MemberDto findPeopleByUsername(@RequestParam(value = "username") String username) {
        return new MemberDto(memberService.findPeopleByUsername(username));
    }

    @RequestMapping(value = "/findPeopleByGymLevelAndAddress", method = RequestMethod.GET)
    public List<MemberDto> findPeopleByGymLevelAndAddress(@RequestParam(value = "gymLevel") String gymLevel,
                                                        @RequestParam(value = "addressTown") String addressTown) {
        try {
            GymLevel enumGymLevel = GymLevel.valueOf(gymLevel.toUpperCase());
            List<Member> foundMembers = memberService.findPeopleByGymLevelAndAddress(enumGymLevel, addressTown);
            return foundMembers.stream().map(m -> new MemberDto(m)).collect(Collectors.toList());
            // return foundMembers.stream().map(m -> {
            //     try {
            //         MemberDto mm =  memberService.convMemberDtoWithPicture(m);
            //         return mm;
            //     } catch (IOException e) {
            //         e.printStackTrace();
            //         return null;
            //     }
            // }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error in finding people by gym level and address:" + e.getMessage());
        }
    }

    // @GetMapping(value = {"/profilePicture", "/profilePicture/"})
    // public byte[] getProfilePic(@RequestParam(value = "username") String username) {
    //     try {
    //         return memberService.getProfilePicture(username);
    //     } catch (Exception e) {
    //         throw new IllegalArgumentException("error in getting profile picture" + e.getMessage());
    //     } 
    // }

    // write a dummy controller that returns hello world
    @GetMapping(value = {"/hello", "/hello/"})
    public String hello() {
        return "Hello World";
    }
}
