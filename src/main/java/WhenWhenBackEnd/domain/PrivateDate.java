package WhenWhenBackEnd.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "_PRIVATE_DATE")
@Getter
public class PrivateDate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIVATE_DATE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private LocalDate localDate;

    private String name;

    // --------------------------------------------------------------------------- //

    protected PrivateDate() { }

    public PrivateDate(Member member, LocalDate localDate, String name) {
        this.member = member;
        this.localDate = localDate;
        this.name = name;
    }

    // --------------------------------------------------------------------------- //

}
