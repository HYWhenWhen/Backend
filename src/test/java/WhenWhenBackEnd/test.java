package WhenWhenBackEnd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@SpringBootTest
public class test {
    private String createRandomString() {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'
        int targetStringLength = 10;

        // 문자열로 이루어진 랜덤 문자열 생성
        String generatedString = new Random().ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        // + 현재 시간을 문자열로 변환해서 추가
        return generatedString.concat(String.valueOf(new Date().getTime()));

    }
    @Test
    void A() {
        System.out.println(createRandomString());
    }
}
