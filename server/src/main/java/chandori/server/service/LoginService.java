package chandori.server.service;

import chandori.server.dto.LoginDto;
import chandori.server.entity.user.UserAccount;
import chandori.server.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    //토큰 프로바이더 만들어야 함

    @Autowired
    public LoginService (UserRepository userRepository, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userRepository = userRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    public Map<String, Object> login(LoginDto dto) {
        if (dto.getUserId() == null || dto.getUserId().isBlank()) {
            throw new RuntimeException("아이디를 입력해주세요");
        }

        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new RuntimeException("비밀번호를 입력해주세요");
        }



        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(dto, userAccount);
        userAccount.setRoles(dto.isManager() ? "MANAGER" : "USER");
        UsernamePasswordAuthenticationToken authToken = userAccount.usernamePasswordAuthenticationToken();
        Authentication auth = authenticationManagerBuilder.getObject()
                .authenticate(authToken);

        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("role", "");

        return loginInfo;
    }
}
