package com.admin.zhonghe.config;

import com.admin.zhonghe.enums.ExceptionEnum;
import com.admin.zhonghe.utils.R;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError :fieldErrors){
            System.out.println(fieldError.getField());
           sb.append(fieldError.getDefaultMessage());
        }
        ExceptionEnum exceptionEnum =ExceptionEnum.BODY_NOT_MATCH;
        exceptionEnum.setMessage( sb.toString());
        return R.fail(exceptionEnum);

    }
    @ExceptionHandler(BindException.class)
    public R bindException(BindException ex) {
        List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
        System.out.println(fieldError);
        StringBuilder sb = new StringBuilder();
        ExceptionEnum exceptionEnum =ExceptionEnum.BODY_NOT_MATCH;
        exceptionEnum.setMessage( sb.toString());
        return R.fail(exceptionEnum);
    }

    @ExceptionHandler(Exception.class)
    public R ex(Exception exception){
        exception.printStackTrace();
        return R.fail(ExceptionEnum.INTERNAL_SERVER_ERROR)  ;
    }


}
