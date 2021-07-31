package WhenWhenBackEnd.logic;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.Schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetDatesOfRange {

    public static List<LocalDate> get(Schedule schedule){
        LocalDate startDate = schedule.getStartDate();
        LocalDate endDate = schedule.getEndDate();

        List<LocalDate> listOfDates = startDate.datesUntil(endDate)
                .collect(Collectors.toList());

        System.out.println(listOfDates.size());
        return listOfDates;
    }
}
