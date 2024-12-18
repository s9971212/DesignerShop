package com.designershop.repositories;

import com.designershop.entities.ProductBrand;
import com.designershop.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {

    @Query(value = "SELECT * FROM product_brand WHERE LOWER(brand) LIKE LOWER(CONCAT('%', :brand, '%'))", nativeQuery = true)
    ProductBrand findByBrandIgnoreCase(@Param("brand") String brand);

    @Query(value = "SELECT * FROM product_brand WHERE brand_id IN (:brandIds)", nativeQuery = true)
    List<ProductBrand> findByBrandIds(@Param("brandIds") List<String> brandIds);
}
