package com.partner.boot.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import com.baomidou.mybatisplus.annotation.TableName;
    import java.io.Serializable;
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
* @since 2023-04-23
*/
    @Getter
    @Setter
    @TableName("sys_dict")
@ApiModel(value = "Dict对象", description = "")
    public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            // 编码
            @ApiModelProperty("编码")
            @Alias("编码")
    private String code;

            // 内容
            @ApiModelProperty("内容")
            @Alias("内容")
    private String value;

            // 类型
            @ApiModelProperty("类型")
            @Alias("类型")
    private String type;

            // 删除
            @ApiModelProperty("删除")
            @Alias("删除")
    private Integer deleted;
}