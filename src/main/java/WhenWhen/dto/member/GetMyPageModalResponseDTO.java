package WhenWhen.dto.member;

import WhenWhen.dto.basic.SimpleMyScheduleDTO2;
import lombok.Data;

import java.util.List;

@Data
public class GetMyPageModalResponseDTO {

    List<SimpleMyScheduleDTO2> myScheduleList;
    Boolean success;

    public GetMyPageModalResponseDTO(List<SimpleMyScheduleDTO2> myScheduleList, Boolean success) {
        if (success == true) {
            this.myScheduleList = myScheduleList;
        }

        this.success = success;
    }

}
