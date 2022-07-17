package com.example.n_1check.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Team {

    @Id @GeneratedValue
    Long id;

    String name;
}
