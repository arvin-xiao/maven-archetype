package cn.medsci.framework.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author arvin
 */
@Data
@Accessors(chain = true)
public class BatchDealUserRequest {

    private List<Long> ids;
}
