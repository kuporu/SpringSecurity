package org.hgc.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.hgc.demo.model.entity.UserEntity;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
