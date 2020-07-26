package cn.medsci.framework.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author weihao.xiao
 */
@Data
@Accessors(chain = true)
public class GetUserByIdRequest {

    private Long id;
}
