package WhenWhenBackEnd.api;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.dto.member.CreateMemberRequestDTO;
import WhenWhenBackEnd.dto.member.CreateMemberResponseDTO;
import WhenWhenBackEnd.dto.member.LogInMemberRequestDTO;
import WhenWhenBackEnd.dto.member.LogInMemberResponseDTO;
import WhenWhenBackEnd.repository.MemberRepository;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {

    private final MemberRepository memberRepository;

    @PostMapping("/sign-up")
    public CreateMemberResponseDTO signUpMember(@RequestBody CreateMemberRequestDTO dto) {
        Member member = new Member(dto.getIdToken(), dto.getNickName());

        memberRepository.save(member);

        return new CreateMemberResponseDTO(member.getIdToken(), member.getNickName());
    }

    @PostMapping("/log-in")
    public LogInMemberResponseDTO logInMember(@RequestBody LogInMemberRequestDTO dto) {
        Member findMember = memberRepository.findByIdToken(dto.getIdToken());

        return new LogInMemberResponseDTO(findMember.getIdToken(), findMember.getNickName());
    }

}
