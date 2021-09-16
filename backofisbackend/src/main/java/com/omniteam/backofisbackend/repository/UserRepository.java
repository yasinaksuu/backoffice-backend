package com.omniteam.backofisbackend.repository;


import com.omniteam.backofisbackend.entity.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByEmailAndIsActive(String email, Boolean isActive);
}
