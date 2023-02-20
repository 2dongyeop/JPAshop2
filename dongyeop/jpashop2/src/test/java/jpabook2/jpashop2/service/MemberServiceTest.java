package jpabook2.jpashop2.service;

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest  {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given -- 조건
        Member member = new Member();
        member.setName("kim");

        //when -- 동작
        Long saveId = memberService.join(member);

        //then -- 검증
        Assert.assertEquals(member, memberRepository.findOne(saveId));
//        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(saveId));
    }
    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given -- 조건
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when -- 동작
        memberService.join(member1);
        memberService.join(member2); //이름이 같은 회원이 회원가입! 예외 발생

        //then -- 검증
        fail("예외가 발생해야 한다.");
    }
}