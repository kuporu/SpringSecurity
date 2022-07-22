package org.hgc.demo.controller;

import org.hgc.demo.enums.ResultCode;
import org.hgc.demo.model.entity.UserEntity;
import org.hgc.demo.model.param.LoginParam;
import org.hgc.demo.model.vo.ResultVO;
import org.hgc.demo.model.vo.UserVO;
import org.hgc.demo.security.JwtManager;
import org.hgc.demo.security.SelfUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/API")
public class LoginController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtManager jwtManager;

    @PostMapping("/login")
    public ResultVO login(@RequestBody LoginParam param) {
        // 生成一个包含账号密码的认证信息
        Authentication token = new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword());
        // AuthenticationManager校验这个认证信息，返回一个已认证的Authentication
        Authentication authentication = authenticationManager.authenticate(token);


        // 采用 session 认证方式
        // 将返回的Authentication存到上下文中
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResultVO("登录成功");

        // 采用 JWT 认证方式
        // 需要返回给前端的VO对象
        UserVO userVO = new UserVO();
        UserEntity user = ((SelfUserDetails) (authentication.getPrincipal())).getUserEntity();
        userVO.setId(user.getId())
                .setUsername(user.getUsername())
                // 生成JWT，将用户名数据存入其中
                .setToken(jwtManager.generate(user.getUsername()));
        return new ResultVO(userVO);
    }

    @GetMapping("/test")
    public ResultVO test () {
        return new ResultVO("操作成功");
    }

    @GetMapping("/logout")
    public ResultVO logout () {
        SecurityContextHolder.clearContext();
        return new ResultVO("退出登录");
    }
}
