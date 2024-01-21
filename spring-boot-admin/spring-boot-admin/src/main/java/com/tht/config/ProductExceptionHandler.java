package com.tht.config;

import com.tht.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(value = {"com.tht"})
public class ProductExceptionHandler {  
  
    @ExceptionHandler(BindException.class)
	public Result<Map<String, String>> validException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> collect = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        log.error("数据校验错误: {}", collect);
        return Result.fail(collect);
    }  
  
    @ExceptionHandler(value = Exception.class)  
    public Result<String> productExceptionHandlerAdapter(Exception e) {
        String errorMessage = null;
        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream(out)) {
            e.printStackTrace(pout);
            errorMessage = out.toString();
            log.error("系统未知错误: {}", errorMessage);
        } catch (Exception ignored) {
            // 此处报错不予记录
        }
        return Result.fail(errorMessage);
    }  
  
}