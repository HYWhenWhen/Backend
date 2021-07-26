package WhenWhenBackEnd.logic;

import WhenWhenBackEnd.domain.Availability;
import WhenWhenBackEnd.domain.Date;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.dto.SimpleDateDTO2;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ExtractTotalDate {

    public static List<SimpleDateDTO2> get(Schedule schedule, List<Date> dateList) {
        LocalDate startDate = schedule.getStartDate();
        LocalDate endDate = schedule.getEndDate();

        Map<LocalDate, Map<Availability, Long>> map = new HashMap<>();

        for (Long i = 0L; !startDate.plusDays(i).equals(endDate.plusDays(1)); i++) {
            LocalDate localDate = startDate.plusDays(i);

            map.put(localDate, new HashMap<>());
            map.get(localDate).put(Availability.POSSIBLE, 0L);
            map.get(localDate).put(Availability.IMPOSSIBLE, 0L);
            map.get(localDate).put(Availability.ADJUSTABLE, 0L);
        }

        // 파라미터들을 알맞게 삽입
        for (Date date : dateList) {
            Map<Availability, Long> innerMap = map.get(date.getLocalDate());
            innerMap.put(date.getAvailability(), innerMap.get(date.getAvailability()) + 1);
        }

        List<SimpleDateDTO2> resultSet = map.entrySet().stream()
                .map(entry -> new SimpleDateDTO2(entry.getKey(),
                        entry.getValue().get(Availability.IMPOSSIBLE) != 0L ? 1 : (entry.getValue().get(Availability.ADJUSTABLE) != 0 ? 2 : 0)
                ))
                .collect(Collectors.toList());

        return resultSet;
    }

}
