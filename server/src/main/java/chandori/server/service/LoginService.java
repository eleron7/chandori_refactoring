package chandori.server.service;

import chandori.server.config.SecurityUtil;
import chandori.server.dto.LoginDto;
import chandori.server.entity.user.UserAccount;
import chandori.server.repository.UserRepository;
import chandori.server.util.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;

    public Map<String, Object> login(LoginDto dto) {
        UserAccount findAccount = userRepository.findByUserId(dto.getUserId()).get();
        if (passwordEncoder.matches(dto.getPassword(), findAccount.getPassword()) == false) {
            throw new NullPointerException("아이디 혹은 비밀번호가 틀렸습니다");
        }

        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(dto, userAccount);
        userAccount.setRoles(dto.isManager() ? "MANAGER" : "USER");
        UsernamePasswordAuthenticationToken authToken = userAccount.toAuthentication();
        Authentication auth = authenticationManagerBuilder.getObject()//authenticationManager 가져옴
                .authenticate(authToken);//authenticationManager와 토큰을 바탕으로 사용자 검증 및 인증
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("role", userAccount.getRoles());
        loginInfo.put("token", tokenProvider.generateTokenDto(auth));
        return loginInfo;
    }

    public boolean logout(RequestEntity<?> httpMessage){
        String userId = SecurityUtil.getCurrentUserId();
        System.out.println("123");
        System.out.println(httpMessage.getHeaders().get("Authorization"));
        String accessToken = httpMessage.getHeaders().get("Authorization").get(0).substring(7);
        Long expiration = tokenProvider.getExpiration(accessToken);
        redisTemplate.opsForValue().set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);

        return true;
    }
}
