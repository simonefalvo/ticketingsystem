package it.uniroma2.ticketingsystem.logger.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @After("@annotation(LogOperation)")
    public void logExecutionTime(JoinPoint jp) throws Throwable {
        System.out.println("****LogAspect.logOperation() " + jp.getSignature().getName());
    }

}