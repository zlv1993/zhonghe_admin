package com.admin.zhonghe.validations;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import static   java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy =PhoneValidator.class)

public @interface Phone {
    boolean required() default true;
    String message() default "手机号码格式错误";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
