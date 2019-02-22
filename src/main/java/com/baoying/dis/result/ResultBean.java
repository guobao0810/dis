package com.baoying.dis.result;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/15 14:43
 * @Version: 1.0
 */
public class ResultBean<T> implements Serializable {

    private static final String SUCCESS = "成功";

    private static final String FAILED = "失败";

    private Integer code;

    private String message;

    private T data;

    public ResultBean(){

    }

    public ResultBean(T data) {
        super();
        this.code = HttpStatus.OK.value();
        this.message = SUCCESS;
        this.data = data;
    }

    public ResultBean(Integer code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultBean(String message){
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        if(message != null){
            this.message = message;
        }else{
            this.message = FAILED;
        }
    }

    public ResultBean(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
