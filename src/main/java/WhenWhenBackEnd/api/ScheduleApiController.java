package WhenWhenBackEnd.api;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.MemberSchedule;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.dto.schedule.*;
import WhenWhenBackEnd.repository.DateRepository;
import WhenWhenBackEnd.repository.MemberRepository;
import WhenWhenBackEnd.repository.MemberScheduleRepository;
import WhenWhenBackEnd.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleApiController {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberScheduleRepository memberScheduleRepository;
    private final DateRepository dateRepository;

    @PostMapping("/create-schedule")
    public CreateScheduleResponseDTO createSchedule(@RequestBody CreateScheduleRequestDTO dto) {
        Member host = memberRepository.findByIdToken(dto.getHostIdToken());
        Schedule schedule = new Schedule(dto.getName(), host, dto.getStartDate(), dto.getEndDate(), dto.getExpectedMemberCnt());

        scheduleRepository.save(schedule);

        return new CreateScheduleResponseDTO(schedule.getName(), schedule.getScheduleKey());
    }

    @PostMapping("/get-submit-page")
    public GetSubmitPageResponseDTO getSubmitPage(@RequestBody GetSubmitPageRequestDTO dto) {
        Schedule schedule = scheduleRepository.findByScheduleKey(dto.getScheduleKey());

        return new GetSubmitPageResponseDTO(schedule.getName(),schedule.getScheduleKey(),schedule.getStartDate(),schedule.getEndDate());
    }

    @PostMapping("/abandon")
    public AbandonScheduleResponseDTO abandonSchedule(@RequestBody AbandonScheduleRequestDTO dto) {
        Member member = memberRepository.findByIdToken(dto.getIdToken());
        Schedule schedule = scheduleRepository.findByScheduleKey(dto.getScheduleKey());

        MemberSchedule memberSchedule = memberScheduleRepository.findByMemberAndSchedule(member, schedule);

        if (memberSchedule == null) {
            schedule.decreaseExpectedMemberCnt();
        }
        else {
            dateRepository.deleteByMemberSchedule(memberSchedule);
            schedule.decreaseJoinedMemberCnt();
            schedule.decreaseExpectedMemberCnt();
        }

        return new AbandonScheduleResponseDTO(member.getIdToken(), schedule.getScheduleKey());
    }

}
