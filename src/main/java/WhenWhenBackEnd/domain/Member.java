package WhenWhenBackEnd.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;

// 회원
@Entity @Table(name = "_member")
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {

    // DB 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // 구글 DB 식별자(사실상 얘도 PK 역할이나 마찬가지)
    private String idToken;

    // 회원 닉네임
    private String nickName;

    protected Member() { }

    public Member(String idToken, String nickName) {
        this.idToken = idToken;
        this.nickName = nickName;
    }

}
