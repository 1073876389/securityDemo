package com.xuwenxing.securityDemo.controller;

import com.xuwenxing.securityDemo.config.security.SecurityUser;
import com.xuwenxing.securityDemo.domain.system.User;
import com.xuwenxing.securityDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xuwx on 2018/10/9.
 */
@Controller
public class UserController
{

    @Autowired
    private UserService userService;

    /**
     * 默认情况下安全配置没有使用指定路径的话登录成功后会登录到当前路径下
     * @param request
     * @param response
     * @param model
     * @return
     */
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

    @RequestMapping("/uploadFiles")
     public String uploadFiles(){
        return "uploadFiles";
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

    /**
     * 文件上传 文件处理类
     */
    @RequestMapping("/myUpload")
    @ResponseBody
     public String myUpload(@RequestParam("myUploadFile")MultipartFile file){
        System.out.println("文件名 "+file.getOriginalFilename());
        //获取文件名
        String filename = file.getOriginalFilename();
        //获取后缀
        filename.substring(filename.lastIndexOf("."));
        //文件上传的路径
        String filePath="d:/upload/";
        //文件名处理
         filename = filePath + UUID.randomUUID() + filename;
         //文件对象
        File dest = new File(filename);
        //创建路径
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        }catch (IOException e){
            e.printStackTrace();
        }
        return "上传失败";
    }




}
