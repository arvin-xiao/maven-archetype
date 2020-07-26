package cn.medsci.framework.controller;

import cn.medsci.framework.dto.*;
import cn.medsci.framework.entity.User;
import cn.medsci.framework.service.UserService;
import cn.medsci.framework.validation.ValidationResult;
import cn.medsci.framework.validation.ValidationUtils;
import cn.medsci.framework.vo.BaseResponse;
import cn.medsci.framework.vo.PageResponse;
import cn.medsci.framework.vo.ResultStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author arvin
 * @since 2020-07-24
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "新增用户")
    @PostMapping(value = "save")
    public ResponseEntity<Boolean> save(@RequestBody User user) {
        boolean save = userService.save(user);
        return ResponseEntity.ok(save);
    }

    @ApiOperation(value = "新增用户")
    @PostMapping(path = "saveUser")
    public BaseResponse saveUser(@RequestBody SaveUserRequest request) {
        BaseResponse response = new BaseResponse();
        ValidationResult result = ValidationUtils.validateEntity(request);
        if (result.isHasErrors()) {
            String msg = result.getErrorMsg().values().stream().findFirst().orElse(ResultStatus.BAD_REQUEST.getMsg());
            return response.setStatus(ResultStatus.BAD_REQUEST.getCode()).setMessage(msg);
        }
        try {
            Integer count = userService.saveUser(request);
            if (count != 1) {
                return response.setStatus(ResultStatus.ERROR.getCode()).setMessage(ResultStatus.ERROR.getMsg());
            }
            return response;
        } catch (Exception e) {
            log.error("新增用户失败:{}",e.getMessage());
            return response.setStatus(ResultStatus.ERROR.getCode()).setMessage("新增用户失败!");
        }
    }

    @ApiOperation(value = "根据用户id获取详情")
    @PostMapping(path = "getUserById")
    public BaseResponse<GetUserByIdResponse> getUserById(@RequestBody GetUserByIdRequest request) {
        BaseResponse<GetUserByIdResponse> response = new BaseResponse<>();
        ValidationResult result = ValidationUtils.validateEntity(request);
        if (result.isHasErrors()) {
            String msg = result.getErrorMsg().values().stream().findFirst().orElse(ResultStatus.BAD_REQUEST.getMsg());
            return response.setStatus(ResultStatus.BAD_REQUEST.getCode()).setMessage(msg);
        }
        try {
            GetUserByIdResponse data = userService.getUserById(request);
            if (data == null) {
                return response.setStatus(ResultStatus.ERROR.getCode()).setMessage(ResultStatus.ERROR.getMsg());
            }
            return response.setData(data);
        } catch (Exception e) {
            log.error("根据用户id获取详情失败:{}",e.getMessage());
            return response.setStatus(ResultStatus.ERROR.getCode()).setMessage("根据用户id获取详情失败!");
        }
    }

    @ApiOperation(value = "查询用户分页")
    @PostMapping(path = "getUserPage")
    public PageResponse<List<GetUserPageResponse>> getUserPage(@RequestBody GetUserPageRequest request) {
        PageResponse<List<GetUserPageResponse>> response = new PageResponse<>();
        ValidationResult result = ValidationUtils.validateEntity(request);
        if (result.isHasErrors()) {
            String msg = result.getErrorMsg().values().stream().findFirst().orElse(ResultStatus.BAD_REQUEST.getMsg());
            return response.setStatus(ResultStatus.BAD_REQUEST.getCode()).setMessage(msg);
        }
        try {
            Page<GetUserPageResponse> page = userService.getUserPage(request);
            if (CollectionUtils.isEmpty(page.getRecords())) {
                return response.setStatus(ResultStatus.NO_CONTENT.getCode()).setMessage(ResultStatus.NO_CONTENT.getMsg());
            }
            return response.setTotalSize(page.getTotal()).setData(page.getRecords());
        } catch (Exception e) {
            log.error("查询用户分页失败:{}",e.getMessage());
            return response.setStatus(ResultStatus.ERROR.getCode()).setMessage("查询用户分页失败!");
        }
    }

    @ApiOperation(value = "修改用户")
    @PostMapping(path = "updateUserById")
    public BaseResponse updateUserById(@RequestBody UpdateUserByIdRequest request) {
        BaseResponse response = new BaseResponse();
        ValidationResult result = ValidationUtils.validateEntity(request);
        if (result.isHasErrors()) {
            String msg = result.getErrorMsg().values().stream().findFirst().orElse(ResultStatus.BAD_REQUEST.getMsg());
            return response.setStatus(ResultStatus.BAD_REQUEST.getCode()).setMessage(msg);
        }
        try {
            Integer count = userService.updateUser(request);
            if (count != 1) {
                return response.setStatus(ResultStatus.ERROR.getCode()).setMessage(ResultStatus.ERROR.getMsg());
            }
            return response;
        } catch (Exception e) {
            log.error("修改用户失败:{}",e.getMessage());
            return response.setStatus(ResultStatus.ERROR.getCode()).setMessage("修改用户失败!");
        }
    }

    @ApiOperation(value = "用户批量处理")
    @PostMapping(path = "batchDealUser")
    public BaseResponse batchDealUser(@RequestBody BatchDealUserRequest request) {
        BaseResponse response = new BaseResponse();
        ValidationResult result = ValidationUtils.validateEntity(request);
        if (result.isHasErrors()) {
            String msg = result.getErrorMsg().values().stream().findFirst().orElse(ResultStatus.BAD_REQUEST.getMsg());
            return response.setStatus(ResultStatus.BAD_REQUEST.getCode()).setMessage(msg);
        }
        try {
            Boolean boo = userService.batchDealUser(request);
            if (boo == null || !boo) {
                return response.setStatus(ResultStatus.ERROR.getCode()).setMessage(ResultStatus.ERROR.getMsg());
            }
            return response;
        } catch (Exception e) {
            log.error("用户批量处理失败:{}",e.getMessage());
            return response.setStatus(ResultStatus.ERROR.getCode()).setMessage("用户批量处理失败!");
        }
    }

}

