package br.com.welson.grades.interceptor;

import br.com.welson.grades.annotation.ExceptionHandler;
import br.com.welson.grades.utils.FacesUtils;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@ExceptionHandler
public class ExceptionHandlerInterceptor implements Serializable {

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) {
        Object o = null;
        try {
            o = invocationContext.proceed();
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(e.getMessage());
        }
        return o;
    }
}
