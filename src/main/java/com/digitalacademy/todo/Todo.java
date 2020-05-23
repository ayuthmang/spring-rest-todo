package com.digitalacademy.todo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Todo {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private boolean completed;

    public Todo() {
    }

    public Todo(String text, boolean completed) {
        this.text = text;
        this.completed = completed;
    }
}

