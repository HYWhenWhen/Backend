package WhenWhenBackEnd.api;

import WhenWhenBackEnd.domain.Date;
import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.MemberSchedule;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.dto.date.CreateMemberScheduleRequestDTO;
import WhenWhenBackEnd.dto.date.CreateMemberScheduleResponseDTO;
import WhenWhenBackEnd.repository.DateRepository;
import WhenWhenBackEnd.repository.MemberRepository;
import WhenWhenBackEnd.repository.MemberScheduleRepository;
import WhenWhenBackEnd.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DateApiController {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberScheduleRepository memberScheduleRepository;
    private final DateRepository dateRepository;

    @PostMapping("/submit")
    public CreateMemberScheduleResponseDTO submitMemberSchedule(@RequestBody CreateMemberScheduleRequestDTO dto) {

        Member member = memberRepository.findByIdToken(dto.getIdToken());
        Schedule schedule = scheduleRepository.findByScheduleKey(dto.getScheduleKey());
        schedule.increaseJoinedMemberCnt();

        MemberSchedule memberSchedule = new MemberSchedule(member, schedule);
        memberScheduleRepository.save(memberSchedule);

        List<Date> dateList = dto.getDates().stream()
                .map(dateDTO -> new Date(memberSchedule, dateDTO.getLocalDate(), dateDTO.getAvailability()))
                .collect(Collectors.toList());

        dateList.stream().forEach(date -> dateRepository.save(date));

        return new CreateMemberScheduleResponseDTO(member.getIdToken(), member.getNickName());
    }

}
