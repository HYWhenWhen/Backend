package WhenWhenBackEnd.api;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.dto.schedule.AbandonScheduleRequestDTO;
import WhenWhenBackEnd.dto.schedule.AbandonScheduleResponseDTO;
import WhenWhenBackEnd.dto.schedule.CreateScheduleRequestDTO;
import WhenWhenBackEnd.dto.schedule.CreateScheduleResponseDTO;
import WhenWhenBackEnd.dto.schedule.GetSubmitPageRequestDTO;
import WhenWhenBackEnd.dto.schedule.GetSubmitPageResponseDTO;
import WhenWhenBackEnd.repository.DateRepository;
import WhenWhenBackEnd.repository.MemberRepository;
import WhenWhenBackEnd.repository.MemberScheduleRepository;
import WhenWhenBackEnd.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Schedule schedule = scheduleRepository.findByScheduleKey(dto.getScheduleKey());
        List<Long> memberScheduleIdList =
                memberScheduleRepository.findByMemberIdAndScheduleID(dto.getIdToken(), dto.getScheduleKey());

        if (memberScheduleIdList.size() != 0) {
            memberScheduleIdList.forEach(memberScheduleId -> dateRepository.DeleteByMemberScheduleId(memberScheduleId));
            schedule.decreaseExpectedMemberCnt();
        }

        schedule.decreaseJoinedMemberCnt();

        return new AbandonScheduleResponseDTO(dto.getIdToken(),dto.getScheduleKey());
    }
}
