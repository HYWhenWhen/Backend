package WhenWhen.repository;

import WhenWhen.domain.Member;
import WhenWhen.domain.MemberSchedule;
import WhenWhen.domain.Schedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static WhenWhen.domain.QMemberSchedule.memberSchedule;

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

    public List<MemberSchedule> findBySchedule(Schedule _schedule) {
        List<MemberSchedule> result = queryFactory
                .select(memberSchedule)
                .from(memberSchedule)
                .where(
                        memberSchedule.schedule.eq(_schedule)
                )
                .fetch();

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
