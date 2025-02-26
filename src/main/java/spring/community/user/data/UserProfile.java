package spring.community.user.data;

import lombok.Data;
import spring.community.user.User;


@Data
public class UserProfile {
    private String email;
    private String username;
    private String profileImageUrl;
    private String bio;
    private String website;

    public static UserProfile from(User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(user.getEmail());
        userProfile.setUsername(user.getUsername());
        userProfile.setProfileImageUrl("https://picsum.photos/id/20/300/300");
        userProfile.setBio("세상은 나의 것");
        userProfile.setWebsite("https://www.naver.com");
        return userProfile;
    }
}
