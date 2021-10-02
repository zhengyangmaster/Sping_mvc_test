package cn.zzy.controller;

import cn.zzy.domain.Role;
import cn.zzy.domain.User;
import cn.zzy.service.RoleService;
import cn.zzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private RoleService roleService;


    @RequestMapping("/login")
    public String login(String username,String password,HttpSession session) {

        User user=service.login(username,password);



        if (user !=null){
            //将用户信息存储到session域中
            session.setAttribute("user",user);


            return "redirect:/user/list";
        }




        return "redirect:/login.jsp";
    }
    @RequestMapping("/del/{userId}")
    public String del(@PathVariable("userId") Long userId) {

        service.del(userId);


        return "redirect:/user/list";
    }

    @RequestMapping("/save")
    public String save(User user ,Long[] roleIds) {

        service.save(user,roleIds);


        return "redirect:/user/list";
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveUI() {

        List<Role> roleList = roleService.list();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");


        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView modelAndView() {

        List<User> userList = service.listAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user-list");


        return modelAndView;
    }
}
