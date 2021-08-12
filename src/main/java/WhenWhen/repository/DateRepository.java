package WhenWhen.repository;

import WhenWhen.domain.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static WhenWhen.domain.QDate.*;
import static WhenWhen.domain.QDate.date;
import static WhenWhen.domain.QMember.*;
import static WhenWhen.domain.QMember.member;
import static WhenWhen.domain.QMemberSchedule.memberSchedule;

@Repository
public class DateRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    // --------------------------------------------------------------------------- //

    @Autowired
    public DateRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    // --------------------------------------------------------------------------- //

    public void save(Date _date) {
        em.persist(_date);
    }

    public Long deleteByMemberSchedule(MemberSchedule _memberSchedule) {
        Long execute = queryFactory
                .delete(date)
                .where(date.memberSchedule.eq(memberSchedule))
                .execute();

        return execute;
    }

    public Long deleteBySchedule(Schedule _schedule) {
        QMemberSchedule memberScheduleSub = new QMemberSchedule("memberScheduleSub");

        long execute = queryFactory
                .delete(date)
                .where(
                        date.memberSchedule.in(
                                JPAExpressions
                                        .select(memberScheduleSub)
                                        .from(memberScheduleSub)
                                        .where(memberScheduleSub.schedule.eq(_schedule))
                        )
                )
                .execute();

        return execute;
    }

    public List<Date> findBySchedule(Schedule _schedule) {
        List<Date> result = queryFactory
                .select(date)
                .from(date)
                .join(date.memberSchedule, memberSchedule)
                .on(memberSchedule.schedule.eq(_schedule))
                .fetch();

        return result;
    }

    public List<Date> findByScheduleAndDateWithMembers(Schedule _schedule, LocalDate _localDate) {
        List<Date> result = queryFactory
                .select(date)
                .from(date)
                .join(date.memberSchedule, memberSchedule)
                .on(memberSchedule.schedule.eq(_schedule))
                .join(memberSchedule.member, member)
                .where(date.localDate.eq(_localDate))
                .fetch();

        return result;
    }

}