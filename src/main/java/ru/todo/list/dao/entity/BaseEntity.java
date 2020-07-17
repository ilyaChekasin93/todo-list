package ru.todo.list.dao.entity;

import lombok.Getter;

import javax.persistence.*;


@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
