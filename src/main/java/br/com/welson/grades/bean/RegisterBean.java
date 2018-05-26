package br.com.welson.grades.bean;

import br.com.welson.grades.annotation.ExceptionHandler;
import br.com.welson.grades.annotation.Transactional;
import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.Admin;
import br.com.welson.grades.persistence.model.ApplicationUser;
import br.com.welson.grades.persistence.model.Student;
import br.com.welson.grades.utils.FacesUtils;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import static br.com.welson.grades.security.Cryptography.encode;

@Named
@ViewScoped
public class RegisterBean implements Serializable {

    private ApplicationUser applicationUser;
    @Inject
    private GenericDAO<ApplicationUser> applicationUserDAO;

    public void initStudent() {
        applicationUser = new ApplicationUser();
        applicationUser.setStudent(new Student());
    }

    public void initAdmin() {
        applicationUser = new ApplicationUser();
        applicationUser.setAdmin(new Admin());
    }

    @Transactional
    @ExceptionHandler
    public String createApplicationUser() {
        applicationUser.setPassword(encode(applicationUser.getUsername(), applicationUser.getPassword()));
        applicationUserDAO.save(applicationUser);
        FacesUtils.addInfoMessage("User created successfully!");
        return "login.xhtml?faces-redirect=true";
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
