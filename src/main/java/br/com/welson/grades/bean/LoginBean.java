package br.com.welson.grades.bean;

import br.com.welson.grades.annotation.ExceptionHandler;
import br.com.welson.grades.annotation.Transactional;
import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.ApplicationUser;
import br.com.welson.grades.security.Cryptography;
import br.com.welson.grades.utils.FacesUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static br.com.welson.grades.security.Cryptography.encode;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private ApplicationUser applicationUser;
    @Inject
    private GenericDAO<ApplicationUser> applicationUserGenericDAO;

    @ExceptionHandler
    public String logon() {
        List<ApplicationUser> applicationUserLogin = applicationUserGenericDAO.findByHQLQuery(0, "applicationUserLogin", username, encode(username, password));
        if (applicationUserLogin.size() == 1) {
            applicationUser = applicationUserLogin.get(0);
            return applicationUser.getAdmin() != null ? "/restricted/admin/index.xhtml?faces-redirect=true" : "/restricted/student/index.xhtml?faces-redirect=true";
        }
        throw new RuntimeException("Username or password invalid");
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        applicationUser = null;
        return "/login.xhtml?faces-redirect=true";
    }

    @ExceptionHandler
    @Transactional
    public void update() {
        if (applicationUser.getPassword() == null || applicationUser.getPassword().isEmpty()) {
            applicationUser.setPassword(applicationUserGenericDAO.getById(applicationUser.getId()).getPassword());
        } else {
            applicationUser.setPassword(encode(applicationUser.getUsername(), applicationUser.getPassword()));
        }
        applicationUser = applicationUserGenericDAO.update(applicationUser);
        FacesUtils.addInfoMessage("User updated successfully!");
    }

    @ExceptionHandler
    @Transactional
    public String delete() {
        if(applicationUser.getAdmin() != null && applicationUserGenericDAO.findByHQLQuery(0, "applicationUserAdmin").size() == 1) {
            throw new RuntimeException("It is not possible to delete you because you are the sole administrator");
        }
        applicationUserGenericDAO.delete(applicationUserGenericDAO.getById(applicationUser.getId()));
        FacesUtils.addInfoMessage("User deleted successfully!");
        return logout();
    }

    public void setPasswordAsNull() {
        applicationUser.setPassword(null);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
