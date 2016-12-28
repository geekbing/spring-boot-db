package com.geekbing.service.impl;

import com.geekbing.entity.Role;
import com.geekbing.expand.jpa.parameter.LinkEnum;
import com.geekbing.expand.jpa.parameter.Operator;
import com.geekbing.expand.jpa.parameter.PredicateBuilder;
import com.geekbing.model.RoleQo;
import com.geekbing.redis.BaseRedisImpl;
import com.geekbing.repository.RoleRepository;
import com.geekbing.service.RoleService;
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
public class RoleServiceImpl extends BaseRedisImpl<Role> implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }


    public Role create(Role role) {
        return roleRepository.save(role);
    }


    public Role update(Role role) {
        return roleRepository.save(role);
    }


    public void delete(Long id) {
        roleRepository.delete(id);
    }

    public List<Role> findAll() {
        List<Role> roleList = this.getList("mysql:findAll:role");
        if (roleList == null) {
            roleList = roleRepository.findAll();
            if (roleList != null)
                this.add("mysql:findAll:role", 5L, roleList);
        }
        return roleList;
    }

    public Page<Role> findPage(RoleQo roleQo) {
        Pageable pageable = new PageRequest(roleQo.getPage(), roleQo.getSize(), new Sort(Sort.Direction.ASC, "id"));

        PredicateBuilder pb = new PredicateBuilder();

        if (!StringUtils.isEmpty(roleQo.getName())) {
            pb.add("name", "%" + roleQo.getName() + "%", LinkEnum.LIKE);
        }

        Page<Role> pages = roleRepository.findAll(pb.build(), Operator.AND, pageable);
        return pages;
    }
}
