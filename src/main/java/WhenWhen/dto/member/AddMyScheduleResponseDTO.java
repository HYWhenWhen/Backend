package WhenWhen.dto.member;

import lombok.Data;

@Data
public class AddMyScheduleResponseDTO {

    private Boolean success;

    public AddMyScheduleResponseDTO(Boolean success) {
        this.success = success;
    }

}
