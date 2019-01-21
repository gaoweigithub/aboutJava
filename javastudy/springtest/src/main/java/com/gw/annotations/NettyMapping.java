package com.gw.annotations;


import org.springframework.http.HttpMethod;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NettyMapping {
    String value() default "";

    HttpMethod[] METHODS() default {};
}
