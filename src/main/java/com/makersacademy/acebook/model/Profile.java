package com.makersacademy.acebook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PROFILE_PAGES")
public class Profile {
    @Id
    private Long userId;
    private String firstName;
    private String lastName;
    private String profilePictureURL;
    private String bio;
    private String location;
    private String websiteURL;
    private boolean isPublic;

    public Profile(Long userId, String firstName, String lastName, String profilePictureURL, String bio, String location, String websiteURL, boolean isPublic) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePictureURL = profilePictureURL;
        this.bio = bio;
        this.location = location;
        this.websiteURL = websiteURL;
        this.isPublic = isPublic;
    }

    public Profile() {

    }

    public Long  getUserId() {return this.userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public String getFirstName() {return this.firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getProfilePictureURL() {return this.profilePictureURL;}
    public void setProfilePictureURL(String profilePictureURL) { this.profilePictureURL = profilePictureURL;}
    public String getBio() {return this.bio;}
    public void setBio(String bio) {this.bio = bio;}
    public String getLocation() {return this.location;}
    public void setLocation(String location) {this.location = location;}
    public String getWebsiteURL() {return this.websiteURL;}
    public void setWebsiteURL(String websiteURL) {this.websiteURL = websiteURL;}
    public boolean getIsPublic() {return this.isPublic;}
    public void setIsPublic(boolean isPublic) {this.isPublic = isPublic;}
}
