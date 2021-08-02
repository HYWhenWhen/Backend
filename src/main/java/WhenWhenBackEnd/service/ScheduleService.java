package WhenWhenBackEnd.service;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Boolean save(Schedule schedule) {
        if(schedule.getExpectedMemberCnt() <= 0 || schedule.getStartDate().isAfter(schedule.getEndDate()) || schedule.getHost() == null)
            return false;

        scheduleRepository.save(schedule);
        return true;
    }

    public Schedule findOne(String scheduleKey) {
        Schedule findSchedule = scheduleRepository.findByScheduleKey(scheduleKey);

        return findSchedule;
    }

    public List<Schedule> findByMember(Member member) {
        List<Schedule> list = scheduleRepository.findByMember(member);

        return list;
    }

    public void delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

}
