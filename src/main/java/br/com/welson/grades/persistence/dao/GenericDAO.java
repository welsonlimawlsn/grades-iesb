package br.com.welson.grades.persistence.dao;

import br.com.welson.grades.persistence.model.AbstractEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T extends AbstractEntity> extends Serializable {

    T save(T entity);

    T update(T entity);

    T getById(Integer id);

    void delete(T entity);

    List<T> listAll();

    List<T> findByHQLQuery(int maxResults, String queryId, Object... values);
}
