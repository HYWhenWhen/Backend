package WhenWhenBackEnd.repository;

import WhenWhenBackEnd.domain.MemberSchedule;
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

}
