package WhenWhenBackEnd.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

// 특정회원과 스케줄에 종속된 날짜 정보
@Entity @Table(name = "_date")
@Getter
public class Date {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_schedule_id")
    private MemberSchedule memberSchedule;

    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    protected Date() { }

    public Date(MemberSchedule memberSchedule, LocalDate localDate, Availability availability) {
        this.memberSchedule = memberSchedule;
        memberSchedule.getDateList().add(this);

        this.localDate = localDate;
        this.availability = availability;
    }

}