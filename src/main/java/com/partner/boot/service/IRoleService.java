package com.partner.boot.service;

import com.partner.boot.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.partner.boot.entity.RolePermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 现计科1901武泊帆
 * @since 2023-04-19
 */
public interface IRoleService extends IService<Role> {

    void savePermissions(Integer roleId, List<Integer> permissionIds);

}