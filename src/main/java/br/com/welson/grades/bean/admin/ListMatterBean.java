package br.com.welson.grades.bean.admin;

import br.com.welson.grades.annotation.Transactional;
import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.Matter;
import br.com.welson.grades.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ListMatterBean {

    private List<Matter> matters;
    @Inject
    private GenericDAO<Matter> matterDAO;

    @PostConstruct
    public void init() {
        matters = matterDAO.listAll();
    }

    public List<Matter> getMatters() {
        return matters;
    }

    public void setMatters(List<Matter> matters) {
        this.matters = matters;
    }

    @Transactional
    public void delete(Matter matter) {
        matterDAO.delete(matter);
        matters.remove(matter);
        FacesUtils.addInfoMessage("Matter deleted successfully!");
    }
}
