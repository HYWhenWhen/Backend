package WhenWhen.dto.member;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddMyScheduleRequestDTO {

    private String idToken;
    private LocalDate localDate;
    private String ScheduleName;

}
