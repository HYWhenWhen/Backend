package WhenWhenBackEnd.api;

import WhenWhenBackEnd.domain.Date;
import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.MemberSchedule;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.dto.SimpleDateDTO2;
import WhenWhenBackEnd.dto.SimpleMemberInfoDTO;
import WhenWhenBackEnd.dto.schedule.GetBestDayRequestDTO;
import WhenWhenBackEnd.dto.schedule.GetBestDayResponseDTO;
import WhenWhenBackEnd.dto.schedule.*;
import WhenWhenBackEnd.logic.DetermineBestDate;
import WhenWhenBackEnd.logic.ExtractTotalDate;
import WhenWhenBackEnd.repository.DateRepository;
import WhenWhenBackEnd.repository.MemberRepository;
import WhenWhenBackEnd.repository.MemberScheduleRepository;
import WhenWhenBackEnd.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/delete-schedule")
    public DeleteScheduleResponseDTO deleteSchedule(@RequestBody DeleteScheduleRequestDTO dto) {
        Member host = memberRepository.findByIdToken(dto.getHostIdToken());
        Schedule schedule = scheduleRepository.findByScheduleKey(dto.getScheduleKey());

        dateRepository.deleteBySchedule(schedule);
        memberScheduleRepository.deleteBySchedule(schedule);
        scheduleRepository.delete(schedule);

        return new DeleteScheduleResponseDTO(host.getIdToken());
    }

    @PostMapping("/get-best-day")
    public GetBestDayResponseDTO getBestDay(@RequestBody GetBestDayRequestDTO dto) {
        Schedule schedule = scheduleRepository.findByScheduleKey(dto.getScheduleKey());

        List<Date> dateList = dateRepository.findBySchedule(schedule);

        List<LocalDate> bestDates = DetermineBestDate.get(schedule, dateList);

        return new GetBestDayResponseDTO(schedule.getScheduleKey(), bestDates);
    }

    @PostMapping("/get-total-page")
    public GetTotalPageResponseDTO getTotalPage(@RequestBody GetTotalPageRequestDTO dto) {
        Schedule schedule = scheduleRepository.findByScheduleKey(dto.getScheduleKey());
        List<Date> dateList = dateRepository.findBySchedule(schedule);

        List<LocalDate> bestDates = DetermineBestDate.get(schedule, dateList);
        List<SimpleDateDTO2> dates = ExtractTotalDate.get(schedule, dateList);

        List<Member> membersInSchedule = memberRepository.findBySchedule(schedule);

        List<SimpleMemberInfoDTO> membersDates = new ArrayList<>();

        for (Member member : membersInSchedule) {
            List<Date> dateList1 = dateRepository.findByMember(member);
            List<SimpleDateDTO2> simpleDateDTO2List = dateList1.stream()
                    .map(date -> new SimpleDateDTO2(date.getLocalDate(), date.getAvailability().ordinal()))
                    .collect(Collectors.toList());

            SimpleMemberInfoDTO info = new SimpleMemberInfoDTO(member.getIdToken(), member.getNickName(), simpleDateDTO2List);

            membersDates.add(info);
        }

        return new GetTotalPageResponseDTO(schedule.getName(), schedule.getScheduleKey(), schedule.getStartDate(), schedule.getEndDate(), bestDates, dates, membersDates);
    }

}


