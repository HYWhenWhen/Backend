package WhenWhenBackEnd.dto.member;

import WhenWhenBackEnd.dto.basic.SimpleScheduleDTO;
import lombok.Data;

import java.util.List;

@Data
public class AddMyScheduleRequestDTO {

    private String idToken;
    private List<SimpleScheduleDTO> dates;

}
