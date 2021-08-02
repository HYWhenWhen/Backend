package WhenWhenBackEnd.dto.basic;

import WhenWhenBackEnd.domain.Availability;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SimpleDateDTO {

    private LocalDate localDate;
    private Availability availability;

}
