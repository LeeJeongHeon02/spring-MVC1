package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    // 1. 테스트가 끝날때마다 리포지토리 클리어하기.
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
        System.out.println("MemberRepositoryTest.afterEach");
    }

    // TEST1. save - given, when, then 구조로 코드작성하기.
    // 멤버 객체 생성 후, 리포지토리에 저장하고, 리포지토리에서 ID로 찾아서
    // 찾은 값이랑 저장된 값이 같은지 비교하기.
    @Test
    void save() {
        // given
        Member member = new Member("JungHun", 24);

        // when
        Member savedMember = memberRepository.save(member);

        // then\
        Member findMember  = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);

        System.out.println("MemberRepositoryTest.save FINISHED");
    }


    // TEST2. findAll
    // 객체를 2개 만들어서 리포지토리에 저장하고
    // findAll 로 꺼내서, 원소의 개수가 2개인지 확인한다.
    // 그리고 result 안에 두 객체가 있는지 확인한다.
    @Test
    void findAll() {
        //given
        Member member1 = new Member("JungHun", 24);
        Member member2 = new Member("hamster", 1);

        //when
        Member save1 = memberRepository.save(member1);
        Member save2 = memberRepository.save(member2);
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members.size()).isEqualTo(2);
        assertThat(members).contains(save1, save2);

        System.out.println("MemberRepositoryTest.findAll FINISHED");
    }
    
}