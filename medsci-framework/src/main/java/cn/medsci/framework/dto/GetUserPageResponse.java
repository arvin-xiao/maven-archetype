package cn.medsci.framework.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author arvin
 */
@Data
@Accessors(chain = true)
public class GetUserPageResponse {

    /**
     * id
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 删除状态(0:未删除，1:已删除)
     */
    private Boolean deleted;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建人名称
     */
    private String createdName;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private Long updatedBy;

    /**
     * 更新人名称
     */
    private String updatedName;

    /**
     * 更新时间
     */
    private Date updatedTime;

}
