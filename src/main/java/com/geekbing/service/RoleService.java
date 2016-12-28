package com.geekbing.service;

import com.geekbing.entity.Role;
import com.geekbing.model.RoleQo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    Role findById(Long id);


    Role create(Role role);


    Role update(Role role);

    void delete(Long id);

    List<Role> findAll();

    Page<Role> findPage(RoleQo roleQo);
}
