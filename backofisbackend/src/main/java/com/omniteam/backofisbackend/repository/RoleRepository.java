package com.omniteam.backofisbackend.repository;


import com.omniteam.backofisbackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(
            value = "select r from UserRole ur, Role r where ur.role.roleId = r.roleId and ur.user.userId =:userId and r.isActive=true"
    )
    List<Role> findRolesByUserId(Integer userId);
}
