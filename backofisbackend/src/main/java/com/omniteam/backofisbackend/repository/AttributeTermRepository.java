package com.omniteam.backofisbackend.repository;

import com.omniteam.backofisbackend.entity.AttributeTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeTermRepository extends JpaRepository<AttributeTerm,Integer> {
}
