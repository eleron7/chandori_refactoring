package chandori.server.controller.account;

import chandori.server.dto.JoinDto;
import chandori.server.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/join")
@RestController
public class JoinController {
    private final JoinService joinService;

    @Autowired
    JoinController(JoinService joinService){
        this.joinService = joinService;
    }

    @PostMapping("")
    public ResponseEntity<?> join(@RequestBody JoinDto dto){
        joinService.join(dto);
        return ResponseEntity.ok().build();
    }
}
