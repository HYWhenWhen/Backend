package WhenWhenBackEnd.dto;

import WhenWhenBackEnd.domain.Availability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleDateDTO {

    LocalDate localDate;
    Availability availability;

}