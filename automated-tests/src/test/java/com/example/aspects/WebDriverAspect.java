package com.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class WebDriverAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger("WEBDRIVER");

    @Before("execution(* *.*(..)) && within(org.openqa.selenium.WebDriver+)")
    public void beforeFindElement(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.toLongString());
    }

}
