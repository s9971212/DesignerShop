package com.designershop.orders.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadOrderDeliveryResponseModel {

    @NotBlank
    private String deliveryId;

    @NotBlank
    private String fullAddress;

    @NotBlank
    private String address;

    private String district;

    private String city;

    private String state;

    private String postalCode;

    @NotBlank
    private String nation;

    @NotBlank
    private String contactPhone;

    @NotBlank
    private String contactName;

    @NotBlank
    private String isDefault;
}
