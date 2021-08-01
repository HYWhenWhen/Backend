package WhenWhenBackEnd.repository;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.MemberSchedule;
import WhenWhenBackEnd.domain.Schedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;

import static WhenWhenBackEnd.domain.QMemberSchedule.memberSchedule;

@Repository
public class MemberScheduleRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    // --------------------------------------------------------------------------- //

    @Autowired
    public MemberScheduleRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    // --------------------------------------------------------------------------- //

    public void save(MemberSchedule _memberSchedule) { em.persist(_memberSchedule); }

    public MemberSchedule findByMemberAndSchedule(Member _member, Schedule _schedule) {
        MemberSchedule result = queryFactory
                .select(memberSchedule)
                .from(memberSchedule)
                .where(
                        memberSchedule.member.eq(_member)
                                .and(memberSchedule.schedule.eq(_schedule))
                )
                .fetchOne();

        return result;
    }

    public Long deleteBySchedule(Schedule _schedule) {
        long execute = queryFactory
                .delete(memberSchedule)
                .where(memberSchedule.schedule.eq(_schedule))
                .execute();

        return execute;
    }

    public Long delete(MemberSchedule _memberSchedule) {
        long execute = queryFactory
                .delete(memberSchedule)
                .where(memberSchedule.eq(_memberSchedule))
                .execute();

        return execute;
    }

}
