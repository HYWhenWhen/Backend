package WhenWhenBackEnd.logic;

import WhenWhenBackEnd.domain.Availability;
import WhenWhenBackEnd.domain.Date;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.dto.SimpleDateDTO2;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExtractResultDate {
    public static List<SimpleDateDTO2> get(Schedule schedule, List<Date> dateList) {
        LocalDate startDate = schedule.getStartDate();
        LocalDate endDate = schedule.getEndDate();

        Map<LocalDate, Availability> map = new HashMap<>();

        for (Long i = 0L; !startDate.plusDays(i).equals(endDate.plusDays(1)); i++) {
            LocalDate localDate = startDate.plusDays(i);

            map.put(localDate, Availability.POSSIBLE);
        }

        for (Date date : dateList) {
            Availability resultAvailability;
            Availability mapAvailability = map.get(date.getLocalDate());
            Availability dateAvailability = date.getAvailability();

            if (mapAvailability == Availability.IMPOSSIBLE || dateAvailability == Availability.IMPOSSIBLE) {
                resultAvailability = Availability.IMPOSSIBLE;
            } else if (mapAvailability == Availability.ADJUSTABLE || dateAvailability == Availability.ADJUSTABLE) {
                resultAvailability = Availability.ADJUSTABLE;
            } else {
                resultAvailability = Availability.POSSIBLE;
            }
            map.put(date.getLocalDate(), resultAvailability);
        }

        List<SimpleDateDTO2> result_list = map.entrySet().stream()
                .map(entry -> new SimpleDateDTO2(entry.getKey(), entry.getValue().ordinal())
                )
                .collect(Collectors.toList());

        result_list.sort(new Comparator<SimpleDateDTO2>() {
            @Override
            public int compare(SimpleDateDTO2 arg0, SimpleDateDTO2 arg1) {
                LocalDate localDate0 = arg0.getLocalDate();
                LocalDate localDate1 = arg1.getLocalDate();

                if (localDate0 == localDate1) return 0;
                else if (localDate0.isAfter(localDate1)) return 1;
                else return -1;
            }
        });
        return result_list;
    }
}
