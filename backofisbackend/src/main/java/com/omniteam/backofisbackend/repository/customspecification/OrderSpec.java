package com.omniteam.backofisbackend.repository.customspecification;

import com.omniteam.backofisbackend.entity.Order;
import com.omniteam.backofisbackend.entity.OrderDetail;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.ListJoin;
import java.time.LocalDateTime;

public class OrderSpec {
    public static Specification<Order> getAllByFilter(int customerId, String status, LocalDateTime startDate,LocalDateTime endDate) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            ListJoin<Order, OrderDetail> orderDetailListJoin = root.joinList("orderDetails");
          return criteriaBuilder.and(
                  criteriaBuilder.equal(root.get("customer"),customerId),
                  criteriaBuilder.equal(root.get("status"),status),
                  criteriaBuilder.between(root.get("createdDate"),startDate,endDate)
          );
        };
    }
}
