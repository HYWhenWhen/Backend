package WhenWhenBackEnd.repository;

import WhenWhenBackEnd.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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

}
