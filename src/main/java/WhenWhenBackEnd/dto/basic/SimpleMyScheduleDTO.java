package WhenWhenBackEnd.dto.basic;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SimpleMyScheduleDTO {

    private LocalDate localDate;
    private Long scheduleKey;
    private String ScheduleName;

    public SimpleMyScheduleDTO(LocalDate localDate, Long scheduleKey, String scheduleName) {
        this.localDate = localDate;
        this.scheduleKey = scheduleKey;
        ScheduleName = scheduleName;
    }

}
