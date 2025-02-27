package spring.community.jwt

import spring.community.user.UserRole
import java.time.Instant

data class AuthenticatedUser (
    val userId: String,
    val roles: Set<UserRole>,
    val issuedAt: Instant,
    val expiration: Instant,
)