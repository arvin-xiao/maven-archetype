package cn.medsci.framework.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author arvin
 */
@Data
public class PageDto {

    @ApiModelProperty(value = "pageIndex从0开始", required = true)
    @NotNull
    @Min(value = 0, message = "页码从0开始")
    private Integer pageIndex;

    @ApiModelProperty(value = "每页的条数，必须大于0", required = true)
    @NotNull
    @Min(value = 1, message = "每页记录数大于0")
    private Integer pageSize;

}
