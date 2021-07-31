package WhenWhenBackEnd.initiator;

import WhenWhenBackEnd.domain.*;
import WhenWhenBackEnd.repository.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// 실제 운영 단계에서는 @Component 주석 처리
@Component
public class DataLoader {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberScheduleRepository memberScheduleRepository;
    private final DateRepository dateRepository;
    private final PrivateDateRepository privateDateRepository;

    public DataLoader(MemberRepository memberRepository, ScheduleRepository scheduleRepository, MemberScheduleRepository memberScheduleRepository, DateRepository dateRepository, PrivateDateRepository privateDateRepository) {
        this.memberRepository = memberRepository;
        this.scheduleRepository = scheduleRepository;
        this.memberScheduleRepository = memberScheduleRepository;
        this.dateRepository = dateRepository;
        this.privateDateRepository = privateDateRepository;

        load();
    }

    private void load() {
        Member member1 = new Member("A1", "최한영");
        Member member2 = new Member("A2", "권순우");
        Member member3 = new Member("A3", "임의진");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        // member1
        PrivateDate privateDate1 = new PrivateDate(member1, LocalDate.of(2017, 10, 1), Availability.POSSIBLE);
        PrivateDate privateDate2 = new PrivateDate(member1, LocalDate.of(2017, 10, 3), Availability.IMPOSSIBLE);
        PrivateDate privateDate3 = new PrivateDate(member1, LocalDate.of(2018, 3, 13), Availability.ADJUSTABLE);
        PrivateDate privateDate4 = new PrivateDate(member1, LocalDate.of(2018, 3, 21), Availability.ADJUSTABLE);
        PrivateDate privateDate5 = new PrivateDate(member1, LocalDate.of(2019, 1, 12), Availability.POSSIBLE);
        PrivateDate privateDate6 = new PrivateDate(member1, LocalDate.of(2019, 1, 22), Availability.ADJUSTABLE);

        privateDateRepository.save(privateDate1);
        privateDateRepository.save(privateDate2);
        privateDateRepository.save(privateDate3);
        privateDateRepository.save(privateDate4);
        privateDateRepository.save(privateDate5);
        privateDateRepository.save(privateDate6);

        // member2
        PrivateDate privateDate7 = new PrivateDate(member2, LocalDate.of(2017, 10, 3), Availability.IMPOSSIBLE);
        PrivateDate privateDate8 = new PrivateDate(member2, LocalDate.of(2017, 10, 5), Availability.ADJUSTABLE);
        PrivateDate privateDate9 = new PrivateDate(member2, LocalDate.of(2018, 3, 12), Availability.POSSIBLE);
        PrivateDate privateDate10 = new PrivateDate(member2, LocalDate.of(2018, 3, 18), Availability.ADJUSTABLE);
        PrivateDate privateDate11 = new PrivateDate(member2, LocalDate.of(2019, 1, 9), Availability.ADJUSTABLE);
        PrivateDate privateDate12 = new PrivateDate(member2, LocalDate.of(2019, 1, 15), Availability.POSSIBLE);

        privateDateRepository.save(privateDate7);
        privateDateRepository.save(privateDate8);
        privateDateRepository.save(privateDate9);
        privateDateRepository.save(privateDate10);
        privateDateRepository.save(privateDate11);
        privateDateRepository.save(privateDate12);

        // member3
        PrivateDate privateDate13 = new PrivateDate(member3, LocalDate.of(2017, 10, 7), Availability.POSSIBLE);
        PrivateDate privateDate14 = new PrivateDate(member3, LocalDate.of(2017, 10, 15), Availability.POSSIBLE);
        PrivateDate privateDate15 = new PrivateDate(member3, LocalDate.of(2018, 3, 14), Availability.ADJUSTABLE);
        PrivateDate privateDate16 = new PrivateDate(member3, LocalDate.of(2018, 3, 22), Availability.ADJUSTABLE);
        PrivateDate privateDate17 = new PrivateDate(member3, LocalDate.of(2019, 1, 12), Availability.IMPOSSIBLE);
        PrivateDate privateDate18 = new PrivateDate(member3, LocalDate.of(2019, 1, 20), Availability.IMPOSSIBLE);

        privateDateRepository.save(privateDate13);
        privateDateRepository.save(privateDate14);
        privateDateRepository.save(privateDate15);
        privateDateRepository.save(privateDate16);
        privateDateRepository.save(privateDate17);
        privateDateRepository.save(privateDate18);

        Schedule schedule1 = new Schedule("멋쟁이 사자처럼", member1, LocalDate.of(2017,9,30), LocalDate.of(2017, 10, 7), 4L);
        Schedule schedule2 = new Schedule("한강 맥주", member2, LocalDate.of(2018, 3, 13), LocalDate.of(2018, 3, 20), 5L);
        Schedule schedule3 = new Schedule("롯데월드", member3, LocalDate.of(2019,1,11), LocalDate.of(2019, 1, 16), 3L);

        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);
        scheduleRepository.save(schedule3);

        MemberSchedule memberSchedule1 = new MemberSchedule(member1, schedule1);
        MemberSchedule memberSchedule2 = new MemberSchedule(member2, schedule1);
        MemberSchedule memberSchedule3 = new MemberSchedule(member1, schedule2);

