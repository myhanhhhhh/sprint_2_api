package com.example.cosmetic.model.customer;//package com.example.mh_cosmetic.model.customer;

import com.example.cosmetic.model.user.AppUser;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String birthday;
    private Integer gender;
    private String identity;
    private String phone;
    private String address;

    @OneToOne
    @JoinColumn(name = "idUser",referencedColumnName = "id")
    private AppUser appUser;
}
