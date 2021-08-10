package WhenWhen.dto.basic;

import lombok.Data;

@Data
public class SimpleDataDTO3 {

    private String nickName;
    private Long availabilityToLong;

    public SimpleDataDTO3(String nickName, Long availabilityToLong) {
        this.nickName = nickName;
        this.availabilityToLong = availabilityToLong;
    }

}
