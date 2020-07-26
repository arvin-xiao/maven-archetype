package cn.medsci.framework.service;

import cn.medsci.framework.dto.*;
import cn.medsci.framework.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author arvin
 * @since 2020-07-24
 */
public interface UserService extends IService<User> {

    Integer saveUser(SaveUserRequest request);

    GetUserByIdResponse getUserById(GetUserByIdRequest request);

    Page<GetUserPageResponse> getUserPage(GetUserPageRequest request);

    Integer updateUser(UpdateUserByIdRequest request);

    Boolean batchDealUser(BatchDealUserRequest request);
}
