package WhenWhen.dto.schedule;

import lombok.Data;

@Data
public class DeleteScheduleResponseDTO {

    Boolean success;

    public DeleteScheduleResponseDTO(Boolean success) {
        this.success = success;
    }

}
