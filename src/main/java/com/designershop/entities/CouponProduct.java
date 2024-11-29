package com.designershop.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coupons_products")
public class CouponProduct {

    /**
     * 優惠券商品限定ID，包含優惠券ID與商品ID
     */
    @EmbeddedId
    private CouponProductId id;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CouponProduct other = (CouponProduct) obj;
        return Objects.equals(id, other.id);
    }
}
