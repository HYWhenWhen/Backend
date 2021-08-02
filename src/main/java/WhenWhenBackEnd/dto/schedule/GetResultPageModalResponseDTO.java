package WhenWhenBackEnd.dto.schedule;

import WhenWhenBackEnd.domain.Availability;
import WhenWhenBackEnd.dto.basic.SimpleMemberDTO;
import lombok.Data;

import java.util.List;
import java.util.TreeMap;

@Data
public class GetResultPageModalResponseDTO {

    private Boolean success;
    TreeMap<Availability, List<SimpleMemberDTO>> availability;


    public GetResultPageModalResponseDTO(Boolean success, TreeMap<Availability, List<SimpleMemberDTO>> availability) {
        if (success == true) {
            this.availability = availability;
        }

        this.success = success;
    }

}
