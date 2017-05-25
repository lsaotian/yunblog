package cn.qinxianyun.blog.config;

import cn.qinxianyun.blog.util.Md5Util;
import cn.qinxianyun.blog.service.UserService;
import cn.qinxianyun.blog.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
@EnableWebSecurity
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        //从数据库找到的用户
        User user = null;
        if (username != null) {
            user = userService.loadUserByUsername(username);
        }
        //
        if (user == null) {
            throw new UsernameNotFoundException("用户名/密码无效");
        } else if (user.isEnabled()) {
            throw new DisabledException("用户已被禁用");
        } else if (user.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        } else if (user.isAccountNonLocked()) {
            throw new LockedException("账号已被锁定");
        } else if (user.isCredentialsNonExpired()) {
            throw new LockedException("凭证已过期");
        }
        //数据库用户的密码
        String password = user.getPassword();
        String pwdDigest = Md5Util.pwdDigest(token.getCredentials().toString());
        //与authentication里面的credentials相比较
        if (!password.equals(pwdDigest)) {

            throw new BadCredentialsException("Invalid username/password");
        }
        //授权
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    public void config(WebSecurity web) {
        web.ignoring().antMatchers("/js/**", "/css/**", "/vendor/**", "/image/**", "/admin/**");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}


