package com.zjm.springbootfast.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjm.springbootfast.common.exception.BusinessException;
import com.zjm.springbootfast.common.pagination.PageInfo;
import com.zjm.springbootfast.common.pagination.Paging;
import com.zjm.springbootfast.common.service.impl.BaseServiceImpl;
import com.zjm.springbootfast.entity.User;
import com.zjm.springbootfast.mapper.UserMapper;
import com.zjm.springbootfast.param.UserPageParam;
import com.zjm.springbootfast.service.MailService;
import com.zjm.springbootfast.service.UserService;
import com.zjm.springbootfast.util.PasswordUtil;
import com.zjm.springbootfast.util.SaltUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * user 服务实现类
 *
 * @author zjm
 * @since 2022-09-08
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailService mailService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveUser(User user) throws Exception {
        // 校验用户名是否存在
        boolean isExists = isExistsByName(user.getName());
        if (isExists) {
            throw new BusinessException("用户名已存在");
        }

        // 校验邮箱是否已存在
        boolean isExistsEmail = isExistsByEmail(user.getEmail());
        if (isExistsEmail) {
            throw new BusinessException("邮箱已使用");
        }
        // 生成盐值
        String salt = SaltUtil.generateSalt();
        String password = user.getPassword();
        // 密码加密
        user.setSalt(salt);
        user.setPassword(PasswordUtil.encrypt(password, salt));

        // 保存用户
        boolean result = super.save(user);

        // 异步发送邮件
        if (result) {
            mailService.sendSimpleMail(user.getEmail(),"注册邮件", "恭喜注册成功!!!");
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(User user) throws Exception {
        // 获取用户
        User updateUser = getById(user.getId());
        if (updateUser == null) {
            throw new BusinessException("修改的用户不存在");
        }
        // 修改系统用户
        updateUser.setName(user.getName())
                .setPassword(user.getPhone())
                .setState(user.getState())
                .setUpdateTime(new Date());

        return super.updateById(updateUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<User> getUserPageList(UserPageParam userPageParam) throws Exception {
        Page<User> page = new PageInfo<>(userPageParam, OrderItem.desc(getLambdaColumn(User::getCreateTime)));
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        IPage<User> iPage = userMapper.selectPage(page, wrapper);
        return new Paging<User>(iPage);
    }

    @Override
    public boolean isExistsByName(String name) throws Exception {
        User selectSysUser = new User().setName(name);
        return userMapper.selectCount(new QueryWrapper<>(selectSysUser)) > 0;
    }

    @Override
    public boolean isExistsByEmail(String email) throws Exception {
        User selectSysUser = new User().setEmail(email);
        return userMapper.selectCount(new QueryWrapper<>(selectSysUser)) > 0;
    }

}
