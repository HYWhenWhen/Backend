package WhenWhenBackEnd.repository;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.MemberSchedule;
import WhenWhenBackEnd.domain.QMemberSchedule;
import WhenWhenBackEnd.domain.Schedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class MemberScheduleRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public MemberScheduleRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    public void save(MemberSchedule memberSchedule) {
        em.persist(memberSchedule);
    }

    public MemberSchedule findByMemberAndSchedule(Member member, Schedule schedule) {
        QMemberSchedule memberSchedule = QMemberSchedule.memberSchedule;

        MemberSchedule result = queryFactory
                .select(memberSchedule)
                .from(memberSchedule)
                .where(
                    memberSchedule.member.eq(member)
                    .and(memberSchedule.schedule.eq(schedule))
                )
                .fetchOne();

        return result;
    }

    public Long deleteBySchedule(Schedule schedule) {
        QMemberSchedule memberSchedule = QMemberSchedule.memberSchedule;

        long execute = queryFactory
                .delete(memberSchedule)
                .where(memberSchedule.schedule.eq(schedule))
                .execute();

        return execute;
    }

}
