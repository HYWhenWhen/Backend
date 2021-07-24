package WhenWhenBackEnd.dto.schedule;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleResponseDTO {

    private String name;
    private String scheduleKey;

}
