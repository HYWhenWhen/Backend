package WhenWhenBackEnd.logic;

import WhenWhenBackEnd.domain.*;
import WhenWhenBackEnd.domain.Date;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DetermineBestDate {

    public static List<LocalDate> get(Schedule schedule, List<Date> dateList) {
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

        // 날짜에 불가능 표시가 하나라도 있으면 그 날짜 삭제
        Iterator<Map.Entry<LocalDate, Map<Availability, Long>>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<LocalDate, Map<Availability, Long>> entry = iter.next();
            if(entry.getValue().get(Availability.IMPOSSIBLE) != 0L)iter.remove();
        }

        // POSSIBLE 내림차순, 날짜 오름차순으로 정렬 후 날짜만 추출하여 List 로 변환.
        List<LocalDate> resultSet = map.entrySet().stream()
                .sorted(
                        Comparator.comparing((Map.Entry<LocalDate, Map<Availability, Long>> entry) -> entry.getValue().get(Availability.POSSIBLE)).reversed()
                                .thenComparing(entry -> entry.getKey())
                )
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        return resultSet;
    }

}
