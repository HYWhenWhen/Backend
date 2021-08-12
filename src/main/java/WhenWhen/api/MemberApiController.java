package WhenWhen.api;

import WhenWhen.domain.Member;
import WhenWhen.domain.MemberSchedule;
import WhenWhen.domain.PrivateDate;
import WhenWhen.domain.Schedule;
import WhenWhen.dto.basic.SimpleMyScheduleDTO;
import WhenWhen.dto.basic.SimpleMyScheduleDTO2;
import WhenWhen.dto.basic.SimpleScheduleDTO;
import WhenWhen.dto.member.*;
import WhenWhen.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {

    private final MemberService memberService;
    private final PrivateDateService privateDateService;
    private final ScheduleService scheduleService;
    private final MemberScheduleService memberScheduleService;
    private final DateService dateService;

//    @PostMapping("/sign-up")
//    public CreateMemberResponseDTO signUp(@RequestBody CreateMemberRequestDTO dto) {
//        if(dto.getNickName().length() <= 0) return new CreateMemberResponseDTO(false);
//
//        Member member = new Member(dto.getIdToken(), dto.getNickName());
//
//        Boolean success = memberService.save(member);
//
//        return new CreateMemberResponseDTO(success);
//    }

    @PostMapping("/log-in")
    public LogInMemberResponseDTO logIn(@RequestBody LogInMemberRequestDTO dto) {
        Member findMember = memberService.findOne(dto.getIdToken());

        if (findMember == null) {
            Member member = new Member(dto.getIdToken(), dto.getNickName());
            memberService.save(member);

            return new LogInMemberResponseDTO(member.getIdToken(), member.getNickName(), true);
        }

        return new LogInMemberResponseDTO(findMember.getIdToken(), findMember.getNickName(), true);
    }

    @PostMapping("/add-my-schedule")
    public AddMyScheduleResponseDTO addMySchedule(@RequestBody AddMyScheduleRequestDTO dto) {
        Member member = memberService.findOne(dto.getIdToken());
        if(member == null)
            return new AddMyScheduleResponseDTO(false);

        LocalDate localDate = dto.getLocalDate();
        String scheduleName = dto.getScheduleName();

        PrivateDate privateDate = new PrivateDate(member, localDate, scheduleName);

        privateDateService.save(privateDate);

        return new AddMyScheduleResponseDTO(true);
    }

    @PostMapping("/delete-my-schedule")
    public DeleteMyScheduleResponseDTO deleteMySchedule(@RequestBody DeleteMyScheduleRequestDTO dto) {
        Member member = memberService.findOne(dto.getIdToken());

        PrivateDate privateDate = privateDateService.findOne(dto.getScheduleKey());

        if(member == null || privateDate.getMember() != member)return new DeleteMyScheduleResponseDTO(false);

        privateDateService.delete(privateDate);

        return new DeleteMyScheduleResponseDTO(true);
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
            return new GetMyPageResponseDTO(false, member.getIdToken(), member.getNickName(),null, null);

        List<PrivateDate> privateDateList = privateDateService.findByMember(member);

        List<LocalDate> list = privateDateList.stream()
                .map(privateDate -> LocalDate.from(privateDate.getLocalDate()))
                .collect(Collectors.toList());

        List<Schedule> list2 = scheduleService.findByMember(member);
        List<SimpleScheduleDTO> list3 = list2.stream()
                .sorted(
                        new Comparator<Schedule>() {
                            @Override
                            public int compare(Schedule o1, Schedule o2) {
                                if (o1.getCreateLocalDateTime().isAfter(o2.getCreateLocalDateTime())) return -1;
                                else return 1;
                            }
                        }
                )
                .map(schedule -> new SimpleScheduleDTO(schedule.getName(), schedule.getScheduleKey()))
                .collect(Collectors.toList());

        return new GetMyPageResponseDTO(true, member.getIdToken(), member.getNickName(),list, list3);
    }

    @PostMapping("/get-my-page-modal")
    public GetMyPageModalResponseDTO getMyPageModal(@RequestBody GetMyPageModalRequestDTO dto) {
        Member member = memberService.findOne(dto.getIdToken());
        LocalDate localDate = dto.getLocalDate();

        if(member == null)
            return new GetMyPageModalResponseDTO(null, false);

        List<PrivateDate> privateDateList = privateDateService.findByMember(member);

        List<SimpleMyScheduleDTO2> result = privateDateList.stream()
                .filter(privateDate -> privateDate.getLocalDate().equals(localDate) == true)
                .map(privateDate -> new SimpleMyScheduleDTO2(privateDate.getId(), privateDate.getName()))
                .sorted(
                        Comparator.comparing(simpleMyScheduleDTO2 -> simpleMyScheduleDTO2.getMyScheduleName())
                )
                .collect(Collectors.toList());

        return new GetMyPageModalResponseDTO(result, true);
    }

    @PostMapping("/abandon")
    public AbandonResponseDTO abandon(@RequestBody AbandonRequestDTO dto) {
        Member member = memberService.findOne(dto.getIdToken());
        Schedule schedule = scheduleService.findOne(dto.getScheduleKey());

        if(member == null || schedule == null)return new AbandonResponseDTO(false);

        MemberSchedule memberSchedule = memberScheduleService.findByMemberAndSchedule(member, schedule);

        schedule.decreaseExpectedMemberCnt();
        if (memberSchedule == null) return new AbandonResponseDTO(true);

        dateService.deleteByMemberSchedule(memberSchedule);
        memberScheduleService.delete(memberSchedule);

        return new AbandonResponseDTO(true);
    }

}
