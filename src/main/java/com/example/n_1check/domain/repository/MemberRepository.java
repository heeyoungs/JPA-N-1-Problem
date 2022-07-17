package com.example.n_1check.domain.repository;

import com.example.n_1check.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member,Long> {

}
