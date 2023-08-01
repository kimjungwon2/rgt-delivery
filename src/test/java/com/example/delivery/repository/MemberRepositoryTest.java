package com.example.delivery.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.delivery.domain.Member;
import com.example.delivery.domain.Role;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("이메일로 멤버를 찾는다.")
    @Test
    void findMemberByEmail(){
        //given
        Member member1 = Member.builder()
                .email("a@a.com")
                .name("김순자")
                .picture("1asdx2")
                .role(Role.GUEST)
                .build();

        Member member2 = Member.builder()
                .email("a32@naver.com")
                .name("김팔순")
                .picture("1a22")
                .role(Role.GUEST)
                .build();

        Member member3 = Member.builder()
                .email("a322@google.com")
                .name("허필두")
                .picture("1a22c12")
                .role(Role.USER)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //when
        Member foundMember = memberRepository.findByEmail("a322@google.com").orElseThrow();

        //then
        assertThat(foundMember.getEmail()).isEqualTo(member3.getEmail());
        assertThat(foundMember.getName()).isEqualTo(member3.getName());
        assertThat(foundMember.getPicture()).isEqualTo(member3.getPicture());
        assertThat(foundMember.getRole()).isEqualTo(member3.getRole());
    }

}