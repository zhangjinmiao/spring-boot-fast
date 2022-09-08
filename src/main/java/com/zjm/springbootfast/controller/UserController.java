package com.zjm.springbootfast.controller;

import com.zjm.springbootfast.common.api.ApiResult;
import com.zjm.springbootfast.common.controller.BaseController;
import com.zjm.springbootfast.common.groups.Add;
import com.zjm.springbootfast.common.groups.Update;
import com.zjm.springbootfast.common.pagination.Paging;
import com.zjm.springbootfast.entity.User;
import com.zjm.springbootfast.param.UserPageParam;
import com.zjm.springbootfast.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * user 控制器
 *
 * @author zjm
 * @since 2022-09-08
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "userAPI", tags = {"user"})
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 注册user
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册user", response = ApiResult.class)
    public ApiResult<Boolean> registerUser(@Validated(Add.class) @RequestBody User user) throws Exception {
        boolean flag = userService.saveUser(user);
        return ApiResult.result(flag);
    }

    /**
     * 修改user
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改user", response = ApiResult.class)
    public ApiResult<Boolean> updateUser(@Validated(Update.class) @RequestBody User user) throws Exception {
        boolean flag = userService.updateUser(user);
        return ApiResult.result(flag);
    }

    /**
     * 删除user
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除user", response = ApiResult.class)
    public ApiResult<Boolean> deleteUser(@PathVariable("id") Long id) throws Exception {
        boolean flag = userService.deleteUser(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取user详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "user详情", response = User.class)
    public ApiResult<User> getUser(@PathVariable("id") Long id) throws Exception {
        User user = userService.getById(id);
        return ApiResult.ok(user);
    }

    /**
     * user分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "user分页列表", response = User.class)
    public ApiResult<Paging<User>> getUserPageList(@Validated @RequestBody UserPageParam userPageParam) throws Exception {
        Paging<User> paging = userService.getUserPageList(userPageParam);
        return ApiResult.ok(paging);
    }

}

