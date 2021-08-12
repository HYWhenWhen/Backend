package WhenWhen.dto.member;

import WhenWhen.dto.basic.SimpleMyScheduleDTO;
import WhenWhen.dto.basic.SimpleScheduleDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GetMyPageResponseDTO {

    String idToken;
    String nickName;
    Boolean success;
    List<LocalDate> dates;
    List<SimpleScheduleDTO> schedules;

    public GetMyPageResponseDTO(Boolean success, String idToken, String nickName, List<LocalDate> dates, List<SimpleScheduleDTO> schedules) {
        if (success == true) {
            this.idToken = idToken;
            this.nickName = nickName;
            this.dates = dates;
            this.schedules = schedules;
        }

        this.success = success;
    }
}
