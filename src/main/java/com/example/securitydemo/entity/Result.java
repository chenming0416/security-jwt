package com.example.securitydemo.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


    public Result getResult(int code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public Result success(Object data){
        return getResult(200,"成功",data);
    }

    public Result fail(Object data){
        return getResult(500,"服务器报错",data);
    }

}
