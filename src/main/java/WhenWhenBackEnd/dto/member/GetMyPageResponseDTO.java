package WhenWhenBackEnd.dto.member;

import WhenWhenBackEnd.dto.basic.SimpleMyScheduleDTO;
import WhenWhenBackEnd.dto.basic.SimpleScheduleDTO;
import lombok.Data;

import java.util.List;

@Data
public class GetMyPageResponseDTO {

    List<SimpleMyScheduleDTO> dates;
    List<SimpleScheduleDTO> schedules;
    Boolean success;

    public GetMyPageResponseDTO(List<SimpleMyScheduleDTO> dates, List<SimpleScheduleDTO> schedules, Boolean success) {
        if (success == true) {
            this.dates = dates;
            this.schedules = schedules;
        }

        this.success = success;
    }
}
