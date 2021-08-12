package WhenWhen.service;

import WhenWhen.domain.Member;
import WhenWhen.domain.MemberSchedule;
import WhenWhen.domain.Schedule;
import WhenWhen.repository.MemberScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberScheduleService {

    private final MemberScheduleRepository memberScheduleRepository;
    private final ScheduleService scheduleService;

    public void save(MemberSchedule memberSchedule) {
        Schedule schedule = scheduleService.findOne(memberSchedule.getSchedule().getScheduleKey());
        schedule.increaseJoinedMemberCnt();

        memberScheduleRepository.save(memberSchedule);
    }

    public MemberSchedule findByMemberAndSchedule(Member member, Schedule schedule) {
        MemberSchedule result = memberScheduleRepository.findByMemberAndSchedule(member, schedule);

        return result;
    }

    public void delete(MemberSchedule memberSchedule) {
        Schedule schedule = scheduleService.findOne(memberSchedule.getSchedule().getScheduleKey());
        schedule.decreaseJoinedMemberCnt();
        
        memberScheduleRepository.delete(memberSchedule);

    }

    public void deleteBySchedule(Schedule schedule) {
        memberScheduleRepository.deleteBySchedule(schedule);
    }

    public List<MemberSchedule> findBySchedule(Schedule schedule) {
        List<MemberSchedule> result = memberScheduleRepository.findBySchedule(schedule);

        return result;
    }

}
