package chandori.server.controller;

import chandori.server.dto.MemberJoinDto;
import chandori.server.service.RegisterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    RegisterMemberService registerMemberService;

    @Autowired
    public AuthorizationController(RegisterMemberService registerMemberService){
        this.registerMemberService = registerMemberService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join (@RequestBody MemberJoinDto dto) {
        try {
            registerMemberService.join(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getNickname(), dto.getJob(), dto.getIncome());
            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
