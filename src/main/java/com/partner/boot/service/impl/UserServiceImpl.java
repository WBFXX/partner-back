package com.partner.boot.service.impl;

import com.partner.boot.entity.User;
import com.partner.boot.mapper.UserMapper;
import com.partner.boot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 计科1901武泊帆
 * @since 2022-12-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
