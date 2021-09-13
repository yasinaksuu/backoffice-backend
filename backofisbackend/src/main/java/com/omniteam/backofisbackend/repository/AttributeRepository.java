package com.omniteam.backofisbackend.repository;


import com.omniteam.backofisbackend.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute,Integer> {


}
