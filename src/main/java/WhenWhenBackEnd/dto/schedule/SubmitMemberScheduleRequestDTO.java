package WhenWhenBackEnd.dto.schedule;

import WhenWhenBackEnd.dto.basic.SimpleDateDTO;
import lombok.Data;

import java.util.List;

@Data
public class SubmitMemberScheduleRequestDTO {

    private String scheduleKey;
    private String idToken;
    private List<SimpleDateDTO> dates;

}

