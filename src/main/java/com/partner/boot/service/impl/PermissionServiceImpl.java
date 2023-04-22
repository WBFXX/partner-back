package com.partner.boot.service.impl;

import com.partner.boot.entity.Permission;
import com.partner.boot.mapper.PermissionMapper;
import com.partner.boot.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 现计科1901武泊帆
 * @since 2023-04-19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> tree() {

        //先查询所有的数据
        List<Permission> allData = list();
//        //筛选出目录、菜单、按钮
//        List<Permission> parentList = allData.stream().filter(p -> p.getPid() == null).collect(Collectors.toList());
//        for (Permission permission : parentList) {
//            List<Permission> level2List = allData.stream().filter(p -> Objects.equals(p.getPid(), permission.getId())).collect(Collectors.toList());//第二层
//            permission.setChildren(level2List);
//            for (Permission permission1 : level2List) {
//                List<Permission> level3List = allData.stream().filter(p -> Objects.equals(p.getPid(), permission1.getId())).collect(Collectors.toList());//第三层
//                permission1.setChildren(level3List);
//
//            }
//        }
        return childrenTree(null,allData);//从第一级开始往下递归获取数
    }


    //递归
    private List<Permission> childrenTree(Integer pid , List<Permission> allData){
        List<Permission> list = new ArrayList<>();
        for (Permission permission : allData) {
            if (Objects.equals(permission.getPid(),pid)) {//pid为null时作为一级节点
                list.add(permission);
                List<Permission> childrenTree = childrenTree(permission.getId(), allData);//递归调用 摘取二级、三级、、、节点
                permission.setChildren(childrenTree);
            }
        }
    return list;
   }
}
