package com.omniteam.backofisbackend.repository;

import com.omniteam.backofisbackend.entity.Customer;
import com.omniteam.backofisbackend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

  Page<Product> findProductsByProductNameContainingOrDescriptionContaining(String productName, String description, Pageable pageable);

}
