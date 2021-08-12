package WhenWhen.dto.schedule;

import WhenWhen.dto.basic.SimpleDateDTO;
import lombok.Data;

import java.util.List;

@Data
public class SubmitMemberScheduleRequestDTO {

    private String scheduleKey;
    private String idToken;
    private List<SimpleDateDTO> dates;

}

