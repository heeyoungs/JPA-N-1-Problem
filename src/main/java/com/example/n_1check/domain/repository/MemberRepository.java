package com.example.n_1check.domain.repository;

import com.example.n_1check.domain.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph("MemberWithTeam")
    List<Member> findAll();
}
