package WhenWhenBackEnd.dto.member;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GetMyScheduleResponseDTO {

    private List<LocalDate> dates;
    private Boolean Success;

    public GetMyScheduleResponseDTO(List<LocalDate> list, Boolean success) {
        if (success == true) {
            this.dates = list;
        }

        Success = success;
    }

}
