package com.nscs.SBMaster.repository;

import com.nscs.SBMaster.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
