package com.designershop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designershop.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
