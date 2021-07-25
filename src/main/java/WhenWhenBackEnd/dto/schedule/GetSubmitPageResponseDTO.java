package WhenWhenBackEnd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetSubmitPageResponseDTO {

    private String scheduleName;
    private String scheduleKey;
    private LocalDate startDate;
    private LocalDate EndDate;

}
