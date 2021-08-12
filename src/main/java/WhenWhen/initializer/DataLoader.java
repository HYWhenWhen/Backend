package WhenWhen.initializer;

import WhenWhen.domain.*;
import WhenWhen.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

//@Component
@RequiredArgsConstructor
public class DataLoader {

    private final MemberService memberService;
    private final ScheduleService scheduleService;
    private final MemberScheduleService memberScheduleService;
    private final DateService dateService;
    private final PrivateDateService privateDateService;

   @PostConstruct
    public void load() {
        Member member1 = new Member("A1", "최한영");
        Member member2 = new Member("A2", "권순우");
        Member member3 = new Member("A3", "임의진");

        PrivateDate privateDate1 = new PrivateDate(member1, LocalDate.of(2017, 10, 1), "밥먹기");
        PrivateDate privateDate2 = new PrivateDate(member1, LocalDate.of(2017, 10, 3), "밥먹기");
        PrivateDate privateDate3 = new PrivateDate(member1, LocalDate.of(2018, 3, 13), "밥먹기");
        PrivateDate privateDate4 = new PrivateDate(member1, LocalDate.of(2018, 3, 21), "밥먹기");
        PrivateDate privateDate5 = new PrivateDate(member1, LocalDate.of(2019, 1, 12), "밥먹기");
        PrivateDate privateDate6 = new PrivateDate(member1, LocalDate.of(2019, 1, 22), "밥먹기");

        PrivateDate privateDate7 = new PrivateDate(member2, LocalDate.of(2017, 10, 3), "잠자기");
        PrivateDate privateDate8 = new PrivateDate(member2, LocalDate.of(2017, 10, 5), "잠자기");
        PrivateDate privateDate9 = new PrivateDate(member2, LocalDate.of(2018, 3, 12), "잠자기");
        PrivateDate privateDate10 = new PrivateDate(member2, LocalDate.of(2018, 3, 18), "게임하기");
        PrivateDate privateDate11 = new PrivateDate(member2, LocalDate.of(2019, 1, 9), "게임하기");
        PrivateDate privateDate12 = new PrivateDate(member2, LocalDate.of(2019, 1, 15), "게임하기");

        PrivateDate privateDate13 = new PrivateDate(member3, LocalDate.of(2017, 10, 7), "학교가기");
        PrivateDate privateDate14 = new PrivateDate(member3, LocalDate.of(2017, 10, 15), "학교가기");
        PrivateDate privateDate15 = new PrivateDate(member3, LocalDate.of(2018, 3, 14), "잠자기");
        PrivateDate privateDate16 = new PrivateDate(member3, LocalDate.of(2018, 3, 22), "잠자기");
        PrivateDate privateDate17 = new PrivateDate(member3, LocalDate.of(2019, 1, 12), "잠자기");
        PrivateDate privateDate18 = new PrivateDate(member3, LocalDate.of(2019, 1, 20), "학교가기");

        Schedule schedule1 = new Schedule("멋쟁이 사자처럼", member1, LocalDate.of(2017,9,30), LocalDate.of(2017, 10, 7), 4L);
        Schedule schedule2 = new Schedule("한강 맥주", member2, LocalDate.of(2018, 3, 13), LocalDate.of(2018, 3, 20), 5L);
        Schedule schedule3 = new Schedule("롯데월드", member3, LocalDate.of(2019,1,11), LocalDate.of(2019, 1, 16), 3L);

        MemberSchedule memberSchedule1 = new MemberSchedule(member1, schedule1);
        MemberSchedule memberSchedule2 = new MemberSchedule(member2, schedule1);
        MemberSchedule memberSchedule3 = new MemberSchedule(member1, schedule2);

        Date s1Date1_1 = new Date(memberSchedule1, schedule1.getStartDate(), Availability.POSSIBLE);
        Date s1Date1_2 = new Date(memberSchedule1, schedule1.getStartDate().plusDays(1L), Availability.POSSIBLE);
        Date s1Date1_3 = new Date(memberSchedule1, schedule1.getStartDate().plusDays(2L), Availability.ADJUSTABLE);
        Date s1Date1_4 = new Date(memberSchedule1, schedule1.getStartDate().plusDays(3L), Availability.POSSIBLE);
        Date s1Date1_5 = new Date(memberSchedule1, schedule1.getStartDate().plusDays(4L), Availability.IMPOSSIBLE);
        Date s1Date1_6 = new Date(memberSchedule1, schedule1.getStartDate().plusDays(5L), Availability.POSSIBLE);
        Date s1Date1_7 = new Date(memberSchedule1, schedule1.getStartDate().plusDays(6L), Availability.POSSIBLE);
        Date s1Date1_8 = new Date(memberSchedule1, schedule1.getStartDate().plusDays(7L), Availability.IMPOSSIBLE);

        Date s1Date2_1 = new Date(memberSchedule2, schedule1.getStartDate(), Availability.POSSIBLE);
        Date s1Date2_2 = new Date(memberSchedule2, schedule1.getStartDate().plusDays(1L), Availability.POSSIBLE);
        Date s1Date2_3 = new Date(memberSchedule2, schedule1.getStartDate().plusDays(2L), Availability.ADJUSTABLE);
        Date s1Date2_4 = new Date(memberSchedule2, schedule1.getStartDate().plusDays(3L), Availability.POSSIBLE);
        Date s1Date2_5 = new Date(memberSchedule2, schedule1.getStartDate().plusDays(4L), Availability.IMPOSSIBLE);
        Date s1Date2_6 = new Date(memberSchedule2, schedule1.getStartDate().plusDays(5L), Availability.IMPOSSIBLE);
        Date s1Date2_7 = new Date(memberSchedule2, schedule1.getStartDate().plusDays(6L), Availability.IMPOSSIBLE);
        Date s1Date2_8 = new Date(memberSchedule2, schedule1.getStartDate().plusDays(7L), Availability.IMPOSSIBLE);

        Date s2Date1_1 = new Date(memberSchedule3, schedule2.getStartDate(), Availability.POSSIBLE);
        Date s2Date1_2 = new Date(memberSchedule3, schedule2.getStartDate().plusDays(1L), Availability.POSSIBLE);
        Date s2Date1_3 = new Date(memberSchedule3, schedule2.getStartDate().plusDays(2L), Availability.ADJUSTABLE);
        Date s2Date1_4 = new Date(memberSchedule3, schedule2.getStartDate().plusDays(3L), Availability.POSSIBLE);
        Date s2Date1_5 = new Date(memberSchedule3, schedule2.getStartDate().plusDays(4L), Availability.ADJUSTABLE);
        Date s2Date1_6 = new Date(memberSchedule3, schedule2.getStartDate().plusDays(5L), Availability.IMPOSSIBLE);
        Date s2Date1_7 = new Date(memberSchedule3, schedule2.getStartDate().plusDays(6L), Availability.IMPOSSIBLE);
        Date s2Date1_8 = new Date(memberSchedule3, schedule2.getStartDate().plusDays(7L), Availability.POSSIBLE);

       memberService.save(member1);
       memberService.save(member2);
       memberService.save(member3);

       privateDateService.save(privateDate1);
       privateDateService.save(privateDate2);
       privateDateService.save(privateDate3);
       privateDateService.save(privateDate4);
       privateDateService.save(privateDate5);
       privateDateService.save(privateDate6);

       privateDateService.save(privateDate7);
       privateDateService.save(privateDate8);
       privateDateService.save(privateDate9);
       privateDateService.save(privateDate10);
       privateDateService.save(privateDate11);
       privateDateService.save(privateDate12);

       privateDateService.save(privateDate13);
       privateDateService.save(privateDate14);
       privateDateService.save(privateDate15);
       privateDateService.save(privateDate16);
       privateDateService.save(privateDate17);
       privateDateService.save(privateDate18);

       scheduleService.save(schedule1);
       scheduleService.save(schedule2);
       scheduleService.save(schedule3);

       memberScheduleService.save(memberSchedule1);
       memberScheduleService.save(memberSchedule2);
       memberScheduleService.save(memberSchedule3);

       dateService.save(s1Date1_1);
       dateService.save(s1Date1_2);
       dateService.save(s1Date1_3);
       dateService.save(s1Date1_4);
       dateService.save(s1Date1_5);
       dateService.save(s1Date1_6);
       dateService.save(s1Date1_7);
       dateService.save(s1Date1_8);

       dateService.save(s1Date2_1);
       dateService.save(s1Date2_2);
       dateService.save(s1Date2_3);
       dateService.save(s1Date2_4);
       dateService.save(s1Date2_5);
       dateService.save(s1Date2_6);
       dateService.save(s1Date2_7);
       dateService.save(s1Date2_8);

       dateService.save(s2Date1_1);
       dateService.save(s2Date1_2);
       dateService.save(s2Date1_3);
       dateService.save(s2Date1_4);
       dateService.save(s2Date1_5);
       dateService.save(s2Date1_6);
       dateService.save(s2Date1_7);
       dateService.save(s2Date1_8);
    }

}