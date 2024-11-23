package com.designershop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponUserProfileId implements Serializable {

    @Column(name = "coupon_id", nullable = false)
    private int couponId;

    @Column(name = "user_id", nullable = false, length = 12)
    private String userId;

    @Override
    public int hashCode() {
        return Objects.hash(couponId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CouponUserProfileId other = (CouponUserProfileId) obj;
        return Objects.equals(couponId, other.couponId) && Objects.equals(userId, other.userId);
    }
}