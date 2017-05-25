package cn.qinxianyun.blog.controller;

import cn.qinxianyun.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

@Resource
UserService userService;


}
