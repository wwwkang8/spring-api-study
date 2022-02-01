package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    // 추천 방식 - final을 사용 : @RequiredArgsConstructor에서 생성자롤 호출해서 생성
    private final MemberRepository memberRepository;

//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member); // 중복회원 검증

        memberRepository.save(member);
        return member.getId();

    }


    // 회원 전체조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 회원 단건조회
    public Member findOne(Member member){
        return memberRepository.findOne(member.getId());
    }



    //증복 검증
    public void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

    }




}
