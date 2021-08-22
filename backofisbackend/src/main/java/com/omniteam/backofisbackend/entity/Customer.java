package com.omniteam.backofisbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="customers")
@Data
@NoArgsConstructor
@AllArgsConstructor()
public class Customer extends  BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "nation_number")
    private String nationNumber;

    @Column(name = "first_name")
    private String firstName;


    @Column(name="last_name")
    private String lastName;


    @OneToMany(mappedBy = "customer")
    private List<CustomerContact> customerContacts;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
