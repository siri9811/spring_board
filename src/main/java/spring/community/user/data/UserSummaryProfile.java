package spring.community.user.data;

import lombok.Data;
import spring.community.user.User;

@Data
public class UserSummaryProfile {
    private String email;
    private String username;
    private String profileImageUrl;

    public static UserSummaryProfile from(User user) {
        UserSummaryProfile userSummaryProfile = new UserSummaryProfile();
        userSummaryProfile.setEmail(user.getEmail());
        userSummaryProfile.setUsername(user.getUsername());
        userSummaryProfile.setProfileImageUrl("https://picsum.photos/id/20/300/300");
        return userSummaryProfile;
    }
}
