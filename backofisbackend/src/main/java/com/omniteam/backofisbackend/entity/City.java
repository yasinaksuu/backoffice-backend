package com.omniteam.backofisbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="cities")
@Data
@NoArgsConstructor
@AllArgsConstructor()
public class City extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name")
    private String cityName;

    @JoinColumn(name="country_id")
    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<CustomerContact> customerContacts;


    @OneToMany(mappedBy = "city")
    private List<User> users;
}
