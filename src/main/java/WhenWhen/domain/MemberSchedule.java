package WhenWhen.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity @Table(name = "_MEMBER_SCHEDULE")
@Getter
public class MemberSchedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SCHEDULE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;

    // --------------------------------------------------------------------------- //

    protected MemberSchedule() { }

    public MemberSchedule(Member member, Schedule schedule) {
        this.member = member;
        this.schedule = schedule;
    }

    // --------------------------------------------------------------------------- //

}