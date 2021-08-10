package WhenWhen.dto.basic;

import lombok.Data;

@Data
public class SimpleScheduleDTO {

    private String ScheduleName;
    private String ScheduleKey;

    public SimpleScheduleDTO(String scheduleName, String scheduleKey) {
        ScheduleName = scheduleName;
        ScheduleKey = scheduleKey;
    }

}
