package com.designershop.users.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ivan Wang
 * @version 1.0
 * @date 2024/12/22
 */
@Getter
@Setter
public class UpdateUserRequestModel {

    @NotBlank
    private String account;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^09\\d{8}$")
    private String phoneNo;

    private String name;

    private String gender;

    private String birthday;

    private String idCardNo;

    private String homeNo;

    private String image;

    @NotBlank
    private String termsCheckBox;
}
