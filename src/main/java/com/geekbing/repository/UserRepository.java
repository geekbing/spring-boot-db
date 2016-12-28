package com.geekbing.repository;


import com.geekbing.entity.User;
import com.geekbing.expand.jpa.repository.ExpandJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by bing on 28/12/2016.
 */
@Repository
public interface UserRepository extends ExpandJpaRepository<User, Long> {

    @Query("select t from User t where t.name =?1 and t.email =?2")
    User findByNameAndEmail(String name, String email);

    @Query("select t from User t where t.name like :name")
    Page<User> findByName(@Param("name") String name, Pageable pageRequest);
}
