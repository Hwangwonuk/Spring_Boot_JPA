package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit 실행할 때 스프링과 함께 실행
@SpringBootTest // 스프링부트를 띄운 상태로 테스트
@Transactional // 트랜잭션 걸고 테스트 후 롤백해버림 기본적으로 실제 서비스에는 롤백하지 않음 테스트에서만 롤백
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void  회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long saveId = memberService.join(member);

        // then
//        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
//        try {
        memberService.join(member2); // 예외가 발생해야 한다!!!
/*        } catch (IllegalStateException e) {
            return;
        }*/
        // then
        fail("예외가 발생해야 한다.");
    }
}