        memberScheduleRepository.save(memberSchedule1);
        memberScheduleRepository.save(memberSchedule2);
        memberScheduleRepository.save(memberSchedule3);

        //  schedule 1, member 1인 데이터들
        Date s1Date1_1 = new Date(memberSchedule1, schedule1.getStartDate(), Availability.POSSIBLE);
        Date s1Date1_2 = new Date(memberSchedule1, LocalDate.from(schedule1.getStartDate().plusDays(1L)), Availability.POSSIBLE);
        Date s1Date1_3 = new Date(memberSchedule1, LocalDate.from(schedule1.getStartDate().plusDays(2L)), Availability.ADJUSTABLE);
        Date s1Date1_4 = new Date(memberSchedule1, LocalDate.from(schedule1.getStartDate().plusDays(3L)), Availability.POSSIBLE);
        Date s1Date1_5 = new Date(memberSchedule1, LocalDate.from(schedule1.getStartDate().plusDays(4L)), Availability.IMPOSSIBLE);
        Date s1Date1_6 = new Date(memberSchedule1, LocalDate.from(schedule1.getStartDate().plusDays(5L)), Availability.POSSIBLE);
        Date s1Date1_7 = new Date(memberSchedule1, LocalDate.from(schedule1.getStartDate().plusDays(6L)), Availability.POSSIBLE);
        Date s1Date1_8 = new Date(memberSchedule1, LocalDate.from(schedule1.getStartDate().plusDays(7L)), Availability.IMPOSSIBLE);

        // schedule 1, member 2인 데이터들
        Date s1Date2_1 = new Date(memberSchedule2, schedule1.getStartDate(), Availability.POSSIBLE);
        Date s1Date2_2 = new Date(memberSchedule2, LocalDate.from(schedule1.getStartDate().plusDays(1L)), Availability.POSSIBLE);
        Date s1Date2_3 = new Date(memberSchedule2, LocalDate.from(schedule1.getStartDate().plusDays(2L)), Availability.ADJUSTABLE);
        Date s1Date2_4 = new Date(memberSchedule2, LocalDate.from(schedule1.getStartDate().plusDays(3L)), Availability.POSSIBLE);
        Date s1Date2_5 = new Date(memberSchedule2, LocalDate.from(schedule1.getStartDate().plusDays(4L)), Availability.IMPOSSIBLE);
        Date s1Date2_6 = new Date(memberSchedule2, LocalDate.from(schedule1.getStartDate().plusDays(5L)), Availability.IMPOSSIBLE);
        Date s1Date2_7 = new Date(memberSchedule2, LocalDate.from(schedule1.getStartDate().plusDays(6L)), Availability.IMPOSSIBLE);
        Date s1Date2_8 = new Date(memberSchedule2, LocalDate.from(schedule1.getStartDate().plusDays(7L)), Availability.IMPOSSIBLE);

        // schedule 2, member 1인 데이터들
        Date s2Date1_1 = new Date(memberSchedule3, schedule1.getStartDate(), Availability.POSSIBLE);
        Date s2Date1_2 = new Date(memberSchedule3, LocalDate.from(schedule2.getStartDate().plusDays(1L)), Availability.POSSIBLE);
        Date s2Date1_3 = new Date(memberSchedule3, LocalDate.from(schedule2.getStartDate().plusDays(2L)), Availability.ADJUSTABLE);
        Date s2Date1_4 = new Date(memberSchedule3, LocalDate.from(schedule2.getStartDate().plusDays(3L)), Availability.POSSIBLE);
        Date s2Date1_5 = new Date(memberSchedule3, LocalDate.from(schedule2.getStartDate().plusDays(4L)), Availability.ADJUSTABLE);
        Date s2Date1_6 = new Date(memberSchedule3, LocalDate.from(schedule2.getStartDate().plusDays(5L)), Availability.IMPOSSIBLE);
        Date s2Date1_7 = new Date(memberSchedule3, LocalDate.from(schedule2.getStartDate().plusDays(6L)), Availability.IMPOSSIBLE);
        Date s2Date1_8 = new Date(memberSchedule3, LocalDate.from(schedule2.getStartDate().plusDays(7L)), Availability.POSSIBLE);

        dateRepository.save(s1Date1_1);
        dateRepository.save(s1Date1_2);
        dateRepository.save(s1Date1_3);
        dateRepository.save(s1Date1_4);
        dateRepository.save(s1Date1_5);
        dateRepository.save(s1Date1_6);
        dateRepository.save(s1Date1_7);
        dateRepository.save(s1Date1_8);

        dateRepository.save(s1Date2_1);
        dateRepository.save(s1Date2_2);
        dateRepository.save(s1Date2_3);
        dateRepository.save(s1Date2_4);
        dateRepository.save(s1Date2_5);
        dateRepository.save(s1Date2_6);
        dateRepository.save(s1Date2_7);
        dateRepository.save(s1Date2_8);

        dateRepository.save(s2Date1_1);
        dateRepository.save(s2Date1_2);
        dateRepository.save(s2Date1_3);
        dateRepository.save(s2Date1_4);
        dateRepository.save(s2Date1_5);
        dateRepository.save(s2Date1_6);
        dateRepository.save(s2Date1_7);
        dateRepository.save(s2Date1_8);
    }

}
