package WhenWhenBackEnd.repository;

import WhenWhenBackEnd.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class DateRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public DateRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    public void save(Date date) {
        em.persist(date);
    }

    public Long deleteByMemberSchedule(MemberSchedule memberSchedule) {
        QDate date = QDate.date;

        Long execute = queryFactory
                .delete(date)
                .where(date.memberSchedule.eq(memberSchedule))
                .execute();

        return execute;
    }

    public List<Date> findBySchedule(Schedule schedule) {
        QDate date = QDate.date;
        QMemberSchedule memberSchedule = QMemberSchedule.memberSchedule;

        List<Date> result = queryFactory
                .select(date)
                .from(date)
                .join(date.memberSchedule, memberSchedule)
                .on(memberSchedule.schedule.eq(schedule))
                .fetch();

        return result;
    }

    public List<Date> findByMember(Member param_member) {
        QDate date = QDate.date;
        QMemberSchedule memberSchedule = QMemberSchedule.memberSchedule;

        List<Date> result = queryFactory
                .select(date)
                .from(date)
                .join(date.memberSchedule, memberSchedule)
                .on(memberSchedule.member.eq(param_member))
                .fetch();

        return result;
    }

    public Long deleteBySchedule(Schedule param_schedule) {
        QDate date = QDate.date;
        QMemberSchedule memberSchedule = QMemberSchedule.memberSchedule;
        QSchedule schedule = QSchedule.schedule;

        List<MemberSchedule> list = queryFactory
                .select(memberSchedule)
                .from(memberSchedule)
                .where(memberSchedule.schedule.eq(param_schedule))
                .fetch();

        long execute = queryFactory
                .delete(date)
                .where(
                        date.memberSchedule.in(list)
                )
                .execute();

        return execute;
    }
//    public Availability findAvailability(Date param_date, MemberSchedule param_memberSchedule){
//        QDate date = QDate.date;
//        QMemberSchedule memberSchedule = QMemberSchedule.memberSchedule;
//
//        Availability availability = queryFactory
//                .select(date.availability)
//                .from(date)
//                .where(
//                        date.memberSchedule.eq(param_memberSchedule)
//                                .and(date.localDate.eq(param_date.getLocalDate()))
//                ).fetchOne();
//
//        return availability;
//    }

}
