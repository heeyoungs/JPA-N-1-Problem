package com.example.n_1check.web;

import com.example.n_1check.domain.entity.Member;
import com.example.n_1check.domain.repository.MemberRepository;
import com.example.n_1check.web.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/findAll")
    @ResponseBody
    public List<MemberDto> memberList(){
        System.out.println("findAll");
                return memberRepository.findAll().stream().map(m -> new MemberDto(m.getId(),m.getName()))
                .collect(Collectors.toList());
    }
}
//Hibernate:
//        select
//        member0_.id as id1_0_,
//        member0_.name as name2_0_,
//        member0_.team_id as team_id3_0_
//        from
//        member member0_
//
//Hibernate:
//        select
//        team0_.id as id1_1_0_,
//        team0_.name as name2_1_0_
//        from
//        team team0_
//        where
//        team0_.id=?
//
//Hibernate:
//        select
//        team0_.id as id1_1_0_,
//        team0_.name as name2_1_0_
//        from
//        team team0_
//        where
//        team0_.id=?