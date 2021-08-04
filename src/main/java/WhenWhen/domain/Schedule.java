package WhenWhen.domain;

import WhenWhen.util.MyUtil;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "_SCHEDULE")
@Getter
public class Schedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULE_ID")
    private Long id;

    private String name;

    private String scheduleKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOST_ID")
    private Member host;

    private LocalDate startDate;
    private LocalDate endDate;

    private Long expectedMemberCnt;

    private Long joinedMemberCnt;

    private LocalDateTime createLocalDateTime;

    // --------------------------------------------------------------------------- //

    protected Schedule() {}

    public Schedule(String name, Member host, LocalDate startDate, LocalDate endDate, Long expectedMemberCnt) {
        this.name = name;
        this.host = host;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectedMemberCnt = expectedMemberCnt;

        this.joinedMemberCnt = 0L;
        this.createLocalDateTime = LocalDateTime.now();
        this.scheduleKey = MyUtil.getRandomString();
    }

    // --------------------------------------------------------------------------- //

    public Long increaseJoinedMemberCnt() { return ++joinedMemberCnt; }
    public Long decreaseJoinedMemberCnt() { return --joinedMemberCnt; }
    public Long decreaseExpectedMemberCnt() {
        return --expectedMemberCnt;
    }

}
