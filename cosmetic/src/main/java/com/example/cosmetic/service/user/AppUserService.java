package com.example.cosmetic.service.user;

import com.example.cosmetic.dto.JwtResponseUserDetail;
import com.example.cosmetic.model.user.AppUser;
import com.example.cosmetic.model.user.UserRole;
import com.example.cosmetic.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/*
JWTUserDetailsService implements the Spring Security UserDetailsService interface.
It overrides the loadUserByUsername for fetching user details from the database using the username.
The Spring Security Authentication Manager calls this method for getting the user details from the database
when authenticating the user details provided by the user. Here we are getting the user details from a hardcoded
User List. In the next tutorial we will be adding the DAO implementation for fetching User Details from the Database.
Also the password for a user is stored in encrypted format using BCrypt.
Previously we have seen Spring Boot Security - Password Encoding Using Bcrypt.
Here using the Online Bcrypt Generator you can generate the Bcrypt for a password.
 */
@Service
public class AppUserService implements IAppUserService {


    @Autowired
    private IAppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByName(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User name or password is wrong");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        for (UserRole userRole : appUser.getUserRoles()) {
            grantList.add(new SimpleGrantedAuthority(userRole.getAppRole().getName()));
        }
        UserDetails userDetails = new JwtResponseUserDetail(
                appUser.getUserName(),
                appUser.getPassword(),
                appUser.getIsOnline(),
                grantList);
        appUserRepository.updateAppUserIsOnline(appUser);
        return userDetails;
    }

    @Override
    public Boolean existsByUsername(String userName) {
        AppUser appUser = appUserRepository.findAppUserByName(userName);
        return appUser != null;
    }

    @Override
    public Boolean createNewAppUser(AppUser appUser, String role) {
        Integer amountAppUserCreated = appUserRepository.createNewAppUser(appUser);
        AppUser currentAppUser = appUserRepository.findAppUserByName(appUser.getUserName());
        appUserRepository.insertRoleForCustomer(2, currentAppUser.getId());
        return amountAppUserCreated > 0;
    }

    @Override
    public Boolean logout(String userName) {
        return appUserRepository.updateAppUserIsOffline(userName) > 0;
    }

    @Override
    public Integer findAppUserIdByUserName(String userName) {
        return appUserRepository.findIdByUserName(userName);
    }

    @Override
    public AppUser findById(Integer idUser) {
        return appUserRepository.findById(idUser).orElse(null);
    }

    @Override
    public AppUser findByName(String userName) {
        return appUserRepository.findAppUserByName(userName);
    }
}