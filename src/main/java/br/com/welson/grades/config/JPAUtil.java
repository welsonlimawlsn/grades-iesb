package br.com.welson.grades.config;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

@Dependent
public class JPAUtil implements Serializable {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager != null && entityManager.isOpen())
            entityManager.close();
    }
}
