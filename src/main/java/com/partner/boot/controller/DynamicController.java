package com.partner.boot.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.net.URLEncoder;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.partner.boot.common.Constants;
import com.partner.boot.entity.User;
import com.partner.boot.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.partner.boot.common.Result;
import org.springframework.web.multipart.MultipartFile;
import com.partner.boot.service.IDynamicService;
import com.partner.boot.entity.Dynamic;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态 前端控制器
 * </p>
 *
 * @author 计科1901武泊帆
 * @since 2023-04-25
 */
@RestController
@RequestMapping("/dynamic")
public class DynamicController {

    @Resource
    private IDynamicService dynamicService;

    @Resource
    IUserService userService;

    @PostMapping
    @SaCheckPermission("dynamic.add")
    public Result save(@RequestBody Dynamic dynamic) {//在后台获取用户信息
        User user = (User) StpUtil.getSession().get(Constants.LOGIN_USER_KEY);
        dynamic.setUid(user.getUid());
        dynamicService.save(dynamic);
        return Result.success();
    }

    @PutMapping
    @SaCheckPermission("dynamic.edit")
    public Result update(@RequestBody Dynamic dynamic) {
        dynamicService.updateById(dynamic);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("dynamic.delete")
    public Result delete(@PathVariable Integer id) {
        dynamicService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    @SaCheckPermission("dynamic.deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        dynamicService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    @SaCheckPermission("dynamic.list")
    public Result findAll() {
        return Result.success(dynamicService.list());
    }

    @GetMapping("/hot")
    @SaCheckPermission("dynamic.list.hot")
    public Result hot(@RequestParam Integer pageNum,
                      @RequestParam Integer pageSize) {
        QueryWrapper<Dynamic> queryWrapper = new QueryWrapper<Dynamic>().orderByDesc("id");
        Page<Dynamic> page = dynamicService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<User> users = userService.list();
        for (Dynamic record : page.getRecords()) {
            //ifPresent表示数据筛选有结果的时候触发
            users.stream().filter(user -> user.getUid().equals(record.getUid())).findFirst().ifPresent(record::setUser);

        }
        return Result.success(page);
    }

    @GetMapping("/{id}")
    @SaCheckPermission("dynamic.list")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(dynamicService.getById(id));
    }

    @GetMapping("/page")
    @SaCheckPermission("dynamic.list")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Dynamic> queryWrapper = new QueryWrapper<Dynamic>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        return Result.success(dynamicService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    @SaCheckPermission("dynamic.export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Dynamic> list = dynamicService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Dynamic信息表", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
     * excel 导入
     *
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    @SaCheckPermission("dynamic.import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Dynamic> list = reader.readAll(Dynamic.class);

        dynamicService.saveBatch(list);
        return Result.success();
    }

}
