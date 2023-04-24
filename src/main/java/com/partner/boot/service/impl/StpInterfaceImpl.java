package com.partner.boot.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.partner.boot.entity.Permission;
import com.partner.boot.entity.User;
import com.partner.boot.service.IUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    IUserService userService;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {

        //loginId
        User user = userService.getOne(new QueryWrapper<User>().eq("uid", loginId));
        String roleFlag = user.getRole();
        List<Permission> permissions = userService.getPermissions(roleFlag);

        return permissions.stream().map(Permission::getAuth ).filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());//字符串的权限集合

        //["user.add","user.edit"]
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        User user = userService.getOne(new QueryWrapper<User>().eq("uid", loginId));
        String roleFlag = user.getRole();
        List<String> roles = new ArrayList<>();
        return StrUtil.isBlank(roleFlag) ? roles : CollUtil.newArrayList(roleFlag);//["ADMIN"]
    }

}
