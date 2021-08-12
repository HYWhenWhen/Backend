package WhenWhen.dto.member;

import WhenWhen.domain.Member;
import lombok.Data;

@Data
public class LogInMemberResponseDTO {

    private String idToken;
    private String nickName;
    private Boolean success;

    public LogInMemberResponseDTO(String idToken, String nickName, Boolean success) {
        if (success == true) {
            this.nickName = nickName;
            this.idToken = idToken;
        }

        this.success = success;
    }

}