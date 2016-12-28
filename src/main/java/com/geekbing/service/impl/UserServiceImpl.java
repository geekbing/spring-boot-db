package com.geekbing.service.impl;

import com.geekbing.entity.User;
import com.geekbing.expand.jpa.parameter.LinkEnum;
import com.geekbing.expand.jpa.parameter.Operator;
import com.geekbing.expand.jpa.parameter.PredicateBuilder;
import com.geekbing.model.UserQo;
import com.geekbing.redis.BaseRedisImpl;
import com.geekbing.repository.UserRepository;
import com.geekbing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by bing on 28/12/2016.
 */
@Service
public class UserServiceImpl extends BaseRedisImpl<User> implements UserService {
    @Autowired
    private UserRepository userRepository;

    private static final String keyHead = "mysql:get:user:";

    public User findById(Long id) {
        User user = this.get(keyHead + id, User.class);
        if (user == null) {
            user = userRepository.findOne(id);
            if (user != null) {
                this.add(keyHead + id, 30L, user);
            }
        }
        return user;
    }

    public List<User> findAll() {
        List<User> users = this.getList("mysql:findAll:role");
        if (users == null) {
            users = userRepository.findAll();
            if (users != null) {
                this.add("mysql:findAll:role", 30L, users);
            }
        }
        return users;
    }

    public User create(User user) {
        User newUser = userRepository.save(user);
        if (newUser != null)
            this.add(keyHead + newUser.getId(), 30L, newUser);
        return newUser;
    }

    public User update(User user) {
        if (user != null) {
            this.delete(keyHead + user.getId());
            this.add(keyHead + user.getId(), 30L, user);
        }
        return userRepository.save(user);
    }

    public void delete(Long id) {
        this.delete(keyHead + id);
        userRepository.delete(id);
    }

    public Page<User> findPage(UserQo userQo) {
        Pageable pageable = new PageRequest(userQo.getPage(), userQo.getSize(), new Sort(Sort.Direction.ASC, "id"));

        PredicateBuilder pb = new PredicateBuilder();

        if (!StringUtils.isEmpty(userQo.getName())) {
            pb.add("name", "%" + userQo.getName() + "%", LinkEnum.LIKE);
        }
        if (!StringUtils.isEmpty(userQo.getCreatedateStart())) {
            pb.add("createdate", userQo.getCreatedateStart(), LinkEnum.GE);
        }
        if (!StringUtils.isEmpty(userQo.getCreatedateEnd())) {
            pb.add("createdate", userQo.getCreatedateEnd(), LinkEnum.LE);
        }

        return userRepository.findAll(pb.build(), Operator.AND, pageable);
    }
}
