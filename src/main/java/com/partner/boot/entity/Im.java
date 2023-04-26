package com.partner.boot.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.partner.boot.common.LDTConfig;
import io.netty.channel.unix.PeerCredentials;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author 现计科1901武泊帆
 * @since 2023-04-25
 */
@Getter
@Setter
@ApiModel(value = "Im对象", description = "")
@Builder
public class Im implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 用户编号
    @ApiModelProperty("用户编号")
    @Alias("用户编号")
    private String uid;

    // 用户姓名
    @ApiModelProperty("用户姓名")
    @Alias("用户姓名")
    private String username;

    // 头像
    @ApiModelProperty("头像")
    @Alias("头像")
    private String avatar;

    // 个性签名
    @ApiModelProperty("个性签名")
    @Alias("个性签名")
    private String sign;

    // 消息文字
    @ApiModelProperty("消息文字")
    @Alias("消息文字")
    private String text;

    // 发送时间
    @ApiModelProperty("发送时间")
    @Alias("发送时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonDeserialize(using = LDTConfig.CmzLdtDeSerializer.class)
    @JsonSerialize(using = LDTConfig.CmzLdtSerializer.class)
    private LocalDateTime createTime;

    // 图片
    @ApiModelProperty("图片")
    @Alias("图片")
    private String img;

}