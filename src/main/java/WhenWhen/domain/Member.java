package WhenWhen.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity @Table(name = "_MEMBER")
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String idToken;

    private String nickName;

    // --------------------------------------------------------------------------- //

    protected Member() {}

    public Member(String idToken, String nickName) {
        this.idToken = idToken;
        this.nickName = nickName;
    }

    // --------------------------------------------------------------------------- //

}
