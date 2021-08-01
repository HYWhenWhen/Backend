package WhenWhenBackEnd.dto.basic;

import lombok.Data;

@Data
public class SimpleMemberDTO {

    private String idToken;
    private String nickName;

    public SimpleMemberDTO(String idToken, String nickName) {
        this.idToken = idToken;
        this.nickName = nickName;
    }
}
