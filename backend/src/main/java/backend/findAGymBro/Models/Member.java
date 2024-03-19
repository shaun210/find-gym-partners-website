package backend.findAGymBro.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

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
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    private int yearsOfExperience;
    private String facebookLink;
    private String instagramLink;
    private String snapchatLink;
    private String tiktokLink;
    private String addressTown;
    private String addressProvince;
    private String addressCountry;

    @OneToMany
    private List<Exercise> exercises;

    @OneToMany
    private List<Workout> workouts;
    
    @OneToMany(mappedBy = "sender")
    private List<FriendRequest> sentFriendRequests;

    @OneToMany(mappedBy = "receiver")
    private List<FriendRequest> receivedFriendRequests;



    public Member() {
        this.friends = new LinkedList<>();
    }

    // use all private variables in constructor
    public Member(String username, String password, String email, String firstName, String lastName, String personalDescription, GymLevel gymLevel, 
    int age, int yearsOfExperience, String facebookLink, String instagramLink, String snapchatLink, String tiktokLink, String addressTown, String addressProvince, String addressCountry, Gender gender) {
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
        this.sentFriendRequests = new LinkedList<>();
        this.receivedFriendRequests = new LinkedList<>();
        this.gender = gender;
        this.addressProvince = addressProvince;
        this.exercises = new LinkedList<>();
        this.workouts = new LinkedList<>();
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

    public String getAddressProvince() {
        return addressProvince;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public List<FriendRequest> getSentFriendRequests() {
        return sentFriendRequests;
    }

    public List<FriendRequest> getReceivedFriendRequests() {
        return receivedFriendRequests;
    }

    public Gender getGender() {
        return gender;
    }

    public List<Exercise> getActualExercises() {
        return exercises;
    }

    public List<Workout> getWorkouts() {
        return workouts;
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

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public void setActualExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
}

