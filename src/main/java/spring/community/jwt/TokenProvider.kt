package spring.community.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import javax.crypto.SecretKey
import io.jsonwebtoken.io.Decoders.BASE64
import org.springframework.stereotype.Component
import spring.community.exception.DomainException.InvalidTokenException
import spring.community.user.UserRole
import java.time.Instant
import java.util.Date


@Component
class TokenProvider(
    private val jwtProperties: JwtProperties)
{
    /**
     * JWT 키
     */
    private val key: SecretKey = Keys
        .hmacShaKeyFor(BASE64.decode(jwtProperties.secretKey))

    fun createToken(
        email: String,
        roles: Set<UserRole>,
        expire: Long = jwtProperties.accessTokenExpiration
    ): String {
        return Jwts.builder()
            .claim("email", email)
            .claim("roles", roles)
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plusMillis(expire * 1000)))
            .signWith(key)
            .compact()
    }

    fun decodeToken(token: String): AuthenticatedUser {
        try {
            val claims: Claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload

            return AuthenticatedUser(
                userId = claims.subject,
                roles = claims["roles", Collection::class.java]!!.map { UserRole.valueOf(it as String) }
                    .toSet(),
                issuedAt = claims.issuedAt.toInstant(),
                expiration = claims.expiration.toInstant()
            )
        } catch (e: Exception) {
            throw InvalidTokenException("Invalid token")
        }
    }

}