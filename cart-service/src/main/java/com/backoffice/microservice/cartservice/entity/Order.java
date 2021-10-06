package com.backoffice.microservice.cartservice.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  ="order_id")
    private Integer orderId;

    @Column(name  ="user_id")
    private Integer userId;

    @Column(name  ="customer_id")
    private Integer customerId;

    @Column(name="status")
    private String status;
}
