package com.example.cosmetic.dto;

import com.example.cosmetic.common.ValidateAppUser;
import com.example.cosmetic.model.user.UserRole;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class AppUserDto implements Validator {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String phone;
    public AppUserDto() {
    }

    public AppUserDto(Integer id, String userName, String password, String confirmPassword, String fullName, String email, String phone, String address, String image, Boolean flagDeleted, Boolean flagOnline, Set<UserRole> userRoles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AppUserDto appUserDto = (AppUserDto) target;
        ValidateAppUser.checkValidateAppUserName(appUserDto.getUserName(),errors);
        ValidateAppUser.checkValidateAppUserPassword(appUserDto.getPassword(),errors);
    }
}
