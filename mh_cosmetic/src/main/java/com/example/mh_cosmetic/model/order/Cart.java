//package com.example.mh_cosmetic.model.order;
//
//import com.example.mh_cosmetic.model.product.Product;
//import com.example.mh_cosmetic.model.user.AppUser;
//import javax.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private Integer quantityOfOrder;
//
//    @ManyToOne
//    @JoinColumn(name = "idProduct", referencedColumnName = "id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "idUser", referencedColumnName = "id")
//    private AppUser appUser;
//
//}
