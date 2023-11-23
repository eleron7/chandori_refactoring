package chandori.server.controller.account;

import chandori.server.dto.LoginDto;
import chandori.server.entity.user.UserAccount;
import chandori.server.service.LoginService;
import chandori.server.util.NormalResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/login")
@RestController
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController (LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("")
    public ResponseEntity<?> Login(@RequestBody LoginDto dto){
        return NormalResponse.toResponseEntity(HttpStatus.OK, loginService.login(dto));
    }
}
