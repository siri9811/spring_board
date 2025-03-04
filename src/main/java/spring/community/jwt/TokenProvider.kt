package spring.community.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders.BASE64
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import spring.community.exception.DomainException.InvalidTokenException
import spring.community.user.UserRole
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey


@Component
class TokenProvider(
    private val jwtProperties: JwtProperties
) {
    /**
     * JWT 키
     */
    private val key: SecretKey = Keys
        .hmacShaKeyFor(BASE64.decode(jwtProperties.secretKey))

    fun createToken(
        email: String,
        roles: Set<UserRole>,
        expire: Long = jwtProperties.accessTokenExpiration,
    ): String {
        return Jwts.builder()
            .claim("email", email)
            .claim("roles", roles)
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plusMillis(expire * 1000)))
            .signWith(key)
            .compact()
    }

    /**
     * JWT 토큰에서 정보 추출
     */
    fun decodeToken(token: String): AuthenticatedUser {
        try {

            val claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload

            // 나머지 코드는 동일
            return AuthenticatedUser(
                email = claims["email", String::class.java]
                    ?: throw InvalidTokenException("Email claim is missing"),
                roles = claims["roles", Collection::class.java]?.map { UserRole.valueOf(it as String) }
                    ?.toSet()
                    ?: throw InvalidTokenException("Roles claim is missing"),
                issuedAt = claims.issuedAt.toInstant(),
                expiration = claims.expiration.toInstant()
            )
        } catch (e: Exception) {
            println("Token decode error: ${e.message}")
            throw InvalidTokenException("Invalid token: ${e.message}")
        }
    }
}
