package com.geekbing.repository;

import com.geekbing.entity.Department;
import com.geekbing.expand.jpa.repository.ExpandJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends ExpandJpaRepository<Department, Long> {
}
