package com.geekbing.service.impl;

import com.geekbing.entity.Department;
import com.geekbing.expand.jpa.parameter.LinkEnum;
import com.geekbing.expand.jpa.parameter.Operator;
import com.geekbing.expand.jpa.parameter.PredicateBuilder;
import com.geekbing.model.DepartmentQo;
import com.geekbing.redis.BaseRedisImpl;
import com.geekbing.repository.DepartmentRepository;
import com.geekbing.service.DepartmentService;
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
public class DepartmentServiceImpl extends BaseRedisImpl<Department> implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department findById(Long id) {
        return departmentRepository.findOne(id);
    }

    public Department create(Department deparment) {
        return departmentRepository.save(deparment);
    }

    public Department update(Department role) {
        return departmentRepository.save(role);
    }

    public void delete(Long id) {
        departmentRepository.delete(id);
    }

    public List<Department> findAll() {
        List<Department> deparments = this.getList("mysql:findAll:deparment");
        if (deparments == null) {
            deparments = departmentRepository.findAll();
            if (deparments != null)
                this.add("mysql:findAll:deparment", 5L, deparments);
        }
        return deparments;
    }

    public Page<Department> findPage(DepartmentQo deparmentQo) {
        Pageable pageable = new PageRequest(deparmentQo.getPage(), deparmentQo.getSize(), new Sort(Sort.Direction.ASC, "id"));

        PredicateBuilder pb = new PredicateBuilder();

        if (!StringUtils.isEmpty(deparmentQo.getName())) {
            pb.add("name", "%" + deparmentQo.getName() + "%", LinkEnum.LIKE);
        }

        Page<Department> pages = departmentRepository.findAll(pb.build(), Operator.AND, pageable);
        return pages;
    }

}
