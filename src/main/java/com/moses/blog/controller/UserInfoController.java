package com.moses.blog.controller;

import com.moses.blog.service.UserInfoService;
import com.moses.blog.entity.JsonResult;
import com.moses.blog.entity.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Moses
 */
@Controller
@RequestMapping("user_info")
public class UserInfoController extends BaseController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("index.action")
    public String index() {
        return "admin/index";
    }

    

    @RequestMapping("login.action")
    public String login() {
        return "admin/login";
    }

    @ResponseBody
    @RequestMapping("login.json")
    public JsonResult<Void> loginHandler(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         HttpSession session) {
        UserInfo userInfo = userInfoService.login(username, password);
        session.setAttribute("username", userInfo.getUsername());
        session.setAttribute("id", userInfo.getId());
        return new JsonResult<>();
    }

    @ResponseBody
    @RequestMapping("detail.json")
    public JsonResult<Object> loginHandler(HttpSession session) {
        Integer id = (Integer) session.getAttribute("id");

        System.out.println("UserInfoController.loginHandler");
        if (id == null) {
            return new JsonResult<>(400, "错误","没找到");
        }
        return new JsonResult<>(200,"成功",id);
    }
}
