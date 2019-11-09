package com.example.aspects;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.join;

@Aspect
public class DemoStepActionsAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger("ACTION");

    @Before("withincode(public static * com.example.steps..*Steps.*(..))")
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
        List<String> params = IntStream.range(0, args.length)
                .mapToObj(i -> ((MethodSignature) signature).getParameterNames()[i] + " = " + args[i])
                .collect(Collectors.toList());
        return " [" + StringUtils.join(params, ", ") + "]";
    }

    private String getStep(Signature signature) {
        if (signature.getDeclaringTypeName().startsWith("com.example.services.ui.") &&
                ((MethodSignature) signature).getReturnType().equals(SelenideElement.class)) {
            return "get element " + signature.getDeclaringType().getSimpleName() + "." + signature.getName();
        }
        return capitalize(join(signature.getName().split("(?=\\p{Lu})"), " "));
    }
}
