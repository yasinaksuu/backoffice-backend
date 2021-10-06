package com.backoffice.microservice.cartservice.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Integer orderDetailId;

    @Column(name  ="order_id")
    private Integer orderId;

    @Column(name  ="product_id")
    private Integer productId;

    @Column(name  ="product_price_id")
    private Integer productPriceId;

    @Column(name="status")
    private String status;

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

}
