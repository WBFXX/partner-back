package com.partner.boot.entity;

    import com.baomidou.mybatisplus.annotation.FieldFill;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableField;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import java.time.LocalDateTime;
import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
    import lombok.Getter;
    import lombok.Setter;

/**
* <p>
    * 动态
    * </p>
*
* @author 现计科1901武泊帆
* @since 2023-04-19
*/
    @Getter
    @Setter
@ApiModel(value = "Dynamic对象", description = "动态")
    public class Dynamic implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            // 标题
            @ApiModelProperty("标题")
            @Alias("标题")
    private String name;

            // 内容
            @ApiModelProperty("内容")
            @Alias("内容")
    private String content;

            // 图片
            @ApiModelProperty("图片")
            @Alias("图片")
    private String imgs;

            // 简介
            @ApiModelProperty("简介")
            @Alias("简介")
    private String description;

            // 创建时间
            @ApiModelProperty("创建时间")
            @Alias("创建时间")
            @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

            // 更新时间
            @ApiModelProperty("更新时间")
            @Alias("更新时间")
    private LocalDateTime updateTime;

            // 用户标识
            @ApiModelProperty("用户标识")
            @Alias("用户标识")
    private String uid;

            // 删除标识
            @ApiModelProperty("删除标识")
            @Alias("删除标识")
    private Integer deleted;
}