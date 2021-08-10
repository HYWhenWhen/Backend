package WhenWhen.dto.member;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetMyPageModalRequestDTO {

    private String idToken;
    private LocalDate localDate;

}
