package com.example.n_1check.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Member {

    @Id @GeneratedValue
    Long id;

    String name;

    @ManyToOne
    Team team;
}
