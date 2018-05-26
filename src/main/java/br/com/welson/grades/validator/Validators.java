package br.com.welson.grades.validator;

import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.ApplicationUser;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class Validators implements Serializable {

    @Inject
    private GenericDAO<ApplicationUser> applicationUserDAO;

    public void validateDuplicatedUsername(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        List<ApplicationUser> usernameList = applicationUserDAO.findByHQLQuery(0, "usernameApplicationUser", (String) o);
        if (usernameList.size() > 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "This username already exists", ""));
        }
    }
}
