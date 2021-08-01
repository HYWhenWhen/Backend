package WhenWhenBackEnd.dto.member;

import lombok.Data;

@Data
public class CreateMemberResponseDTO {

    private Boolean success;

    public CreateMemberResponseDTO(Boolean success) {
        this.success = success;
    }

}