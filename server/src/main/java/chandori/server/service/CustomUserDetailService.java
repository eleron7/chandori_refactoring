package chandori.server.service;

import chandori.server.entity.user.UserAccount;
import chandori.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserId(username)
                .map(this::createUserDetails)
                .orElseThrow(()-> new UsernameNotFoundException("해당하는 사용자를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(UserAccount userAccount) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(String.valueOf(userAccount.getRoles()));
        return new User(
                userAccount.getUserId(),
                userAccount.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
