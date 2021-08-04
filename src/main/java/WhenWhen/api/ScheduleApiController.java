package WhenWhen.api;

import WhenWhen.domain.*;
import WhenWhen.dto.basic.SimpleDataDTO3;
import WhenWhen.dto.basic.SimpleDateDTO2;
import WhenWhen.dto.schedule.*;
import WhenWhen.service.DateService;
import WhenWhen.service.MemberScheduleService;
import WhenWhen.service.MemberService;
import WhenWhen.service.ScheduleService;
import WhenWhen.util.MyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleApiController {

    private final ScheduleService scheduleService;
    private final MemberService memberService;
    private final MemberScheduleService memberScheduleService;
    private final DateService dateService;

    @PostMapping("/create-schedule")
    public CreateScheduleResponseDTO create(@RequestBody CreateScheduleRequestDTO dto) {
        Member host = memberService.findOne(dto.getHostIdToken());

        Schedule schedule = new Schedule(dto.getScheduleName(), host, dto.getStartDate(), dto.getEndDate(), dto.getExpectedMemberCnt());

        Boolean success = scheduleService.save(schedule);

        return new CreateScheduleResponseDTO(schedule, success);
    }

    @PostMapping("/get-submit-page")
    public GetSubmitPageResponseDTO getSubmitPage(@RequestBody GetSubmitPageRequestDTO dto) {
        Member member = memberService.findOne(dto.getIdToken());
        Schedule schedule = scheduleService.findOne(dto.getScheduleKey());

        if(member == null || schedule == null)
            return new GetSubmitPageResponseDTO(null, false);

        return new GetSubmitPageResponseDTO(schedule, true);
    }

    @PostMapping("/submit/member-schedule")
    public SubmitMemberScheduleResponseDTO submit(@RequestBody SubmitMemberScheduleRequestDTO dto) {
        Member member = memberService.findOne(dto.getIdToken());
        Schedule schedule = scheduleService.findOne(dto.getScheduleKey());

        if(member == null || schedule == null)
            return new SubmitMemberScheduleResponseDTO(false);

        int joinedCnt = memberScheduleService.findBySchedule(schedule).size();
        if(joinedCnt >= schedule.getExpectedMemberCnt())return new SubmitMemberScheduleResponseDTO(false);

        MemberSchedule memberSchedule = new MemberSchedule(member, schedule);
        memberScheduleService.save(memberSchedule);

        List<Date> dateList = dto.getDates().stream()
                .filter(dateDTO ->
                        !(dateDTO.getLocalDate().isBefore(schedule.getStartDate()) || dateDTO.getLocalDate().isAfter(schedule.getEndDate()))
                )
                .map(dateDTO -> new Date(memberSchedule, dateDTO.getLocalDate(), dateDTO.getAvailability()))
                .collect(Collectors.toList());

        dateService.saveAll(dateList);

        return new SubmitMemberScheduleResponseDTO(true);
    }

    @PostMapping("/delete-schedule")
    public DeleteScheduleResponseDTO deleteSchedule(@RequestBody DeleteScheduleRequestDTO dto) {
        Member host = memberService.findOne(dto.getHostIdToken());
        Schedule schedule = scheduleService.findOne(dto.getScheduleKey());

        if(host == null || schedule == null)
            return new DeleteScheduleResponseDTO(false);

        if(schedule.getHost() != host)return new DeleteScheduleResponseDTO(false);

        dateService.deleteBySchedule(schedule);
        memberScheduleService.deleteBySchedule(schedule);
        scheduleService.delete(schedule);

        return new DeleteScheduleResponseDTO(true);
    }

    @PostMapping("/get-result-page")
    public GetResultPageResponseDTO getResultPage(@RequestBody GetResultPageRequestDTO dto) {
        Schedule schedule = scheduleService.findOne(dto.getScheduleKey());

        if(schedule == null)
            return new GetResultPageResponseDTO(false, null, null, null, null, null, null);

        List<Date> dateList = dateService.findBySchedule(schedule);

        TreeMap<LocalDate, SimpleDateDTO2> dates = MyUtil.getResultDates(schedule, dateList);

        return new GetResultPageResponseDTO(true, schedule.getName(), schedule.getStartDate(),
                schedule.getEndDate(), schedule.getExpectedMemberCnt(), schedule.getJoinedMemberCnt(), dates);
    }

//    @PostMapping("/get-result-page-modal")
//    public GetResultPageModalResponseDTO getResultPageModal(@RequestBody GetResultPageModalRequestDTO dto) {
//        Schedule schedule = scheduleService.findOne(dto.getScheduleKey());
//        LocalDate localDate = dto.getDate();
//
//        if(schedule == null) return new GetResultPageModalResponseDTO(false, null);
//        if(localDate.isBefore(schedule.getStartDate()) || localDate.isAfter(schedule.getEndDate()))
//            return new GetResultPageModalResponseDTO(false, null);
//
//        List<Date> dateList = dateService.findByScheduleAndDateWithMembers(schedule, localDate);
//
//        TreeMap<Availability, List<SimpleMemberDTO>> availability = MyUtil.getResultAvailability(dateList);
//
//        return new GetResultPageModalResponseDTO(true, availability);
//    }
    @PostMapping("/get-result-page-modal")
    public GetResultPageModalResponseDTO getResultPageModal(@RequestBody GetResultPageModalRequestDTO dto) {
        Schedule schedule = scheduleService.findOne(dto.getScheduleKey());
        LocalDate localDate = dto.getDate();

        if(schedule == null) return new GetResultPageModalResponseDTO(false, null);
        if(localDate.isBefore(schedule.getStartDate()) || localDate.isAfter(schedule.getEndDate()))
            return new GetResultPageModalResponseDTO(false, null);

        List<Date> dateList = dateService.findByScheduleAndDateWithMembers(schedule, localDate);

        List<SimpleDataDTO3> availability = MyUtil.getResultAvailability(dateList);

        return new GetResultPageModalResponseDTO(true, availability);
    }

}
