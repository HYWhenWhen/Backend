package WhenWhenBackEnd.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequestDTO {

    private String idToken;
    private String nickName;

}
