package com.omniteam.backofisbackend.repository.customspecification;

import com.omniteam.backofisbackend.entity.Order;
import com.omniteam.backofisbackend.entity.OrderDetail;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;

public class OrderSpec {
    public static Specification<Order> getAllByFilter(int customerId, String status, LocalDateTime startDate,LocalDateTime endDate) {
        return Specification
                .where(getOrdersByCustomerId(customerId))
                .or(getOrdersByStatus(status))
                .or(getOrdersByDateTimeRange(startDate,endDate)
        );
    }

    public static Specification<Order> getOrdersByCustomerId(int customerId){
        return (root, query, criteriaBuilder) -> {
            ListJoin<Order, OrderDetail> orderDetailListJoin = root.joinList("orderDetails");
            if(customerId == 0){
                return criteriaBuilder.conjunction();
            }
            Predicate equalPredicate = criteriaBuilder.equal(root.get("customer"), customerId);
            return equalPredicate;
        };
    }

    public static Specification<Order> getOrdersByStatus(String status){
        return (root, query, criteriaBuilder) -> {
            if(status == null || status.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            Predicate equalPredicate = criteriaBuilder.equal(root.get("status"), status);
            return equalPredicate;
        };
    }

    public static Specification<Order> getOrdersByDateTimeRange(LocalDateTime startDate,LocalDateTime endDate){
        return (root, query, criteriaBuilder) -> {
            if(startDate==null && endDate ==null){
                return criteriaBuilder.conjunction();
            }
            Predicate betweenPredicate = criteriaBuilder.between(root.get("createdDate"),startDate,endDate);
            return betweenPredicate;
        };
    }
}
