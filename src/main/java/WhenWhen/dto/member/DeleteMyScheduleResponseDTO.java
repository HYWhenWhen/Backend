package WhenWhen.dto.member;

import lombok.Data;

@Data
public class DeleteMyScheduleResponseDTO {

    private Boolean success;

    public DeleteMyScheduleResponseDTO(Boolean success) {
        this.success = success;
    }

}
