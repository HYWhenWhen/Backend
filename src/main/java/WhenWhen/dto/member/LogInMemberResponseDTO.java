package WhenWhen.dto.member;

import WhenWhen.domain.Member;
import lombok.Data;

@Data
public class LogInMemberResponseDTO {

    private String idToken;
    private String nickName;
    private Boolean success;

    public LogInMemberResponseDTO(String idToken, Member member, Boolean success) {
        if (success == true) {
            this.nickName = member.getNickName();
            this.idToken = idToken;
        }

        this.success = success;
    }

}