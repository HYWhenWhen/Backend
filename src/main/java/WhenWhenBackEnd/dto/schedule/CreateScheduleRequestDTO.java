package WhenWhenBackEnd.dto.schedule;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleRequestDTO {

    private String name;
    private Long expectedMemberCnt;
    private String hostIdToken;
    private LocalDate startDate;
    private LocalDate endDate;

}
