package br.com.welson.grades.interceptor;

import br.com.welson.grades.annotation.Transactional;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

@Interceptor
@Transactional
public class TransactionalInterceptor implements Serializable {

    @Inject
    private EntityManager entityManager;

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) {
        Object o = null;
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            o = invocationContext.proceed();
            if (!transaction.getRollbackOnly()) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            transaction.rollback();
            System.err.println("Transaction failed!");
            e.printStackTrace();
        }
        return o;
    }
}
