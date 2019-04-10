package com.stylefeng.guns.rest.modular.vo;

import com.stylefeng.guns.rest.common.exception.BizExceptionEnum;
import lombok.Data;

@Data
public class ResponseVo<M> {
    private int state;
    private String msg;
    private M data;

    private ResponseVo() {}

    public static<T> ResponseVo ok(T data) {
        ResponseVo<T> result = new ResponseVo<>();
        result.setData(data);
        result.setState(0);
        return result;
    }

    public static<T> ResponseVo ok(String msg) {
        ResponseVo<T> result = new ResponseVo<>();
        result.setState(0);
        result.setMsg(msg);
        return result;
    }

    public static<T> ResponseVo serviceFail(BizExceptionEnum bizExceptionEnum) {
        ResponseVo<T> result = new ResponseVo<>();
        result.setMsg(bizExceptionEnum.getMessage());
        result.setState(bizExceptionEnum.getCode());
        return result;
    }

    public static<T> ResponseVo serviceFail(String msg) {
        ResponseVo<T> result = new ResponseVo<>();
        result.setMsg(msg);
        result.setState(999);
        return result;
    }

    public static<T> ResponseVo appFail(BizExceptionEnum bizExceptionEnum) {
        ResponseVo<T> result = new ResponseVo<>();
        result.setMsg(bizExceptionEnum.getMessage());
        result.setState(bizExceptionEnum.getCode());
        return result;
    }
}
