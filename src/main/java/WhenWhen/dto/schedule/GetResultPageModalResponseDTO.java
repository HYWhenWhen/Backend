package WhenWhen.dto.schedule;


import WhenWhen.dto.basic.SimpleDataDTO3;
import lombok.Data;

import java.util.List;

@Data
public class GetResultPageModalResponseDTO {

    private Boolean success;
    private List<SimpleDataDTO3> availability;

    public GetResultPageModalResponseDTO(Boolean success, List<SimpleDataDTO3> availability) {
        if (success == true) {
            this.availability = availability;
        }

        this.success = success;
    }

}
