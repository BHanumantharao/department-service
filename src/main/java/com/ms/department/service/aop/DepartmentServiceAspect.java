package com.ms.department.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class DepartmentServiceAspect {

    @Around(value = "execution(* com.ms.department.service.service.DepartmentService.*(..))")
    public void validateReqHeader(ProceedingJoinPoint pjp) throws Throwable, AuthException {
        HttpServletRequest req = getRequest();
        if(req.getHeader("X-Dept-Msg") != null && !req.getHeader("X-Dept-Msg").equalsIgnoreCase("DEPT")) {
            throw new AuthException("Error: Can't process request, pass right input");
        }

        pjp.proceed();
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest();
    }
}
