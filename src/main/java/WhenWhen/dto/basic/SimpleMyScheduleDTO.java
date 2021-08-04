package WhenWhen.dto.basic;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SimpleMyScheduleDTO {

    private LocalDate localDate;
    private Long myScheduleID;
    private String ScheduleName;

    public SimpleMyScheduleDTO(LocalDate localDate, Long myScheduleID, String scheduleName) {
        this.localDate = localDate;
        this.myScheduleID = myScheduleID;
        ScheduleName = scheduleName;
    }

}
