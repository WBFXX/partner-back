package com.partner.boot.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import cn.hutool.core.annotation.Alias;
import com.partner.boot.common.LDTConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author 计科1901武泊帆
 * @since 2022-12-30
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    //serialVersionUID作用：
    //序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性。

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 用户名
    @ApiModelProperty("用户名")
    @Alias("用户名")
    private String username;

    // 密码
    @ApiModelProperty("密码")
    @Alias("密码")
    private String password;

    // 昵称
    @ApiModelProperty("昵称")
    @Alias("昵称")
    private String name;

    // 地址
    @ApiModelProperty("地址")
    @Alias("地址")
    private String address;

    // 邮箱
    @ApiModelProperty("邮箱")
    @Alias("邮箱")
    private String email;

    // 唯一标识
    @ApiModelProperty("唯一标识")
    @Alias("唯一标识")
    private String uid;
    //逻辑删除
    @ApiModelProperty("逻辑删除")
    @TableLogic(value = "0",delval = "id")
    private Integer deleted;
    // 头像
    @ApiModelProperty("头像")
    @Alias("头像")
    private String avatar;

    @ApiModelProperty("创建时间")
    @Alias("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonDeserialize(using = LDTConfig.CmzLdtDeSerializer.class)
    @JsonSerialize(using = LDTConfig.CmzLdtSerializer.class)
    private LocalDateTime createTime;


    @ApiModelProperty("更新时间")
    @Alias("更新时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonDeserialize(using = LDTConfig.CmzLdtDeSerializer.class)
    @JsonSerialize(using = LDTConfig.CmzLdtSerializer.class)
    private LocalDateTime updateTime;

    @ApiModelProperty("角色")
    @Alias("角色")
    private String  role;

    @ApiModelProperty("个性签名")
    @Alias("个性签名")
    private String  sign;
}