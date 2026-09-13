package com.works.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List hata(MethodArgumentNotValidException e){
        return parseError(e.getFieldErrors());
    }

    // hataların bulunduğu bir list geriye dönsün
    // her hata için bir map oluşturulsun, map içinde field, rejectedValue, defaultMessage olsun
    private List parseError(List<FieldError> fieldErrors) {
        List list = new ArrayList();
        for (FieldError fieldError : fieldErrors) {
            Map map = new HashMap();
            map.put("field", fieldError.getField());
            map.put("rejectedValue", fieldError.getRejectedValue());
            map.put("defaultMessage", fieldError.getDefaultMessage());
            list.add(map);
        }
        return list;
    }


}
