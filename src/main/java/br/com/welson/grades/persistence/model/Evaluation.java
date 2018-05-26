package br.com.welson.grades.persistence.model;

import javax.persistence.Entity;

@Entity
public class Evaluation extends AbstractEntity {

    private double exam;
    private double forum;
    private double exercise;
    private double learningCheck;
    private double edad;

    public double getExam() {
        return exam;
    }

    public void setExam(double exam) {
        this.exam = exam;
    }

    public double getForum() {
        return forum;
    }

    public void setForum(double forum) {
        this.forum = forum;
    }

    public double getExercise() {
        return exercise;
    }

    public void setExercise(double exercise) {
        this.exercise = exercise;
    }

    public double getLearningCheck() {
        return learningCheck;
    }

    public void setLearningCheck(double learningCheck) {
        this.learningCheck = learningCheck;
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }
}
