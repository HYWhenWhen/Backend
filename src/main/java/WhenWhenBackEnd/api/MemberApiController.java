package WhenWhenBackEnd.api;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.PrivateDate;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.dto.SimpleDateDTO2;
import WhenWhenBackEnd.dto.SimpleScheduleDTO;
import WhenWhenBackEnd.dto.member.*;
import WhenWhenBackEnd.repository.MemberRepository;
import WhenWhenBackEnd.repository.PrivateDateRepository;
import WhenWhenBackEnd.repository.ScheduleRepository;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {

    private final MemberRepository memberRepository;
    private final PrivateDateRepository privateDateRepository;
    private final ScheduleRepository scheduleRepository;

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

    @PostMapping("/get-my-page")
    public GetMyPageResponseDTO getMyPage(@RequestBody GetMyPageRequestDTO dto) {
        Member member = memberRepository.findByIdToken(dto.getIdToken());

        List<SimpleDateDTO2> privateDateList = privateDateRepository.findByMember(member)
                .stream()
                .map(privateDate -> new SimpleDateDTO2(privateDate.getLocalDate(), privateDate.getAvailability().ordinal()))
                .collect(Collectors.toList());

        List<SimpleScheduleDTO> scheduleList = scheduleRepository.findByMember(member)
                .stream()
                .map(schedule -> new SimpleScheduleDTO(schedule.getName(), schedule.getScheduleKey()))
                .collect(Collectors.toList());

        return new GetMyPageResponseDTO(member.getIdToken(), member.getNickName(), privateDateList, scheduleList);

    }

}
