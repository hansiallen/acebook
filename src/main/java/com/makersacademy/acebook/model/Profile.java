package com.makersacademy.acebook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PROFILE_PAGES")
public class Profile {
    @Id
    private Long userId;
    private String firstName;
    private String lastName;
    private String profilePictureUrl;
    private String bio;
    private String location;
    private String websiteUrl;
    private boolean isPublic;

    public Profile(Long userId, String firstName, String lastName, String profilePictureUrl, String bio, String location, String websiteUrl, boolean isPublic) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePictureUrl = profilePictureUrl;
        this.bio = bio;
        this.location = location;
        this.websiteUrl = websiteUrl;
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
    public String getProfilePictureUrl() {return this.profilePictureUrl;}
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl;}
    public String getBio() {return this.bio;}
    public void setBio(String bio) {this.bio = bio;}
    public String getLocation() {return this.location;}
    public void setLocation(String location) {this.location = location;}
    public String getWebsiteUrl() {return this.websiteUrl;}
    public void setWebsiteUrl(String websiteUrl) {this.websiteUrl = websiteUrl;}
    public boolean getIsPublic() {return this.isPublic;}
    public void setIsPublic(boolean isPublic) {this.isPublic = isPublic;}
}
