package com.moses.blog.controller;

import com.moses.blog.exception.PasswordNotMatchException;
import com.moses.blog.exception.ServiceException;
import com.moses.blog.exception.UserNotFoundException;
import com.moses.blog.view.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Moses
 */
public class BaseController {

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Exception e) {
        //判断异常类型并处理
        if (e instanceof UserNotFoundException) {
            //300 请求参数异常
            return new JsonResult<>(300, e);
        } else if (e instanceof PasswordNotMatchException) {
            return new JsonResult<>(500, e);
        }
        return null;
    }
}
