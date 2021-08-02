package WhenWhenBackEnd.dto.member;

import WhenWhenBackEnd.dto.basic.SimpleScheduleDTO;
import WhenWhenBackEnd.dto.basic.SimpleScheduleDTO2;
import lombok.Data;

import java.util.List;

@Data
public class GetMyPageResponseDTO {

    List<SimpleScheduleDTO> dates;
    List<SimpleScheduleDTO2> schedules;
    Boolean success;

    public GetMyPageResponseDTO(List<SimpleScheduleDTO> dates, List<SimpleScheduleDTO2> schedules, Boolean success) {
        if (success == true) {
            this.dates = dates;
            this.schedules = schedules;
        }

        this.success = success;
    }
}
