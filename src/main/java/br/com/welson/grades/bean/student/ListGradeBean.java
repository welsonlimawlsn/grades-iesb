package br.com.welson.grades.bean.student;

import br.com.welson.grades.annotation.Transactional;
import br.com.welson.grades.bean.LoginBean;
import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.Grade;
import br.com.welson.grades.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ListGradeBean {

    private List<Grade> grades;
    @Inject
    private GenericDAO<Grade> gradeDAO;
    @Inject
    private LoginBean loginBean;

    @PostConstruct
    public void init() {
        grades = gradeDAO.findByHQLQuery(0, "gradesByStudent", loginBean.getApplicationUser().getStudent());
    }

    @Transactional
    public void delete(Grade grade) {
        gradeDAO.delete(grade);
        grades.remove(grade);
        FacesUtils.addInfoMessage("Grade deleted successfully!");
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
