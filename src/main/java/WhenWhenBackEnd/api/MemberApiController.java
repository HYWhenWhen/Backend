package WhenWhenBackEnd.api;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.MemberSchedule;
import WhenWhenBackEnd.domain.PrivateDate;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.dto.basic.SimpleScheduleDTO;
import WhenWhenBackEnd.dto.basic.SimpleScheduleDTO2;
import WhenWhenBackEnd.dto.member.*;
import WhenWhenBackEnd.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {

    private final MemberService memberService;
    private final PrivateDateService privateDateService;
    private final ScheduleService scheduleService;
    private final MemberScheduleService memberScheduleService;
    private final DateService dateService;

    @PostMapping("/sign-up")
    public CreateMemberResponseDTO signUp(@RequestBody CreateMemberRequestDTO dto) {
        if(dto.getNickName().length() <= 0) return new CreateMemberResponseDTO(false);

        Member member = new Member(dto.getIdToken(), dto.getNickName());

        Boolean success = memberService.save(member);

        return new CreateMemberResponseDTO(success);
    }

    @PostMapping("/log-in")
    public LogInMemberResponseDTO logIn(@RequestBody LogInMemberRequestDTO dto) {
        Member member = memberService.logIn(dto.getIdToken());

        return new LogInMemberResponseDTO(member, member == null ? false : true);
    }

    @PostMapping("/add-my-schedule")
    public AddMyScheduleResponseDTO addMySchedule(@RequestBody AddMyScheduleRequestDTO dto) {
        Member member = memberService.findOne(dto.getIdToken());
        if(member == null)
            return new AddMyScheduleResponseDTO(false);

        List<PrivateDate> list = dto.getDates().stream()
                .map(simpleSchedule -> new PrivateDate(member, simpleSchedule.getLocalDate(), simpleSchedule.getScheduleName()))
                .collect(Collectors.toList());

        privateDateService.saveAll(list);

        return new AddMyScheduleResponseDTO(true);
    }

    @PostMapping("/get-my-schedule")
    public GetMyScheduleResponseDTO getMySchedule(@RequestBody GetMyScheduleRequestDTO dto) {
        Member member = memberService.findOne(dto.getIdToken());
        if(member == null)
            return new GetMyScheduleResponseDTO(null, false);

        List<PrivateDate> list = privateDateService.findByMember(member);

        List<LocalDate> localDates = list.stream()
                .map(privateDate -> privateDate.getLocalDate())
                .sorted()
                .collect(Collectors.toList());

        return new GetMyScheduleResponseDTO(localDates, true);
    }

    @PostMapping("/get-my-page")
    public GetMyPageResponseDTO getMyPage(@RequestBody GetMyPageRequestDTO dto) {
        Member member = memberService.findOne(dto.getIdToken());
        if(member == null)
            return new GetMyPageResponseDTO(null, null, false);

        List<PrivateDate> privateDateList = privateDateService.findByMember(member);

        List<SimpleScheduleDTO> simpleScheduleDTOList = privateDateList.stream()
                .map(privateDate -> new SimpleScheduleDTO(privateDate.getName(), privateDate.getLocalDate()))
                .sorted(
                        Comparator.comparing(simpleSchedule -> simpleSchedule.getLocalDate())
                )
                .collect(Collectors.toList());

        List<Schedule> scheduleList = scheduleService.findByMember(member);

        List<SimpleScheduleDTO2> simpleScheduleDTO2List = scheduleList.stream()
                .sorted(
                        (o1, o2) -> o1.getCreateLocalDateTime().compareTo(o2.getCreateLocalDateTime()) * -1
                )
                .map(schedule -> new SimpleScheduleDTO2(schedule.getName(), schedule.getScheduleKey()))
                .collect(Collectors.toList());

        return new GetMyPageResponseDTO(simpleScheduleDTOList, simpleScheduleDTO2List, true);
    }

    @PostMapping("/abandon")
    public AbandonResponseDTO abandon(@RequestBody AbandonRequestDTO dto) {
        Member member = memberService.findOne(dto.getIdToken());
        Schedule schedule = scheduleService.findOne(dto.getScheduleKey());

        if(member == null || schedule == null)return new AbandonResponseDTO(false);

        MemberSchedule memberSchedule = memberScheduleService.findByMemberAndSchedule(member, schedule);

        schedule.decreaseExpectedMemberCnt();
        if (memberSchedule == null) return new AbandonResponseDTO(true);

        schedule.decreaseJoinedMemberCnt();

        dateService.deleteByMemberSchedule(memberSchedule);
        memberScheduleService.delete(memberSchedule);

        return new AbandonResponseDTO(true);
    }

}
