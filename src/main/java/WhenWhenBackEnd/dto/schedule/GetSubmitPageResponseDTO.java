package WhenWhenBackEnd.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GetSubmitPageResponseDTO {
    String name;
    String scheduleKey;
    LocalDate startDate;
    LocalDate EndDate;

    public GetSubmitPageResponseDTO(String name, String scheduleKey, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.scheduleKey = scheduleKey;
        this.startDate = startDate;
        EndDate = endDate;
    }
}
