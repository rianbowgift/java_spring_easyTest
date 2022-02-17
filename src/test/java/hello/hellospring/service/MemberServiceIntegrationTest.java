package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional  //테스트를 실행할때 트렌잭션실행해서 다끝나면 롤백해준다(다지워짐)
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test

    void 회원가입() {
        //given //무언가가 주어젔는데
        Member member = new Member();
        member.setName("spring4");

        //when  //이걸실행했을때
        Long saveId = memberService.join(member);


        //then  //이것이 나와야함

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());


    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));


         assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        /*
        try{
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e){
            //정상성공
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
*/

        //then
    }


}