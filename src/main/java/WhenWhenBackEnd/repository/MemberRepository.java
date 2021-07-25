package WhenWhenBackEnd.repository;

import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;


@Repository
@Transactional
public class MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public MemberRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    public void save(Member member) { em.persist(member); }

    public Member findByIdToken(String idToken) {
        QMember member = QMember.member;

        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.idToken.eq(idToken))
                .fetchOne();

        return findMember;
    }

}
