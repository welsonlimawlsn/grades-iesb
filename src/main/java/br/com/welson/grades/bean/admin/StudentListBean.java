package br.com.welson.grades.bean.admin;

import br.com.welson.grades.annotation.Transactional;
import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.ApplicationUser;
import br.com.welson.grades.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class StudentListBean {

    @Inject
    private GenericDAO<ApplicationUser> applicationUserDAO;
    private List<ApplicationUser> students;

    @PostConstruct
    public void init() {
        students = applicationUserDAO.findByHQLQuery(0, "applicationUserStudent");
    }

    public List<ApplicationUser> getStudents() {
        return students;
    }

    public void setStudents(List<ApplicationUser> students) {
        this.students = students;
    }

    @Transactional
    public void delete(ApplicationUser student) {
        applicationUserDAO.delete(student);
        students.remove(student);
        FacesUtils.addInfoMessage("Student deleted successfully!");
    }
}
