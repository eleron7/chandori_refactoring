package chandori.server.service;

import chandori.server.entity.MemberInfo.Member;
import chandori.server.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterMemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Autowired
    public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository){
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }

    public Long join(String email, String password, String name, String nickname, String job, Long income) {
        Member user = Member.createMember(email, password, name, nickname, job, income, passwordEncoder);
        validateDuplicateMember(user);
        memberRepository.save(user);

        return user.getId();
    }

    public void validateDuplicateMember(Member user) {
        memberRepository.findByEmail(user.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
