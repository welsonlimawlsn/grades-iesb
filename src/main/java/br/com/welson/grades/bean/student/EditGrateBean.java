package br.com.welson.grades.bean.student;

import br.com.welson.grades.annotation.ExceptionHandler;
import br.com.welson.grades.annotation.Transactional;
import br.com.welson.grades.bean.LoginBean;
import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.Grade;
import br.com.welson.grades.utils.FacesUtils;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EditGrateBean implements Serializable {

    private Grade grade;
    @Inject
    private GenericDAO<Grade> gradeDAO;
    @Inject
    private LoginBean loginBean;
    private int id;

    public void init() {
        List<Grade> grades = gradeDAO.findByHQLQuery(0, "gradesByIdAndStudent", id, loginBean.getApplicationUser().getStudent());
        if (grades.size() > 0) {
            grade = grades.get(0);
        }
    }

    @ExceptionHandler
    @Transactional
    public String save() {
        gradeDAO.update(grade);
        FacesUtils.addInfoMessage("Grade edited successfully!");
        return "/restricted/student/grade/list.xhtml?faces-redirect=true";
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
