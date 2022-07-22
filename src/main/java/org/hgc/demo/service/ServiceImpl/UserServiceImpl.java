package org.hgc.demo.service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hgc.demo.mapper.UserMapper;
import org.hgc.demo.model.entity.UserEntity;
import org.hgc.demo.model.param.LoginParam;
import org.hgc.demo.model.vo.UserVO;
import org.hgc.demo.security.SelfUserDetails;
import org.hgc.demo.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService, UserDetailsService {

    @Override
    public UserVO login(LoginParam user) {
        return null;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getUsername, username);
        UserEntity selectOne = baseMapper.selectOne(wrapper);
        if (Objects.isNull(selectOne)) {
            throw new UsernameNotFoundException("没有找到该用户");
        }

        // TODO: 2022/7/21 权限还没有涉及到，暂时放个空集合
        return new SelfUserDetails(selectOne);
    }
}
