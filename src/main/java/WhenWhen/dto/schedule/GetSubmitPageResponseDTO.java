package WhenWhen.dto.schedule;

import WhenWhen.domain.Schedule;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetSubmitPageResponseDTO {

    private String scheduleName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean success;

    public GetSubmitPageResponseDTO(Schedule schedule, Boolean success) {
        if (success == true) {
            this.scheduleName = schedule.getName();
            this.startDate = schedule.getStartDate();
            this.endDate = schedule.getEndDate();
        }

        this.success = success;
    }

}
