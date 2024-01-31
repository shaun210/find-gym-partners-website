package backend.findAGymBro.DTO;

import backend.findAGymBro.Models.Member;

public class MemberDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private byte[] profilePicture;

    public MemberDto() {
    }

    public MemberDto(Member member) {
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
    }

    public MemberDto(Member member, byte[] profilePic) {
        this(member);
        this.profilePicture = profilePic;
    }

    public String getUsername() {
        return username;
    }
    
    public String getEmail(){
        return email;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }
}
