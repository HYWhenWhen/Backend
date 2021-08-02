package WhenWhenBackEnd.service;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Boolean save(Member member) {
        Member findMember = memberRepository.findByIdToken(member.getIdToken());

        if (findMember == null) {
            memberRepository.save(member);
            return true;
        }

        return false;
    }

    public Member logIn(String idToken) {
        Member findMember = memberRepository.findByIdToken(idToken);

        return findMember;
    }

    public Member findOne(String idToken) {
        Member findMember = memberRepository.findByIdToken(idToken);

        return findMember;
    }

}
