package WhenWhen.repository;

import WhenWhen.domain.Member;
import WhenWhen.domain.Schedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static WhenWhen.domain.QMemberSchedule.memberSchedule;
import static WhenWhen.domain.QSchedule.*;
import static WhenWhen.domain.QSchedule.schedule;

@Repository
public class ScheduleRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    // --------------------------------------------------------------------------- //

    @Autowired
    public ScheduleRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    // --------------------------------------------------------------------------- //

    public void save(Schedule _schedule) { em.persist(_schedule); }

    public Schedule findById(Long _id) {
        Schedule result = queryFactory
                .select(schedule)
                .from(schedule)
                .where(schedule.id.eq(_id))
                .fetchOne();

        return result;
    }

    public Schedule findByScheduleKey(String _scheduleKey) {
        Schedule result = queryFactory
                .select(schedule)
                .from(schedule)
                .where(schedule.scheduleKey.eq(_scheduleKey))
                .fetchOne();

        return result;
    }

    public List<Schedule> findByMember(Member _member) {
        List<Schedule> result = queryFactory
                .select(schedule)
                .from(memberSchedule)
                .join(memberSchedule.schedule, schedule)
                .where(memberSchedule.member.eq(_member))
                .fetch();

        return result;
    }

    public Long delete(Schedule _schedule) {
        long execute = queryFactory
                .delete(schedule)
                .where(schedule.eq(_schedule))
                .execute();

        return execute;
    }

}
