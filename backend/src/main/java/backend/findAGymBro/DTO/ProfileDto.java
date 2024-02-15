package backend.findAGymBro.DTO;

import backend.findAGymBro.Models.Member;

public class ProfileDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String personalDescription;
    private String gymLevel;
    private String addressTown;
    private String addressProvince;
    private String addressCountry;
    private String gender;
    private int age;
    private String facebookLink;
    private String instagramLink;
    private String snapchatLink;
    private String tiktokLink;


    public ProfileDto(String username, String email, String firstName, String lastName, String personalDescription, String gymLevel, 
        String addressTown, String addressProvince, String addressCountry, String gender, int age, String facebookLink, String instagramLink, String snapchatLink, String tiktokLink) {
            this.username = username;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.personalDescription = personalDescription;
            this.gymLevel = gymLevel;
            this.addressTown = addressTown;
            this.addressProvince = addressProvince;
            this.addressCountry = addressCountry;
            this.gender = gender;
            this.age = age;
            this.facebookLink = facebookLink;
            this.instagramLink = instagramLink;
            this.snapchatLink = snapchatLink;
            this.tiktokLink = tiktokLink;
    }

    public ProfileDto(Member Member) {
        this.username = Member.getUsername();
        this.email = Member.getEmail();
        this.firstName = Member.getFirstName();
        this.lastName = Member.getLastName();
        this.personalDescription = Member.getPersonalDescription();
        this.gymLevel = Member.getGymLevel().toString();
        this.addressTown = Member.getAddressTown();
        this.addressProvince = Member.getAddressProvince();
        this.addressCountry = Member.getAddressCountry();
        this.gender = Member.getGender().toString();
        this.age = Member.getAge();
        this.facebookLink = Member.getFacebookLink();
        this.instagramLink = Member.getInstagramLink();
        this.snapchatLink = Member.getSnapchatLink();
        this.tiktokLink = Member.getTiktokLink();
        
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

    public String getPersonalDescription() {
        return personalDescription;
    }

    public String getGymLevel() {
        return gymLevel;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public String getSnapchatLink() {
        return snapchatLink;
    }

    public String getTiktokLink() {
        return tiktokLink;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    public void setGymLevel(String gymLevel) {
        this.gymLevel = gymLevel;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public void setGender(String gender) {
        this.gender =  gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public void setSnapchatLink(String snapchatLink) {
        this.snapchatLink = snapchatLink;
    }

    public void setTiktokLink(String tiktokLink) {
        this.tiktokLink = tiktokLink;
    }
}
