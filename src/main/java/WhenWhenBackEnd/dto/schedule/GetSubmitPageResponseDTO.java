package WhenWhenBackEnd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetSubmitPageResponseDTO {
    String name;
    String scheduleKey;
    LocalDate startDate;
    LocalDate EndDate;
}
