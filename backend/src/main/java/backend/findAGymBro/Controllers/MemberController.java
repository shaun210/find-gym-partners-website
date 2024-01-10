package backend.findAGymBro.Controllers;
import backend.findAGymBro.DTO.MemberDto;
import backend.findAGymBro.Models.GymLevel;
import backend.findAGymBro.Models.Member;
import backend.findAGymBro.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping(value = {"/createMember", "/createMember/"})
    public MemberDto createMember(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "firstName") String firstName,
                               @RequestParam(value = "lastName") String lastName,
                               @RequestParam(value = "personalDescription") String personalDescription,
                               @RequestParam(value = "gymLevel") int gymLevelInt,
                               @RequestParam(value = "age") int age,
                               @RequestParam(value = "yearsOfExperience") int yearsOfExperience,
                                @RequestParam(value = "facebookLink") String facebookLink,
                                @RequestParam(value = "instagramLink") String instagramLink,
                                @RequestParam(value = "snapchatLink") String snapchatLink,
                                @RequestParam(value = "tiktokLink") String tiktokLink
                               ) {
        // gym level
        GymLevel gymLevel;
        switch (gymLevelInt) {
            case 0:
                gymLevel = GymLevel.BEGINNER;
                break;
            case 1:
                gymLevel = GymLevel.INTERMEDIATE;
                break;
            case 2:
                gymLevel = GymLevel.ADVANCED;
                break;
            default:
                throw new IllegalArgumentException("Invalid gym level");
        }
        return new MemberDto(memberService.createMember(username, password, email, firstName, lastName, personalDescription, gymLevel, age, yearsOfExperience, facebookLink, instagramLink, snapchatLink, tiktokLink));
    }
    @PostMapping(value = {"/login", "/login/"})
    public MemberDto login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        return new MemberDto(memberService.login(username, password));
    }

    @RequestMapping(value = "/getMember", method = RequestMethod.GET)
    public MemberDto getMember(@RequestParam(value = "username") String username) {
        return new MemberDto(memberService.getMember(username));
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

}
