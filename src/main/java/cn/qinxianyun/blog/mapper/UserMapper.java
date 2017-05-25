package cn.qinxianyun.blog.mapper;

import cn.qinxianyun.blog.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 获取用户凭证
     * @param username 账号
     * @return
     */
    User getUser(@Param("username") String username);

    List<User> allUser();
}
