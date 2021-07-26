package WhenWhenBackEnd.dto.member;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberResponseDTO {

    private String idToken;
    private String nickName;

}