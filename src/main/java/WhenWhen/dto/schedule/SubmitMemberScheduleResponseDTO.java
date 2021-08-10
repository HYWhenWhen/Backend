package WhenWhen.dto.schedule;

import lombok.Data;

@Data
public class SubmitMemberScheduleResponseDTO {

    private Boolean success;

    public SubmitMemberScheduleResponseDTO(Boolean success) {
        this.success = success;
    }

}

