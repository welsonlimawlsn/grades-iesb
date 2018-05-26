package br.com.welson.grades.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Grade extends AbstractEntity {
    @ManyToOne
    private Matter matter;
    @ManyToOne
    private Student student;
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Evaluation firstEvaluation;
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Evaluation secondEvaluation;

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Evaluation getFirstEvaluation() {
        return firstEvaluation;
    }

    public void setFirstEvaluation(Evaluation firstEvaluation) {
        this.firstEvaluation = firstEvaluation;
    }

    public Evaluation getSecondEvaluation() {
        return secondEvaluation;
    }

    public void setSecondEvaluation(Evaluation secondEvaluation) {
        this.secondEvaluation = secondEvaluation;
    }

    public double getAverage() {
        if (matter.getType() == Type.HUMAN) {
            double average1 = firstEvaluation.getExam() + firstEvaluation.getForum() + firstEvaluation.getLearningCheck();
            double average2 = (secondEvaluation.getExam() + secondEvaluation.getForum() + secondEvaluation.getLearningCheck()) * 0.95 + (secondEvaluation.getEdad() * 0.05);
            return average1 * 0.4 + average2 * 0.6;
        }
        if (matter.getType() == Type.EXACT) {
            double average1 = firstEvaluation.getExam() + firstEvaluation.getExercise() + firstEvaluation.getForum() + firstEvaluation.getLearningCheck();
            double average2 = (secondEvaluation.getExam() + secondEvaluation.getExercise() + secondEvaluation.getForum() + secondEvaluation.getLearningCheck()) * 0.95 + (secondEvaluation.getEdad() * 0.05);
            return average1 * 0.4 + average2 * 0.6;
        }
        return 0;
    }

    public Mention getMention() {
        double average = getAverage();
        if (average == 0) {
            return Mention.SR;
        } else if (average < 3) {
            return Mention.II;
        } else if (average < 5) {
            return Mention.MI;
        } else if (average < 7) {
            return Mention.MM;
        } else if (average < 9) {
            return Mention.MS;
        } else {
            return Mention.SS;
        }
    }
}
