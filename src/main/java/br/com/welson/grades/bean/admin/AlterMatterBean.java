package br.com.welson.grades.bean.admin;

import br.com.welson.grades.annotation.Transactional;
import br.com.welson.grades.persistence.dao.GenericDAO;
import br.com.welson.grades.persistence.model.Matter;
import br.com.welson.grades.persistence.model.Type;
import br.com.welson.grades.utils.FacesUtils;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AlterMatterBean implements Serializable {

    private final GenericDAO<Matter> matterDAO;
    private Matter matter;
    private int id;

    @Inject
    public AlterMatterBean(GenericDAO<Matter> matterDAO) {
        this.matterDAO = matterDAO;
    }

    public void init() {
        matter = matterDAO.getById(id);
    }

    @Transactional
    public String alter() {
        matterDAO.update(matter);
        FacesUtils.addInfoMessage("Matter altered successfully!");
        return "/restricted/admin/matter/list.xhtml?faces-redirect=true";
    }

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type[] getTypes() {
        return Type.values();
    }
}
