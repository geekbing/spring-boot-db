package com.geekbing.service;

import com.geekbing.entity.Department;
import com.geekbing.model.DepartmentQo;
import com.geekbing.redis.BaseRedis;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService extends BaseRedis<Department> {

    Department findById(Long id);


    Department create(Department deparment);


    Department update(Department role);

    void delete(Long id);

    List<Department> findAll();

    Page<Department> findPage(DepartmentQo deparmentQo);
}
