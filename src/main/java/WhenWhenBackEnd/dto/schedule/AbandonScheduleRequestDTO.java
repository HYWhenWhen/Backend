package WhenWhenBackEnd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbandonScheduleRequestDTO {
    String idToken;
    String scheduleKey;
}