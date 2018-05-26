package br.com.welson.grades.converter;

import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.Matter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MatterConverter implements Converter {

    @Inject
    private GenericDAO<Matter> matterDAO;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || !value.matches("\\d+"))
            return null;
        return matterDAO.getById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value != null && !value.equals("")) {
            Matter matter = (Matter) value;
            if (matter.getId() != null) {
                return matter.getId().toString();
            }
        }
        return null;
    }
}
