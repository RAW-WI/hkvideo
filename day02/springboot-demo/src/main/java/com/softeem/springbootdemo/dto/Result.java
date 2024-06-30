package com.softeem.springbootdemo.dto;

/**
 * 统一返回结果
 */
public class Result {

    //状态码
    private int code;
    //附加文本信息
    private String msg;
    //传递复杂数据
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
}
