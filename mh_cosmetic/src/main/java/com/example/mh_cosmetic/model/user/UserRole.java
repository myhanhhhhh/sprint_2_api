//package com.example.mh_cosmetic.model.user;
//
//import javax.persistence.*;
//
//@Entity
//public class UserRole {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "app_role_id", referencedColumnName = "id")
//    private AppRole appRole;
//
//    @ManyToOne
//    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
//    private AppUser appUser;
//
//    public UserRole() {
//    }
//
//    public UserRole(Integer id, AppUser appUser, AppRole appRole) {
//        this.id = id;
//        this.appUser = appUser;
//        this.appRole = appRole;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public AppRole getAppRole() {
//        return appRole;
//    }
//
//    public void setAppRole(AppRole appRole) {
//        this.appRole = appRole;
//    }
//
//    public AppUser getAppUser() {
//        return appUser;
//    }
//
//    public void setAppUser(AppUser appUser) {
//        this.appUser = appUser;
//    }
//}
