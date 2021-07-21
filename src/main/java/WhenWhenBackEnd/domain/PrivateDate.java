package WhenWhenBackEnd.domain;


import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

// 회원 개인 소속 캘린더
@Entity @Table(name = "_private_date")
@Getter
public class PrivateDate {

    // DB 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "private_date_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    protected PrivateDate() {}

    public PrivateDate(Member member, LocalDate localDate, Availability availability) {
        this.member = member;
        member.getPrivateDateList().add(this);

        this.localDate = localDate;
        this.availability = availability;
    }

}
