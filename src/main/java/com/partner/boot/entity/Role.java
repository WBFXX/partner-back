package com.partner.boot.entity;

    import com.baomidou.mybatisplus.annotation.FieldFill;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableField;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableName;
    import java.io.Serializable;
    import java.time.LocalDateTime;
    import java.util.List;

    import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
    import lombok.Getter;
    import lombok.Setter;

/**
* <p>
    * 
    * </p>
*
* @author 现计科1901武泊帆
* @since 2023-04-19
*/
    @Getter
    @Setter
    @TableName("sys_role")
@ApiModel(value = "Role对象", description = "")
    public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            // 名称
            @ApiModelProperty("名称")
            @Alias("名称")
    private String name;

            // 唯一标识
            @ApiModelProperty("唯一标识")
            @Alias("唯一标识")
    private String flag;

            // 逻辑删除
            @ApiModelProperty("逻辑删除")
            @Alias("逻辑删除")
    private Integer deleted;

            // 创建时间
            @ApiModelProperty("创建时间")
            @Alias("创建时间")
            @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

            // 更新时间
            @ApiModelProperty("更新时间")
            @Alias("更新时间")
    private LocalDateTime updateTime;

            @TableField(exist = false)
    private List<Integer> permissionIds;
}