package com.geekbing.repository;

import com.geekbing.entity.Role;
import com.geekbing.expand.jpa.repository.ExpandJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ExpandJpaRepository<Role, Long> {
}
