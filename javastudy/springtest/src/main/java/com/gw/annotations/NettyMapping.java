package com.gw.annotations;


import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface NettyMapping {
    String value() default "";

    HttpMethod[] METHODS() default {};
}
