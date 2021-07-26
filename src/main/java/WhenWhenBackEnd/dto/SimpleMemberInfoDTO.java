package WhenWhenBackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMemberInfoDTO {

    private String idToken;
    private String nickName;

    List<SimpleDateDTO2> dates;

}
