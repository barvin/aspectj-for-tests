package com.example.aspects;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<String> params = IntStream.range(0, args.length)
                .mapToObj(i -> ((MethodSignature) signature).getParameterNames()[i] + " = " + args[i])
                .collect(Collectors.toList());
        return " [" + StringUtils.join(params, ", ") + "]";
    }

    private String getStep(Signature signature) {
        return capitalize(join(signature.getName().split("(?=\\p{Lu})"), " "));
    }
}
