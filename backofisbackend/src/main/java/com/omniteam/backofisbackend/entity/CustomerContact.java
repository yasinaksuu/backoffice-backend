package com.omniteam.backofisbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="customer_contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor()
public class CustomerContact extends  BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_contact_id")
    private Integer customerContactId;


    @Column(name = "contact_description")
    private String contactDescription;

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "contact_value")
    private String contactValue;

    @Column(name = "is_default")
    private Boolean isDefault;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;


    @JoinColumn(name = "country_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;///@OneToOneda yapılabilir***

    @JoinColumn(name = "city_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;///@OneToOneda yapılabilir***


    @JoinColumn(name = "district_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private District district;///@OneToOneda yapılabilir***








}
