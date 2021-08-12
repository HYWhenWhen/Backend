package WhenWhen.repository;

import WhenWhen.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static WhenWhen.domain.QMember.member;

@Repository
public class MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    // --------------------------------------------------------------------------- //

    @Autowired
    public MemberRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    // --------------------------------------------------------------------------- //

    public void save(Member _member) { em.persist(_member); }

    public Member findByIdToken(String _idToken) {
        Member result = queryFactory
                .select(member)
                .from(member)
                .where(member.idToken.eq(_idToken))
                .fetchOne();

        return result;
    }

}
