package com.admin.zhonghe.validations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone,String> {
    private  boolean required=false;
    private Pattern pattern=Pattern.compile("1(([38]\\d)|(5[^4&&\\d]) |(4[579])|(7[0135678]))\\d{8}");

    @Override
    public void initialize(Phone constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {  if(required) {
        return pattern.matcher(s).matches();
    }else {
        if(StringUtils.isEmpty(s)) {
            return false;
        }else{
            return pattern.matcher(s).matches();
        }
    }

    }
}
