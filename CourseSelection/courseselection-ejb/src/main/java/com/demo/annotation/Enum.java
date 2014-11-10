package com.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {

	public abstract String message() default "Invalid enum value. This is not permitted.";
	public abstract Class<? extends java.lang.Enum<?>> enumClass();
	
}
