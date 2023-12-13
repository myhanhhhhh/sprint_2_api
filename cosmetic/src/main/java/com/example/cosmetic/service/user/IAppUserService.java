package com.example.cosmetic.service.user;


import com.example.cosmetic.model.user.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends UserDetailsService {
    Boolean existsByUsername(String userName);

    Boolean createNewAppUser(AppUser appUser, String role);

    Boolean logout(String userName);
    Integer findAppUserIdByUserName(String userName);

    AppUser findById(Integer idUser);

    AppUser findByName(String userName);
}