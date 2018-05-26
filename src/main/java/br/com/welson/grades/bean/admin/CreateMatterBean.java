package br.com.welson.grades.bean.admin;

import br.com.welson.grades.annotation.ExceptionHandler;
import br.com.welson.grades.annotation.Transactional;
import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.Matter;
import br.com.welson.grades.persistence.model.Type;
import br.com.welson.grades.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class CreateMatterBean implements Serializable {

    private Matter matter;
    @Inject
    private GenericDAO<Matter> matterDAO;

    @PostConstruct
    public void init() {
        matter = new Matter();
    }

    @Transactional
    @ExceptionHandler
    public void save() {
        matterDAO.save(matter);
        matter = new Matter();
        FacesUtils.addInfoMessage("Matter created successfully!");
    }

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }

    public Type[] getTypes() {
        return Type.values();
    }
}
