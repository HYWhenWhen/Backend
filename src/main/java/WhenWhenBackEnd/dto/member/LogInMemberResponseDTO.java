package WhenWhenBackEnd.dto.member;

import WhenWhenBackEnd.domain.Member;
import lombok.Data;

@Data
public class LogInMemberResponseDTO {

    private String nickName;
    private Boolean success;

    public LogInMemberResponseDTO(Member member, Boolean success) {
        if (success == true) {
            this.nickName = member.getNickName();
        }

        this.success = success;
    }

}