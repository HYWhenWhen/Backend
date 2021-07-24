package WhenWhenBackEnd.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetSubmitPageRequestDTO {
    String scheduleKey;
    String hostIdToken;

    public GetSubmitPageRequestDTO(String scheduleKey, String hostIdToken) {
        this.scheduleKey = scheduleKey;
        this.hostIdToken = hostIdToken;
    }
}


