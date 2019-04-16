package com.aspectlog.annotation;

import com.aspectlog.Enumeration.LogType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Log {

    public LogType type();

    public String contect();

}
