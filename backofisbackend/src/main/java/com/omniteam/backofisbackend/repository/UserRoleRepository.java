package com.omniteam.backofisbackend.repository;


import com.omniteam.backofisbackend.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
}
