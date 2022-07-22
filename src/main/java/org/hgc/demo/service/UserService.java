package org.hgc.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hgc.demo.model.entity.UserEntity;
import org.hgc.demo.model.param.LoginParam;
import org.hgc.demo.model.vo.UserVO;

public interface UserService extends IService<UserEntity> {
    /**
     * 登录
     * @param user 用户参数
     * @return 登录成功则返回vo对象，失败则抛出异常
     */
    UserVO login(LoginParam user);
}
