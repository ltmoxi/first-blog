package com.moses.blog.controller.admin;

import com.moses.blog.controller.BaseController;
import com.moses.blog.entity.JsonResult;
import com.moses.blog.entity.UserInfo;
import com.moses.blog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 负责处理用户登陆等信息
 *
 * @author Moses
 */
@Controller
@RequestMapping("/admin/user_info")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 跳转到后台主页
     *
     * @return view
     */
    @RequestMapping("/index.action")
    public String index() {
        return "web/admin/index";
    }

    /**
     * 退出登陆状态
     *
     * @param session session
     * @return view
     */
    @RequestMapping("/login_out.action")
    public String loginOut(HttpSession session) {
        //session保存的登陆状态信息,登陆状态信息一般用session来确认,使用invalidate方法可以删除这个session
        session.invalidate();
        return "web/admin/login";
    }


    /**
     * 跳转到登陆页面
     */
    @RequestMapping("/login.action")
    public String login() {
        return "web/admin/login";
    }

    /**
     * 处理登陆信息
     *
     * @param username 用户名
     * @param password 密码
     * @param session  session信息,用来保存用户id与用户名
     */
    @ResponseBody
    @RequestMapping("/login.json")
    public JsonResult<Void> loginHandler(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         HttpSession session) {
        UserInfo userInfo = userInfoService.login(username, password);
        session.setAttribute("username", userInfo.getUsername());
        session.setAttribute("id", userInfo.getId());
        return new JsonResult<>();
    }

    /**
     * 处理注册信息
     *
     * @param username 用户名
     * @param password 密码
     */
    @ResponseBody
    @RequestMapping("/reg.json")
    public JsonResult<Void> regHandler(@RequestParam("username") String username,
                                       @RequestParam("password") String password) {
        userInfoService.reg(username, password);
        return new JsonResult<>();
    }


    @RequestMapping("/reg.action")
    public String reg() {
        return "web/admin/reg";
    }
}
