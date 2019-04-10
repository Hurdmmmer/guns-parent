package com.stylefeng.guns.rest.common.aop;

import com.stylefeng.guns.core.aop.BaseControllerExceptionHandler;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.rest.common.exception.BizExceptionEnum;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午3:19:56
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截jwt相关异常
     */
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip jwtException(JwtException e) {
        return new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorTip handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return new ErrorTip(BizExceptionEnum.AUTH_REQUEST_ERROR.getCode(), fieldError.getDefaultMessage());
    }


    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorTip handleBindException(BindException ex) {
        //校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return new ErrorTip(BizExceptionEnum.AUTH_REQUEST_ERROR.getCode(), fieldError.getDefaultMessage());

    }
}
