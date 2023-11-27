package chandori.server.config;

import chandori.server.service.CustomUserDetailsService;
import chandori.server.util.jwt.JwtAccessDeniedHandler;
import chandori.server.util.jwt.JwtAuthenticationEntryPoint;
import chandori.server.util.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Component
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final RedisTemplate redisTemplate;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(); // CustomUserDetailsService는 사용자 정보를 제공하는 클래스입니다. 아래에 구현되어 있어야 합니다.
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()//*CORS 활성화

                .and()
                .httpBasic().disable()//*HTTP 기본 인증 비활성화
                .csrf().disable()//*CSRF 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//*세션 관리정책 무상태(JWT)

                .and()
                .exceptionHandling()//*예외처리
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)//1. 인증실패시 처리하는 클래스는?
                .accessDeniedHandler(jwtAccessDeniedHandler)//2. 권한이 없을 때 처리하는 클래스는?

                .and()
                .authorizeHttpRequests()//*요청에대한 권한 설정
                .requestMatchers("/**").permitAll()// "/**" 경로 모든 권한 허용
                .anyRequest().authenticated()//모든 요청은 인증된 사용자만 권한 허용

                .and()
                .apply(new JwtSecurityConfig(tokenProvider, redisTemplate));//Jwt 보안구성 설정

        return http.build();
    }

}