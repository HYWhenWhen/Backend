package WhenWhenBackEnd.dto.schedule;

import WhenWhenBackEnd.dto.SimpleDateDTO2;
import WhenWhenBackEnd.dto.SimpleMemberInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetTotalPageResponseDTO {

    private String scheduleName;
    private String scheduleKey;
    private LocalDate startDate;
    private LocalDate endDate;

    List<LocalDate> bestDates;

    List<SimpleDateDTO2> dates;

    List<SimpleMemberInfoDTO> memberDates;

}


