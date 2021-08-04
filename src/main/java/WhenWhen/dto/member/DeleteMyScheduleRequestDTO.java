package WhenWhen.dto.member;

import lombok.Data;

@Data
public class DeleteMyScheduleRequestDTO {

    private String idToken;
    private Long scheduleKey;

}
