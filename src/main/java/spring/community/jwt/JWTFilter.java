package spring.community.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import spring.community.user.CustomUserDetails;
import spring.community.user.User;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

  private final JWTUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authorization = request.getHeader("Authorization");
    //Authorization 헤더 검증
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      System.out.println("token null");
      filterChain.doFilter(request, response);
      return;
    }
    System.out.println("autorization now");
    String token = authorization.split(" ")[1];
    //토큰 유효시간 검증
    if (jwtUtil.isExpired(token)) {
      System.out.println("token expired");
      filterChain.doFilter(request, response);
      return;
    }
    String username = jwtUtil.getUsername(token);
    String role = jwtUtil.getRole(token);

    User user = new User();
    user.setUsername(username);
    user.setPassword("temppassword");
    user.setRole(role);

    CustomUserDetails customUserDetails = new CustomUserDetails(user);

    Authentication authtoken = new UsernamePasswordAuthenticationToken(
        customUserDetails, null, customUserDetails.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authtoken);

    filterChain.doFilter(request, response);
  }
}
