package com.zjm.springbootfast.service;

import com.zjm.springbootfast.common.pagination.Paging;
import com.zjm.springbootfast.common.service.BaseService;
import com.zjm.springbootfast.entity.User;
import com.zjm.springbootfast.param.UserPageParam;

/**
 * user 服务类
 *
 * @author zjm
 * @since 2022-09-08
 */
public interface UserService extends BaseService<User> {

    /**
     * 保存
     *
     * @param user
     * @return
     * @throws Exception
     */
    boolean saveUser(User user) throws Exception;

    /**
     * 修改
     *
     * @param user
     * @return
     * @throws Exception
     */
    boolean updateUser(User user) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteUser(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param userPageParam
     * @return
     * @throws Exception
     */
    Paging<User> getUserPageList(UserPageParam userPageParam) throws Exception;

    /**
     * 判断用户名是否存在
     *
     * @param name
     * @return
     * @throws Exception
     */
    boolean isExistsByName(String name) throws Exception;

    /**
     * 判断邮箱是否存在
     *
     * @param email
     * @return
     * @throws Exception
     */
    boolean isExistsByEmail(String email) throws Exception;
}
