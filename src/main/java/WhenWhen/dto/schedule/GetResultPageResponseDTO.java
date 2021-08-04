package WhenWhen.dto.schedule;

import WhenWhen.dto.basic.SimpleDateDTO2;
import lombok.Data;

import java.time.LocalDate;
import java.util.TreeMap;

@Data
public class GetResultPageResponseDTO {

    private Boolean success;
    private String scheduleName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long expectedMemberCnt;
    private Long joinedMemberCnt;

    TreeMap<LocalDate, SimpleDateDTO2> dates;

    public GetResultPageResponseDTO(Boolean success, String scheduleName, LocalDate startDate, LocalDate endDate, Long expectedMemberCnt,
                                    Long joinedMemberCnt, TreeMap<LocalDate, SimpleDateDTO2> dates) {
        if (success == true) {
            this.scheduleName = scheduleName;
            this.startDate = startDate;
            this.endDate = endDate;
            this.expectedMemberCnt = expectedMemberCnt;
            this.joinedMemberCnt = joinedMemberCnt;
            this.dates = dates;
        }

        this.success = success;
    }

}


