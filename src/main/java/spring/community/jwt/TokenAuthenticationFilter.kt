package spring.community.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class TokenAuthenticationFilter(
    private val tokenProvider: TokenProvider,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token: String? = request.getHeader("Authorization")?.substringAfter("Bearer ")

        if (!token.isNullOrBlank()) {
            val authentication: AuthenticatedUser = tokenProvider.decodeToken(token)

            // 인증된 정보 SecurityContext에 저장
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                authentication,
                token,
                authentication.roles
            )
        }
        filterChain.doFilter(request, response)
    }
}
