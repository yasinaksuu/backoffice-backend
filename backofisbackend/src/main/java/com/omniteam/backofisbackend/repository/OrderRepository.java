package com.omniteam.backofisbackend.repository;


import com.omniteam.backofisbackend.entity.Order;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
