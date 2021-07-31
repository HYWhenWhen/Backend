package WhenWhenBackEnd.dto.schedule;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.dto.SimpleDateDTO2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetResultResponseDTO {
    List<LocalDate> dates;
    List<SimpleDateDTO2> result;
    List<List<SimpleDateDTO3>> result_of_dates;
}
