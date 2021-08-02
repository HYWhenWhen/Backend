package WhenWhenBackEnd.dto.basic;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SimpleScheduleDTO {

    private String ScheduleName;
    private LocalDate localDate;

    public SimpleScheduleDTO(String scheduleName, LocalDate localDate) {
        ScheduleName = scheduleName;
        this.localDate = localDate;
    }

}
