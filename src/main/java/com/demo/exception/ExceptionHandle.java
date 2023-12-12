package com.demo.exception;

import com.demo.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(ServiceException.class)
    public R handleValidException(ServiceException e) {
        log.error("业务异常: {},类型: {}", e.getMessage(), e.getClass());
        return R.error(e.getMessage()).setCode(e.getStatus());
    }
}
