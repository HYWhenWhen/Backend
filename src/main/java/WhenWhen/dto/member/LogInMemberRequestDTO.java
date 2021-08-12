package WhenWhen.dto.member;

import lombok.Data;

@Data
public class LogInMemberRequestDTO {

    private String idToken;
    private String code;
    private String nickName;

}