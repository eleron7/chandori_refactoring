package chandori.server.controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/join")
    public String joinPage() { return "join"; }

    @GetMapping("/dashboard")
    public String dashboardPage(@AuthenticationPrincipal User user, Model model) {//오버라이드한 loadUserByUsername로 부터 유저 세션정보를 받아옴
        model.addAttribute("userNickname", user.getUsername());//사용자의 이름 정보를 반환
        model.addAttribute("loginRoles", user.getAuthorities());//사용자의 권한 정보를 반환
        return "dashboard";
    }

    @GetMapping("/setting/admin")
    public String adminSettingPage(){
        return "admin_setting";
    }

    @GetMapping("/setting/user")
    public String userSettingPage(){
        return "user_setting";
    }
}
