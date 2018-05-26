package br.com.welson.grades.persistence.model;

public enum Type {

    HUMAN("Human"), EXACT("Exact");

    private String type;

    Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
