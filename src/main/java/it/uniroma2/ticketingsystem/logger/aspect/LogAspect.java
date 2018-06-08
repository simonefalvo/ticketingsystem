package it.uniroma2.ticketingsystem.logger.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @After("@annotation(LogOperation)")
    public void logExecutionTime(JoinPoint jp) throws Throwable {

        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        LogOperation a = method.getAnnotation(LogOperation.class);
        String value = a.objName();
        String[] argNames = signature.getParameterNames();
        Object[] args = jp.getArgs();

        int index = 0;
        for (String s : argNames) {
            System.out.println("**** argomento " + s);
            if (value.equals(s)) {
                System.out.println("**** argomento TROVATO " + s + ", index: " + String.valueOf(index));
                break;
            }
            index++;
        }
        Object target = args[index];

        System.out.println("**** LogAspect.logOperation() " + target.toString());
    }

}