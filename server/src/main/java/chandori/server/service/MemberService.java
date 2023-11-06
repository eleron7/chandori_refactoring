package chandori.server.service;

import chandori.server.entity.MemberInfo.Member;
import chandori.server.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findOne(String email) {
        return memberRepository.findByEmail(email);
    }

}
