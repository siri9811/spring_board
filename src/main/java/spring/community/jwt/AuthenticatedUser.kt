package spring.community.jwt

import spring.community.user.User
import spring.community.user.UserRole
import java.time.Instant

/**
 * Jwt Payload에 해당하는 객체
 */
data class AuthenticatedUser(
    val email: String,
    val roles: Set<UserRole>,
    val issuedAt: Instant,
    val expiration: Instant,
)
