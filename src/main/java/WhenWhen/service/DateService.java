package WhenWhen.service;

import WhenWhen.domain.Date;
import WhenWhen.domain.MemberSchedule;
import WhenWhen.domain.Schedule;
import WhenWhen.repository.DateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DateService {

    private final DateRepository dateRepository;

    public void save(Date date) {
        dateRepository.save(date);
    }

    public void saveAll(List<Date> dateList) {
        for (Date date : dateList) {
            dateRepository.save(date);
        }
    }

    public void deleteByMemberSchedule(MemberSchedule memberSchedule) {
        dateRepository.deleteByMemberSchedule(memberSchedule);
    }

    public void deleteBySchedule(Schedule schedule) {
        dateRepository.deleteBySchedule(schedule);
    }

    public List<Date> findBySchedule(Schedule schedule) {
        List<Date> result = dateRepository.findBySchedule(schedule);

        return result;
    }

    public List<Date> findByScheduleAndDateWithMembers(Schedule schedule, LocalDate localDate) {
        List<Date> result = dateRepository.findByScheduleAndDateWithMembers(schedule, localDate);

        return result;
    }

}