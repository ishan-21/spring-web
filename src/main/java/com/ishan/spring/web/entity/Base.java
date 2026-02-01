package com.ishan.spring.web.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class Base {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

}
