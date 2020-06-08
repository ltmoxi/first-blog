package com.moses.blog.entity;

/**
 * Json格式的信息承载类
 *
 * @author Moses
 */
public class JsonResult<T> {
    /**
     * 状态码
     */
    private Integer code = 2000;
    /**
     * 返回的信息,如插入成功,删除失败之类的
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public JsonResult() {

    }

    public JsonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResult(Integer code, Exception e) {
        this.code = code;
        this.message = e.getMessage();
    }

    public JsonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
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
