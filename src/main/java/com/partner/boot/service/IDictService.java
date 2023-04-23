package com.partner.boot.service;

import com.partner.boot.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 现计科1901武泊帆
 * @since 2023-04-23
 */
public interface IDictService extends IService<Dict> {

    List<Dict> findIcons();
}
