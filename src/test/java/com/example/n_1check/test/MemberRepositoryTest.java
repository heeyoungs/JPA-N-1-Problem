package com.example.n_1check.test;

import com.example.n_1check.domain.entity.Member;
import com.example.n_1check.domain.entity.Team;
import com.example.n_1check.domain.repository.MemberRepository;
import com.example.n_1check.domain.repository.TeamRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @BeforeEach
    void beforeEach(){
        System.out.println("실행 전");
    }

    @AfterEach
    void afterEach(){
        System.out.println("실행 후");
    }


    @Test
    @DisplayName("N+1문제를 체크해보자")
    void teamSelect(){
        Team team1 = new Team();
        team1.setName("팀 1");
        Team saveTeam1 = teamRepository.save(team1);

        Team team2 = new Team();
        team2.setName("팀 2");
        Team saveTeam2 = teamRepository.save(team2);

        Member member1 = new Member();
        member1.setName("히영");
        member1.setTeam(saveTeam1);
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("영히");
        member2.setTeam(saveTeam2);
        memberRepository.save(member2);

        memberRepository.findAll().forEach(System.out::println);


//        Hibernate:
//        select
//        member0_.id as id1_0_,
//                member0_.name as name2_0_,
//        member0_.team_id as team_id3_0_
//                from
//        member member0_
    }
}
