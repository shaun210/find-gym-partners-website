package backend.findAGymBro.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.LinkedList;
import java.util.List;

@Entity
public class Member {
    @Id
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    @ManyToMany
    private List<Member> friends;
    private String personalDescription;

    @Enumerated(EnumType.STRING)
    private GymLevel gymLevel;
    private int age;
    private int yearsOfExperience;
    private String facebookLink;
    private String instagramLink;
    private String snapchatLink;
    private String tiktokLink;
    private String addressTown;
    private String addressCountry;


    public Member() {
        this.friends = new LinkedList<>();
    }

    // use all private variables in constructor
    public Member(String username, String password, String email, String firstName, String lastName, String personalDescription, GymLevel gymLevel, 
    int age, int yearsOfExperience, String facebookLink, String instagramLink, String snapchatLink, String tiktokLink, String addressTown, String addressCountry) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalDescription = personalDescription;
        this.gymLevel = gymLevel;
        this.age = age;
        this.yearsOfExperience = yearsOfExperience;
        this.friends = new LinkedList<>();
        this.facebookLink = facebookLink;
        this.instagramLink = instagramLink;
        this.snapchatLink = snapchatLink;
        this.tiktokLink = tiktokLink;
        this.addressTown = addressTown;
        this.addressCountry = addressCountry;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public List<Member> getFriends() {
        return friends;
    }

    public String getPersonalDescription() {
        return personalDescription;
    }

    public GymLevel getGymLevel() {
        return gymLevel;
    }

    public int getAge() {
        return age;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
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

    public String getAddressTown() {
        return addressTown;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addFriend(Member friend) {
        this.friends.add(friend);
    }

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    public void setGymLevel(GymLevel gymLevel) {
        this.gymLevel = gymLevel;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
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

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }
    
}

