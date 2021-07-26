package WhenWhenBackEnd.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetBestDayResponseDTO {

    String scheduleKey;
    List<LocalDate> dates;

}
