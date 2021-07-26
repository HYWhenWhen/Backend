package WhenWhenBackEnd;

import WhenWhenBackEnd.domain.*;
import WhenWhenBackEnd.domain.Date;
import WhenWhenBackEnd.dto.SimpleScheduleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class test {

    @Test
    void Test() {
        get();
    }

    // List<date> list
    public List<Date> get() {
        LocalDate startDate = LocalDate.of(2017,03,01);
        LocalDate endDate = LocalDate.of(2017,03,10);
        List<Date> list = new ArrayList<>();

        Map<LocalDate, Map<Availability, Long>> map = new HashMap<>();
        Member member = new Member("asd123", "한영");
        Schedule schedule = new Schedule("멋사", member, startDate, endDate, 5L);
        MemberSchedule memberSchedule = new MemberSchedule(member, schedule);
        list.add(new Date(memberSchedule, endDate, Availability.POSSIBLE));

        for (Long i = 0L; !startDate.plusDays(i).equals(endDate.plusDays(1)); i++) {
            LocalDate localDate = startDate.plusDays(i);

            map.put(localDate, new HashMap<>());
            map.get(localDate).put(Availability.POSSIBLE, 0L);
            map.get(localDate).put(Availability.IMPOSSIBLE, 0L);
            map.get(localDate).put(Availability.ADJUSTABLE, 0L);
        }

        // 파라미터들을 알맞게 삽입
        for (Date date : list) {
            Map<Availability, Long> innerMap = map.get(date.getLocalDate());
            innerMap.put(date.getAvailability(), innerMap.get(date.getAvailability()) + 1);
        }

        // 날짜에 불가능 표시가 하나라도 있으면 그 날짜 삭제
        Iterator<Map.Entry<LocalDate, Map<Availability, Long>>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<LocalDate, Map<Availability, Long>> entry = iter.next();
            if(entry.getValue().get(Availability.IMPOSSIBLE) != 0L)iter.remove();
        }

        // POSSIBLE 내림차순, 날짜 오름차순으로 정렬 후 날짜만 추출하여 List로 변환.
        List<LocalDate> resultSet = map.entrySet().stream()
                .sorted(
                        Comparator.comparing((Map.Entry<LocalDate, Map<Availability, Long>> entry) -> entry.getValue().get(Availability.POSSIBLE)).reversed()
                                .thenComparing(entry -> entry.getKey())
                )
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

//        return resultSet;
        return new ArrayList<>();
    }


}
