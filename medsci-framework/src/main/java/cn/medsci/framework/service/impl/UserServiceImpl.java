package cn.medsci.framework.service.impl;

import cn.medsci.framework.dto.*;
import cn.medsci.framework.entity.User;
import cn.medsci.framework.mapper.UserMapper;
import cn.medsci.framework.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author arvin
 * @since 2020-07-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Integer saveUser(SaveUserRequest request) {
        User u = new User();
        BeanUtils.copyProperties(request,u);
        return baseMapper.insert(u);
    }

    @Override
    public GetUserByIdResponse getUserById(GetUserByIdRequest request) {
        GetUserByIdResponse data = new GetUserByIdResponse();
        User user = baseMapper.selectById(request.getId());
        BeanUtils.copyProperties(user,data);
        return data;
    }

    @Override
    public Page<GetUserPageResponse> getUserPage(GetUserPageRequest request) {
        Page<GetUserPageResponse> data = new Page<>();
        Page<User> page = new Page<>(request.getPageIndex(), request.getPageSize());
        IPage<User> selectPage = baseMapper.selectPage(page, null);
        List<GetUserPageResponse> records = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(selectPage.getRecords())){
            for (User u : selectPage.getRecords()) {
                GetUserPageResponse item = new GetUserPageResponse();
                BeanUtils.copyProperties(u,item);
                records.add(item);
            }
        }
        return data.setRecords(records).setTotal(page.getTotal());
    }

    @Override
    public Integer updateUser(UpdateUserByIdRequest request) {
        User user = baseMapper.selectById(request.getId());
        BeanUtils.copyProperties(user,request);
        return baseMapper.updateById(user);
    }

    @Override
    public Boolean batchDealUser(BatchDealUserRequest request) {
        return removeByIds(request.getIds());
    }

}
