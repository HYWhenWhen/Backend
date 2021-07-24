package WhenWhenBackEnd.repository;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.QMember;
import WhenWhenBackEnd.domain.QSchedule;
import WhenWhenBackEnd.domain.Schedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class ScheduleRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ScheduleRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    public void save(Schedule schedule) {
        em.persist(schedule);
    }

    public Schedule findByScheduleKey(String scheduleKey) {
        QSchedule schedule = QSchedule.schedule;

        Schedule findSchedule = queryFactory
                .select(schedule)
                .from(schedule)
                .where(schedule.scheduleKey.eq(scheduleKey))
                .fetchOne();

        return findSchedule;
    }

}
