package br.com.welson.grades.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Admin extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
