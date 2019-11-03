package com.example.aspects;

import com.codeborne.selenide.SelenideElement;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.join;

@Aspect
public class DemoStepActionsAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger("ACTION");

    @Pointcut("withincode(public static * com.example.steps..*Steps.*(..))")
    void anyStepStatement() {
    }

    @Before("anyStepStatement()")
    public void beforeAnyStepStatement(JoinPoint joinPoint) {
        if (joinPoint.getKind().equals(JoinPoint.METHOD_CALL)) {
            Signature signature = joinPoint.getSignature();
            LOGGER.info(getStep(signature) + getParameters(signature, joinPoint.getArgs()));
        }
    }

    private String getParameters(Signature signature, Object[] args) {
        if (args.length == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(((MethodSignature) signature).getParameterNames()[i]).append(" = ").append(args[i]);
        }
        return result.append("]").toString();
    }

    private String getStep(Signature signature) {
        if (signature.getDeclaringTypeName().startsWith("com.example.services.ui.") &&
                ((MethodSignature) signature).getReturnType().equals(SelenideElement.class)) {
            return "get element " + signature.getDeclaringType().getSimpleName() + "." + signature.getName();
        }
        return capitalize(join(signature.getName().split("(?=\\p{Lu})"), " "));
    }
}
