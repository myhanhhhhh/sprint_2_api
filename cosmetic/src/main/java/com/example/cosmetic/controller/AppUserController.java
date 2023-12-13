package com.example.cosmetic.controller;

import com.example.cosmetic.config.JwtTokenUtil;
import com.example.cosmetic.dto.AppUserDto;
import com.example.cosmetic.model.user.AppUser;
import com.example.cosmetic.model.user.JwtResponse;
import com.example.cosmetic.service.user.IAppUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class AppUserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final String LOGIN_FAILED = "Đăng nhập thất bại";

    @PostMapping("/login-by-username")
    public ResponseEntity<Object> loginByAccount(@Valid @RequestBody AppUserDto appUserDto,
                                                 BindingResult bindingResult) {

        new AppUserDto().validate(appUserDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(LOGIN_FAILED);
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    appUserDto.getUserName(), appUserDto.getPassword()));
        } catch (DisabledException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Tài khoản của bạn đã bị vô hiệu hoá");
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(LOGIN_FAILED);
        }

        AppUser appUser = new AppUser();

        BeanUtils.copyProperties(appUserDto, appUser);

        UserDetails userDetails = appUserService.loadUserByUsername(appUser.getUserName());

        String jwtToken = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity
                .ok()
                .body(new JwtResponse(jwtToken));
    }


    @GetMapping("/logout/{userName}")
    public ResponseEntity<Object> logout(@PathVariable String userName) {
        boolean logout = appUserService.logout(userName);
        if (logout) {
            return ResponseEntity.ok("Đăng xuất thành công");
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Đăng xuất thất bại, vui lòng chờ trong giây lát");
    }
    @GetMapping("/getUser")
    private ResponseEntity<Object> getUser(@RequestParam(name = "user") String userName){
        AppUser appUser = appUserService.findByName(userName);
        if (appUser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }
}
