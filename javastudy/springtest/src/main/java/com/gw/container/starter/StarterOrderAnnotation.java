package com.gw.container.starter;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface StarterOrderAnnotation {
    int order() default Integer.MAX_VALUE;
}
