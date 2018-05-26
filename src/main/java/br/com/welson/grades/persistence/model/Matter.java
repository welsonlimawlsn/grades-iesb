package br.com.welson.grades.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;

@Entity
public class Matter extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @Enumerated(STRING)
    @Column(nullable = false)
    private Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
