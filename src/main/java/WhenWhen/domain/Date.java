package WhenWhen.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "_DATE")
@Getter
public class Date {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DATE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SCHEDULE_ID")
    private MemberSchedule memberSchedule;

    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    // --------------------------------------------------------------------------- //

    protected Date() { }

    public Date(MemberSchedule memberSchedule, LocalDate localDate, Availability availability) {
        this.memberSchedule = memberSchedule;
        this.localDate = localDate;
        this.availability = availability;
    }

    // --------------------------------------------------------------------------- //

}