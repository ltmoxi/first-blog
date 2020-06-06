package com.moses.blog.controller;

import com.moses.blog.entity.JsonResult;
import com.moses.blog.exception.NotFoundException;
import com.moses.blog.exception.PasswordNotMatchException;
import com.moses.blog.exception.ServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Moses
 */
public class BaseController {

    /**
     * 用于统一处理异常的方法
     *
     * @param e 异常
     * @return json数据
     */
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Exception e) {
        //判断异常类型并处理
        if (e instanceof NotFoundException) {
            //300 请求参数异常
            return new JsonResult<>(300, e);
        } else if (e instanceof PasswordNotMatchException) {
            //500 用户名密码不正确异常
            return new JsonResult<>(500, e);
        } else if (e instanceof ServiceException) {
            //600 业务类异常
            return new JsonResult<>(600, e);
        }
        return null;
    }
}
