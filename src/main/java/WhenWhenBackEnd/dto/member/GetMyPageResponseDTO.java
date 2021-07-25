package WhenWhenBackEnd.dto.member;

import WhenWhenBackEnd.dto.SimpleDateDTO;
import WhenWhenBackEnd.dto.SimpleDateDTO2;
import WhenWhenBackEnd.dto.SimpleScheduleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMyPageResponseDTO {

    private String idToken;
    private String nickName;

    List<SimpleDateDTO2> dates;
    List<SimpleScheduleDTO> schedules;

}
