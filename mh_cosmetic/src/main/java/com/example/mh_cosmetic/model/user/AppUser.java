//package com.example.mh_cosmetic.model.user;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import javax.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Set;
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//public class AppUser {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String userName;
//    private String password;
//    private String email;
//    private Boolean isOnline;
//    private Boolean isDeleted;
//
//    @JsonBackReference
//    @OneToMany(mappedBy = "appUser")
//    private Set<UserRole> userRoles;
//}
