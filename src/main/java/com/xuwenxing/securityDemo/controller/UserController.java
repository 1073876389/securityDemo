package com.xuwenxing.securityDemo.controller;

import com.xuwenxing.securityDemo.config.security.SecurityUser;
import com.xuwenxing.securityDemo.domain.system.User;
import com.xuwenxing.securityDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by xuwx on 2018/10/9.
 */
@Controller
public class UserController
{

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String allUser(HttpServletRequest request, HttpServletResponse response, Model model){
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        SecurityUser user = (SecurityUser)securityContextImpl.getAuthentication().getPrincipal();
        model.addAttribute("SessionUser",user);
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/allUser")
    public String root(Map<String,Object> map) {
        List<User> list = userService.findList();
        map.put("users",list);
        return "showAllUser";
    }



    @RequestMapping("/userLogin")
    public String userLogin(Model model,String username, String password){
        User user = userService.userLogin(username, password);
        if (user!=null){
            model.addAttribute("msj","登陆成功!");
            model.addAttribute("success",true);
            model.addAttribute("user",user);
            return "redirect:/allUser";
        }else {
            model.addAttribute("success",false);
            model.addAttribute("msj","登陆失败!");
            return "/login";
        }
    }


}
