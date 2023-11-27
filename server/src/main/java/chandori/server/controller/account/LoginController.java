package chandori.server.controller.account;

import chandori.server.dto.LoginDto;
import chandori.server.service.LoginService;
import chandori.server.util.NormalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/login")
@RestController
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController (LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("")
    public ResponseEntity<?> Login(@RequestBody LoginDto dto){
        return NormalResponse.toResponseEntity(HttpStatus.OK, loginService.login(dto));
    }
}
