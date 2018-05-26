package br.com.welson.grades.persistence.model;

public enum Mention {

    SS("Superior"), MS("Médio Superior"), MM("Médio"), MI("Médio Inferior"), II("Inferior"), SR("Sem Rendimento");

    private String s;

    Mention(String s) {
        this.s = s;
    }

    public String getValueAsString() {
        return s;
    }
}
