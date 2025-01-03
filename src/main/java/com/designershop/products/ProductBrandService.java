package com.designershop.products;

import com.designershop.entities.ProductBrand;
import com.designershop.repositories.ProductBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Ivan Wang
 * @version 1.0
 * @date 2024/12/22
 */
@Service
@RequiredArgsConstructor
public class ProductBrandService {

    private final ProductBrandRepository productBrandRepository;

    public ProductBrand readProductBrand(String brand) {
        ProductBrand productBrand = productBrandRepository.findByBrandIgnoreCase(brand);
        if (Objects.isNull(productBrand)) {
            productBrand = new ProductBrand();
            productBrand.setBrand(brand);
            productBrandRepository.save(productBrand);
        }

        return productBrand;
    }
}
