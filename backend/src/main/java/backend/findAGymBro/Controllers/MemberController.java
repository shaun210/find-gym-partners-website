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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

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
                                @RequestParam(value = "addressCountry") String addressCountry,
                                @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture
                               ) {
        try {
            System.out.println("Received file: " + profilePicture.getOriginalFilename());

            GymLevel enumGymLevel = GymLevel.valueOf(gymLevel.toUpperCase());
            return new MemberDto(memberService.createMember(username, password, email, firstName, lastName, personalDescription, enumGymLevel, age, 
                yearsOfExperience, facebookLink, instagramLink, snapchatLink, tiktokLink, addressTown, addressCountry, profilePicture));
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
            return memberService.findPeopleByGymLevelAndAddress(enumGymLevel, addressTown).stream().map(member -> new MemberDto(member)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("error in finding people by gym level and address");
        } 
    }
}
