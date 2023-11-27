package chandori.server;

import chandori.server.dto.LoginDto;
import chandori.server.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginTest {

    @Autowired
    private LoginService loginService;

    @Test
    void login() {
        String user_id = "hong1234";
        String password = "@Hong1234";
        boolean isManager = false;

        LoginDto loginDto = LoginDto.builder()
                .userId(user_id)
                .password(password)
                .isManager(isManager)
                .build();

        loginService.login(loginDto);
    }
}

