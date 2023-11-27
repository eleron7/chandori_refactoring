package chandori.server.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 여기에서 사용자를 데이터베이스에서 찾거나 다른 소스에서 가져오는 로직을 구현합니다.
        // 이 예제에서는 하드코딩된 방식으로 사용자를 생성합니다.
        if ("userId".equals(userId)) {
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username("userId")
                    .password("password") // 패스워드는 인코딩되어야 하지만 간단한 예제를 위해 인코딩하지 않음
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId);
        }
    }
}