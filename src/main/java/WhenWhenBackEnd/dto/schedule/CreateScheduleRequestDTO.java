package WhenWhenBackEnd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleRequestDTO {

    private String name;
    private Long expectedMemberCnt;
    private String hostIdToken;
    private LocalDate startDate;
    private LocalDate endDate;

}
