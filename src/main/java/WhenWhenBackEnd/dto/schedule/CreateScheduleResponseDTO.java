package WhenWhenBackEnd.dto.schedule;

import WhenWhenBackEnd.domain.Schedule;
import lombok.Data;

@Data
public class CreateScheduleResponseDTO {

    private String scheduleKey;
    private Boolean success;

    public CreateScheduleResponseDTO(Schedule schedule, Boolean success) {
        if (success == true) {
            this.scheduleKey = schedule.getScheduleKey();
        }

        this.success = success;
    }
}
