package WhenWhenBackEnd.dto.schedule;

import WhenWhenBackEnd.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetResultResponseDTO {
    List<Member> memberNames;
}
