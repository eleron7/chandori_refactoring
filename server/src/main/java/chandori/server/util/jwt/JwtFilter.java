package chandori.server.util.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;
    private final RedisTemplate redisTemplate;

    //토큰 추출과정
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);//HTTP 요청에서 Authorization 헤더를 가져옴
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {//Authorization 헤더가 존재하고, 그 값이 Bearer 로 시작하는지 확인
            return bearerToken.substring(7);//Bearer 이후의 부분이 실제 JWT 토큰이므로 해당 부분을 반환
        }
        return null;//조건을 만족하지 않는다면 null
    }

    //Jwt 필터 로직
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        String jwt = resolveToken(request);//토큰 추출
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {//토큰 겅증
            String isLogout = (String)redisTemplate.opsForValue().get(jwt);
            if(ObjectUtils.isEmpty(isLogout)) {//가져온 값이 비어있다면 로그아웃 처리된 토큰이 아님
                Authentication authentication = tokenProvider.getAuthentication(jwt);//인증정보 생성
                SecurityContextHolder.getContext().setAuthentication(authentication);//인증정보 설정
            }
        }
        filterChain.doFilter(request, response);//다음 필터로 전달
    }
}
