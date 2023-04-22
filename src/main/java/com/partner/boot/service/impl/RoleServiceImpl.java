package com.partner.boot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.partner.boot.entity.Role;
import com.partner.boot.entity.RolePermission;
import com.partner.boot.exception.ServiceException;
import com.partner.boot.mapper.RoleMapper;
import com.partner.boot.mapper.RolePermissionMapper;
import com.partner.boot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 现计科1901武泊帆
 * @since 2023-04-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Transactional
    @Override
    public void savePermissions(Integer roleId, List<Integer> permissionIds) {
        if (CollUtil.isEmpty(permissionIds) || roleId == null){
            throw new ServiceException("数据不能为空");
        }
        rolePermissionMapper.delete(new UpdateWrapper<RolePermission>()
                .eq("role_id",roleId));
        permissionIds.forEach(v -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(v);
            rolePermissionMapper.insert(rolePermission);
        });


    }
}
