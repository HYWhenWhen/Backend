package WhenWhenBackEnd.dto.privatedate;

import WhenWhenBackEnd.dto.SimpleDateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SetMyPrivateScheduleRequestDTO {

    private String idToken;
    private List<SimpleDateDTO> dates = new ArrayList<>(); // 미리 생성해놔야 한다 반드시!

}
