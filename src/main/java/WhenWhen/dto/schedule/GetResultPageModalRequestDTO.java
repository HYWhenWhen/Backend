package WhenWhen.dto.schedule;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetResultPageModalRequestDTO {

    private String scheduleKey;
    private LocalDate date;

}
