package com.partner.boot.service;

import com.partner.boot.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 现计科1901武泊帆
 * @since 2023-04-19
 */
public interface IPermissionService extends IService<Permission> {

    List<Permission> tree();
}
