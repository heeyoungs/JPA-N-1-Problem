package com.example.n_1check.domain.repository;

import com.example.n_1check.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {

    //    @EntityGraph("MemberWithTeam")
    @Query("select m from Member m join fetch m.team")
    List<Member> findAll();
}
