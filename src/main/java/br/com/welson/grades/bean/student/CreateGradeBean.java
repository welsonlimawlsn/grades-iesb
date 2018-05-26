package br.com.welson.grades.bean.student;

import br.com.welson.grades.annotation.Transactional;
import br.com.welson.grades.bean.LoginBean;
import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.Evaluation;
import br.com.welson.grades.persistence.model.Grade;
import br.com.welson.grades.persistence.model.Matter;
import br.com.welson.grades.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class CreateGradeBean {

    private Grade grade;
    @Inject
    private GenericDAO<Grade> gradeDAO;
    @Inject
    private LoginBean loginBean;
    @Inject
    private GenericDAO<Matter> matterDAO;
    private List<Matter> matters;

    @PostConstruct
    public void init() {
        grade = new Grade();
        grade.setStudent(loginBean.getApplicationUser().getStudent());
        grade.setFirstEvaluation(new Evaluation());
        grade.setSecondEvaluation(new Evaluation());
        matters = matterDAO.listAll();
    }

    @Transactional
    public String save() {
        Grade savedGrade = gradeDAO.save(grade);
        init();
        FacesUtils.addInfoMessage("Grade created successfully!");
        return "/restricted/student/grade/edit.xhtml?id=" + savedGrade.getId() + "faces-redirect=true";
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<Matter> getMatters() {
        return matters;
    }

    public void setMatters(List<Matter> matters) {
        this.matters = matters;
    }
}
