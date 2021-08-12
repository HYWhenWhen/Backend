package WhenWhen.repository;

import WhenWhen.domain.Member;
import WhenWhen.domain.PrivateDate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static WhenWhen.domain.QPrivateDate.privateDate;

@Repository
public class PrivateDateRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    // --------------------------------------------------------------------------- //

    @Autowired
    public PrivateDateRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    // --------------------------------------------------------------------------- //

    public void save(PrivateDate _privateDate) {
        em.persist(_privateDate);
    }

    public PrivateDate findById(Long id) {
        PrivateDate privateDate = em.find(PrivateDate.class, id);

        return privateDate;
    }

    public List<PrivateDate> findByMember(Member _member) {
        List<PrivateDate> result = queryFactory
                .select(privateDate)
                .from(privateDate)
                .where(privateDate.member.eq(_member))
                .fetch();

        return result;
    }

    public void delete(PrivateDate privateDate) {
        em.remove(privateDate);
    }

}
