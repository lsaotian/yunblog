package cn.qinxianyun.blog.controller.admin;

import cn.qinxianyun.blog.service.UserService;
import cn.qinxianyun.blog.util.Md5Util;
import cn.qinxianyun.blog.util.ResultInfo;
import cn.qinxianyun.blog.util.ResultInfoFactory;
import cn.qinxianyun.blog.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login/auth",method = RequestMethod.POST)
    public ResultInfo loginAuth(User user, HttpSession session){
        ResultInfo resultInfo = null;
        User userInfo = userService.loadUserByUsername(user.getUsername());
        if (userInfo==null){
            resultInfo =  ResultInfoFactory.getErrorResultInfo("账号不存在");
        }else{
            if (userInfo.getPassword().equals(Md5Util.pwdDigest(user.getPassword()))){
                resultInfo = ResultInfoFactory.getSuccessResultInfo();
            }else {
                resultInfo = ResultInfoFactory.getErrorResultInfo("账号或密码错误");
            }
        session.setAttribute("user",userInfo);
        }

        return resultInfo;

    }
}
