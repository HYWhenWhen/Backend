package WhenWhenBackEnd.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 회원
@Entity @Table(name = "_member")
@Getter
public class Member {

    // DB 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // 구글 DB 식별자(사실상 얘도 PK 역할이나 마찬가지)
    private String idToken;

    // 회원 닉네임
    private String nickName;

    // 내 개인 일정 모음
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<PrivateDate> privateDateList = new ArrayList<>();

    // 멤버 스케줄 모음
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberSchedule> memberScheduleList = new ArrayList<>();

    protected Member() { }

    public Member(String idToken, String nickName) {
        this.idToken = idToken;
        this.nickName = nickName;
    }

}
