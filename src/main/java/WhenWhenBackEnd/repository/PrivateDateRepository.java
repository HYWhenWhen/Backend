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
public class PrivateDateRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public PrivateDateRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    public void save(PrivateDate privateDate) {
        em.persist(privateDate);
    }

    public List<PrivateDate> findByMember(Member member) {
        QPrivateDate privateDate = QPrivateDate.privateDate;

        List<PrivateDate> result = queryFactory
                .select(privateDate)
                .from(privateDate)
                .where(privateDate.member.eq(member))
                .fetch();

        return result;
    }

}
