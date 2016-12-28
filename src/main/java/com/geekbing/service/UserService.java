package com.geekbing.service;

import com.geekbing.entity.User;
import com.geekbing.model.UserQo;
import com.geekbing.redis.BaseRedis;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends BaseRedis<User> {
    User findById(Long id);

    List<User> findAll();

    User create(User user);

    User update(User user);

    void delete(Long id);

    Page<User> findPage(UserQo userQo);
}