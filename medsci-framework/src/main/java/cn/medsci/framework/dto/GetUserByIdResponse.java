package cn.medsci.framework.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author weihao.xiao
 */
@Data
@Accessors(chain = true)
public class GetUserByIdResponse {

    private Long id;

    /**
     * 账户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别(0:女，1:男，2:保密)
     */
    private Integer gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;
}
