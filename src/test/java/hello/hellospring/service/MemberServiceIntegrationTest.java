//package hello.hellospring.service;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import hello.hellospring.mappertest.domain.Member;
//import hello.hellospring.mappertest.service.MemberService;
//import hello.hellospring.repository.MemberRepository;
//import hello.hellospring.repository.MemoryMemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional // 실제 데이터를 반영시키지 않는다.
//public class MemberServiceIntegrationTest {
//
//    @Autowired
//    MemberService memberService;
//    @Autowired MemberRepository memberRepository;
//
//    @Test
//    void 회원가입() {
//
//        //given
//        Member member = new Member();
//        member.setName("kim");
//
//        //when
//        Long saveId = memberService.join(member);
//
//        //then
//        Member findMember = memberService.findOne(saveId).get();
//        assertThat(member.getName()).isEqualTo(findMember.getName());
//    }
//
//    @Test
//    public void 중복_회원_예외() {
//        // given
//        Member member1 = new Member();
//        member1.setName("spring");
//
//        Member member2 = new Member();
//        member2.setName("spring");
//        // when
//        memberService.join(member1);
//        IllegalStateException e =
//            assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//
//    }
//
//}
