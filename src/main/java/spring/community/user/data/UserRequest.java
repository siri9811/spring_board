package spring.community.user.data;

import spring.community.user.User;

public record UserRequest(
        String username,
        String password
) {
    public static UserRequest createUserDto(User user) {
        return new UserRequest(
                user.getUsername(),
                user.getPassword()
        );
    }
}

// 역할이 다른게, record로 입력받는건 매우 좋다
// record는 불변이다. 그래서 setter가 없다.
// DTO가 불변이 되어도 좋다. 근데, 변경되어야 하는 경우가 있다면, record를 사용하지 않는다.
// Request DTO, Response DTO
