package cn.qinxianyun.blog.service.impl;

import cn.qinxianyun.blog.mapper.UserMapper;
import cn.qinxianyun.blog.service.UserService;
import cn.qinxianyun.blog.vo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User loadUserByUsername(String username) {
        return userMapper.getUser(username);
    }
}


