package com.omniteam.backofisbackend.repository;

import com.omniteam.backofisbackend.entity.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerContactRepository  extends JpaRepository<CustomerContact,Integer> {
}
