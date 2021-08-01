package WhenWhenBackEnd.util;

import WhenWhenBackEnd.domain.Availability;
import WhenWhenBackEnd.domain.Date;
import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.dto.basic.SimpleDateDTO2;
import WhenWhenBackEnd.dto.basic.SimpleMemberDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class MyUtil {

    public static String getRandomString() {
        int left = 97; // 'a'
        int right = 122; // 'z'
        int length = 10;

        String charString = new Random().ints(left, right + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String timeString = String.valueOf(new java.util.Date().getTime());

        return charString + timeString;
    }


    public static TreeMap<LocalDate, SimpleDateDTO2> getResultDates(Schedule schedule, List<Date> dateList) {
        TreeMap<LocalDate, SimpleDateDTO2> map = new TreeMap<>();

        LocalDate startDate = schedule.getStartDate();
        LocalDate endDate = schedule.getEndDate();

        for (Long i = 0L; !startDate.plusDays(i).equals(endDate.plusDays(1)); i++) {
            LocalDate localDate = startDate.plusDays(i);

            map.put(localDate, new SimpleDateDTO2(Availability.POSSIBLE, 0L, 0L, 0L));
        }

        for (Date date : dateList) {
            LocalDate localDate = date.getLocalDate();
            Availability availability = date.getAvailability();
            SimpleDateDTO2 dto = map.get(localDate);

            switch (availability) {
                case POSSIBLE:
                    dto.addPossibleCnt();
                    break;
                case ADJUSTABLE:
                    dto.addAdjustableCnt();
                    break;
                case IMPOSSIBLE:
                    dto.addImpossibleCnt();
            }
        }

        for (SimpleDateDTO2 dto : map.values()) {
            if(dto.getImpossibleCnt() > 0)dto.setAvailability(Availability.IMPOSSIBLE);
            else if(dto.getAdjustableCnt() > 0)dto.setAvailability(Availability.ADJUSTABLE);
        }

        return map;
    }

    public static TreeMap<Availability, List<SimpleMemberDTO>> getResultAvailability(List<Date> dateList) {
        TreeMap<Availability, List<SimpleMemberDTO>> map = new TreeMap<>();

        map.put(Availability.POSSIBLE, new ArrayList<>());
        map.put(Availability.ADJUSTABLE, new ArrayList<>());
        map.put(Availability.IMPOSSIBLE, new ArrayList<>());

        for (Date date : dateList) {
            Availability availability = date.getAvailability();
            Member member = date.getMemberSchedule().getMember();

            map.get(availability).add(new SimpleMemberDTO(member.getIdToken(), member.getNickName()));
        }

        return map;
    }

}