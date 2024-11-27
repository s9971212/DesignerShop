package com.designershop.admin.coupons.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminUpdateCouponRequestModel {

    private String code;

    @NotBlank
    private String discountType;

    @NotBlank
    private String discountValue;

    private String minimumOrderPrice;

    private String usageLimit;

    private String description;

    private String image;

    @NotBlank
    private String startDate;

    @NotBlank
    private String endDate;

    @NotBlank
    private String isActive;

    private List<String> userIds;

    private List<String> categoryIds;

    private List<String> brandIds;

    private List<String> productIds;
}