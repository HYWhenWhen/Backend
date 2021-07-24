package WhenWhenBackEnd.dto;

import WhenWhenBackEnd.domain.Availability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
// 반환할 때, 가능여부를 정수로 통일시켜 돌려줘야하기 때문에 만들었다.
public class SimpleDateDTO2 {

    LocalDate localDate;
    Integer availability;

}
