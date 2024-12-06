package com.makersacademy.acebook.dto;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;

import java.util.List;

public class LikesHandler {
    private UserRepository userRepository;
    private String currentUser;

    public LikesHandler(UserRepository userRepository, String currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public LikesHandler() {}

    public UserRepository getUserRepository() { return userRepository; }
    public String getCurrentUser() { return currentUser; }

    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }
    public void setCurrentUser(String currentUser) { this.currentUser = currentUser; }

    public List<User> getUsersWhoLiked(Long postId) {
        return userRepository.findUsersWhoLikedPost(postId);
    }

    public String getLikedString(Long id, String liked, boolean isComment) {
        StringBuilder likedString = new StringBuilder();
        List<User> users = getUsersWhoLiked(id);

        int count = 0;
        int total = users.size();

        // If currentUser likes this post or comment, 'You' goes at the front of the string.
        if (liked != null) {
            count++;
            likedString
                    .append("You")
                    .append(getJoinString(total - count));
        }

        for (User user : users) {
            if (user.getAuth0Id().equals(currentUser)) continue; // skip the currentUser
            count++;
            if (count == 2 && total > 3) {
                likedString
                        .append(user.getNickname())
                        .append(" and ")
                        .append(total - 2)
                        .append(" others ");
                break;
            }
            likedString
                    .append(user.getNickname())
                    .append(getJoinString(total - count));
        }

        likedString.append("reacted to this");
        likedString.append(isComment ? " comment" : " post");
        return total > 0 ? likedString.toString() : "";
    }

    private String getJoinString(int itemsRemaining) {
        return itemsRemaining > 1 ? ", " : itemsRemaining == 1 ? " and " : " ";
    }
}
