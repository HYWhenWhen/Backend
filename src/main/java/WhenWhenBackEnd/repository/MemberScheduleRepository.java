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

    public List<Long> findByMemberIdAndScheduleID(String idToken, String scheduleKey) {
        QMemberSchedule memberSchedule = QMemberSchedule.memberSchedule;

        List<Long> findMemberSchedule = queryFactory
                .select(memberSchedule.id)
                .from(memberSchedule)
                .where(memberSchedule.member.idToken.eq(idToken))
                .where(memberSchedule.schedule.scheduleKey.eq(scheduleKey))
                .fetch();

        return findMemberSchedule;
    }

}
