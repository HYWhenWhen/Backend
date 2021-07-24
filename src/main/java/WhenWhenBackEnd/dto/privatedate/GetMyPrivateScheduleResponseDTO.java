package WhenWhenBackEnd.dto.privatedate;

import WhenWhenBackEnd.dto.SimpleDateDTO;
import WhenWhenBackEnd.dto.SimpleDateDTO2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMyPrivateScheduleResponseDTO {

    private String idToken;
    private List<SimpleDateDTO2> dates = new ArrayList<>(); // 미리 생성해놔야 한다 반드시!

}
