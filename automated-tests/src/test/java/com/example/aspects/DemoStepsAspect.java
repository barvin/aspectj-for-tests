package com.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.join;

@Aspect
public class DemoStepsAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger("STEP");

    @Before("execution(public static * com.example.steps..*Steps.*(..))")
    public void beforeAnyStep(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        LOGGER.warn(getStep(signature) + getParameters(signature, joinPoint.getArgs()));
    }

    private String getParameters(Signature signature, Object[] args) {
        StringBuilder result = new StringBuilder(" [");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(((MethodSignature) signature).getParameterNames()[i]).append(" = ").append(args[i]);
        }
        return result.append("]").toString();
    }

    private String getStep(Signature signature) {
        return capitalize(join(signature.getName().split("(?=\\p{Lu})"), " "));
    }
}
