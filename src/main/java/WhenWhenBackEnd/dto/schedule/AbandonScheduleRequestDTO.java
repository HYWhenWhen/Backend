package WhenWhenBackEnd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbandonScheduleRequestDTO {

    private String idToken;
    private String scheduleKey;

}