package WhenWhenBackEnd.dto.schedule;

import lombok.Data;

@Data
public class DeleteScheduleRequestDTO {

    private String hostIdToken;
    private String scheduleKey;

}
