package com.geekbing.controller;

import com.geekbing.entity.User;
import com.geekbing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by bing on 28/12/2016.
 */
@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "Hello, World";
    }

    @RequestMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @RequestMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

}
