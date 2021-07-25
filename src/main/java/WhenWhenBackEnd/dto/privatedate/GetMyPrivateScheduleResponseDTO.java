package WhenWhenBackEnd.dto.privatedate;

import WhenWhenBackEnd.dto.SimpleDateDTO2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMyPrivateScheduleResponseDTO {

    private String idToken;
    private List<SimpleDateDTO2> dates;

}
