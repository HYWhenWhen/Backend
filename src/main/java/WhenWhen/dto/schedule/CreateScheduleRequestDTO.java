package WhenWhen.dto.schedule;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateScheduleRequestDTO {

    private String scheduleName;
    private Long expectedMemberCnt;
    private String hostIdToken;
    private LocalDate startDate;
    private LocalDate endDate;

}
