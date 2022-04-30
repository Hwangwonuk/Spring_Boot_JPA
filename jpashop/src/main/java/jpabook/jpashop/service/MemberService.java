package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 자동으로 스프링 빈 등록
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 들어간 필드의 생성자만 만들어줌
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired 생략가능 @RequiredArgsConstructor 로 생성자 만들었기 때문에 주석
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional // 쓰기에는 readOnly=true를 사용하면 값변경이 안됨 기본값 false로 설정
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 중복이름이 있을 시 EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
//    @Transactional(readOnly = true) // 읽기에는 readOnly = true
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
//    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
