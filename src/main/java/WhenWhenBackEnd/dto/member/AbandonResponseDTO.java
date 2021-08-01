package WhenWhenBackEnd.dto.member;

import lombok.Data;

@Data
public class AbandonResponseDTO {

    Boolean success;

    public AbandonResponseDTO(Boolean success) {
        this.success = success;
    }

}
