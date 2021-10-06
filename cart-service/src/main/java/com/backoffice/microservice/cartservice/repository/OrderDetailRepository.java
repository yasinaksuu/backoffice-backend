package com.backoffice.microservice.cartservice.repository;

import com.backoffice.microservice.cartservice.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> getOrderDetailsByOrderIdAndIsActiveIsTrue(Integer orderId);
}
