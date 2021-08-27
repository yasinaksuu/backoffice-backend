package com.omniteam.backofisbackend.repository;


import com.omniteam.backofisbackend.entity.ProductAttributeTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeTermRepository extends JpaRepository<ProductAttributeTerm,Integer> {
}