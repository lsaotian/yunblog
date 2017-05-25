package cn.qinxianyun.blog.service;


import cn.qinxianyun.blog.vo.User;

public interface UserService {


    User loadUserByUsername(String username);
}
