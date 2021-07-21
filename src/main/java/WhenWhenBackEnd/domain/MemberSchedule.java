package WhenWhenBackEnd.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 다대다 관계인 멤버와 스케줄 관계를 다대일 관계 두개로 풀어내기 위한 클래스.
// 회원 1명은 여러 개의 스케줄에 포함되어 있을 수 있고, 1개의 스케줄에는 여러 명의 회원이 들어갈 수 있다.
// 특정 회원이 자신이 들어가 있는 스케줄을 조회하거나, 특정 스케줄이 자신이 포함하고 있는 회원을 조회할 때 이 테이블과 조인 쿼리를 하면 된다.
@Entity @Table(name = "_member_schedule")
@Getter
public class MemberSchedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    // 날짜 모음
    @OneToMany(mappedBy = "memberSchedule", fetch = FetchType.LAZY)
    private List<Date> dateList = new ArrayList<>();

    protected MemberSchedule() { }

    public MemberSchedule(Member member, Schedule schedule) {
        this.member = member;
        member.getMemberScheduleList().add(this);

        this.schedule = schedule;
        schedule.getMemberScheduleList().add(this);
    }

}