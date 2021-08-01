package WhenWhenBackEnd.dto.basic;

import lombok.Data;

@Data
public class SimpleScheduleDTO2 {

    private String ScheduleName;
    private String ScheduleKey;

    public SimpleScheduleDTO2(String scheduleName, String scheduleKey) {
        ScheduleName = scheduleName;
        ScheduleKey = scheduleKey;
    }

}
