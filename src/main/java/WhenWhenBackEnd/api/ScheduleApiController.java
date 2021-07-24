package WhenWhenBackEnd.api;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.dto.member.CreateMemberRequestDTO;
import WhenWhenBackEnd.dto.member.CreateMemberResponseDTO;
import WhenWhenBackEnd.dto.member.LogInMemberRequestDTO;
import WhenWhenBackEnd.dto.member.LogInMemberResponseDTO;
import WhenWhenBackEnd.dto.schedule.CreateScheduleRequestDTO;
import WhenWhenBackEnd.dto.schedule.CreateScheduleResponseDTO;
import WhenWhenBackEnd.repository.MemberRepository;
import WhenWhenBackEnd.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleApiController {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    @PostMapping("/create-schedule")
    public CreateScheduleResponseDTO createSchedule(@RequestBody CreateScheduleRequestDTO dto) {
        Member host = memberRepository.findByIdToken(dto.getHostIdToken());
        Schedule schedule = new Schedule(dto.getName(), host, dto.getStartDate(), dto.getEndDate(), dto.getExpectedMemberCnt());

        scheduleRepository.save(schedule);

        return new CreateScheduleResponseDTO(schedule.getName(), schedule.getScheduleKey());
    }

}
