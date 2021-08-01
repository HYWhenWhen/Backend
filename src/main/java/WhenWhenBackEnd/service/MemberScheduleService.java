package WhenWhenBackEnd.service;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.MemberSchedule;
import WhenWhenBackEnd.domain.Schedule;
import WhenWhenBackEnd.repository.MemberScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberScheduleService {

    private final MemberScheduleRepository memberScheduleRepository;

    public void save(MemberSchedule memberSchedule) {
        memberSchedule.getSchedule().increaseJoinedMemberCnt();

        memberScheduleRepository.save(memberSchedule);
    }

    public MemberSchedule findByMemberAndSchedule(Member member, Schedule schedule) {
        MemberSchedule result = memberScheduleRepository.findByMemberAndSchedule(member, schedule);

        return result;
    }

    public void delete(MemberSchedule memberSchedule) {
        memberScheduleRepository.delete(memberSchedule);
    }

    public void deleteBySchedule(Schedule schedule) {
        memberScheduleRepository.deleteBySchedule(schedule);
    }

}
