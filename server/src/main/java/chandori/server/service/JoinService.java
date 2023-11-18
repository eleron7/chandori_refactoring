package chandori.server.service;

import chandori.server.dto.JoinDto;
import chandori.server.entity.user.UserAccount;
import chandori.server.repository.userRepository;
import chandori.util.AccountInfoPolicy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final PasswordEncoder passwordEncoder;
    private final userRepository memberRepository;

    @Autowired
    public JoinService(PasswordEncoder passwordEncoder, userRepository memberRepository){
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }

    public void join(JoinDto dto) {
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(dto, userAccount);
        String userId = userAccount.getUserId();
        String password = userAccount.getPassword();

        //아이디 패스워드 포맷 확인
        AccountInfoPolicy.checkUserIdFormat(userId);
        AccountInfoPolicy.checkPasswordFormat(password);

        //패스워드 암호화
        userAccount.setPassword(passwordEncoder.encode(password));
        userAccount.setRoles("USER");

        //아이디 중복 체크
        validateDuplicateUserId(userId);

        memberRepository.save(userAccount);
    }

    public void validateDuplicateUserId(String userId) {
        memberRepository.findByUserId(userId)
                .ifPresent(m -> {
                    throw new RuntimeException("이미 존재하는 아이디 입니다");
                });
    }
}
