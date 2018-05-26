package br.com.welson.grades.persistence.dao;

import br.com.welson.grades.persistence.model.AbstractEntity;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class DAOFactory implements Serializable {

    @Inject
    private EntityManager entityManager;

    @Produces
    @Dependent
    @SuppressWarnings("unchecked")
    public <T extends AbstractEntity> GenericDAO<T> getDAO(InjectionPoint injectionPoint) {
        ParameterizedType type = (ParameterizedType) injectionPoint.getType();
        Class classType = (Class) type.getActualTypeArguments()[0];
        return new DAOImpl<>(entityManager, classType);
    }
}
