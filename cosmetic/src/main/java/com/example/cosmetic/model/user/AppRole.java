package com.example.cosmetic.model.user;//package com.example.mh_cosmetic.model.user;

import javax.persistence.*;

import java.util.Set;
@Entity
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Boolean isDeleted;
    @OneToMany(mappedBy = "appRole")
    private Set<UserRole> userRoles;

    public AppRole() {
    }

    public AppRole(Integer id, String name, Boolean isDeleted, Set<UserRole> userRoles) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.userRoles = userRoles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }


}
