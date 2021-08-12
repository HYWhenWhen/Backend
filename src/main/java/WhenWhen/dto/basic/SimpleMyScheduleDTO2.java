package WhenWhen.dto.basic;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SimpleMyScheduleDTO2 {

    private Long myScheduleID;
    private String myScheduleName;

    public SimpleMyScheduleDTO2(Long myScheduleID, String myScheduleName) {
        this.myScheduleID = myScheduleID;
        this.myScheduleName = myScheduleName;
    }

}
