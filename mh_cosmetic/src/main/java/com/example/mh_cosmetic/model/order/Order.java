//package com.example.mh_cosmetic.model.order;
//
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
//@Table(name = "`order`")
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String dateOfOrder;
//    private String timeOfOrder;
//    private Double totalMoney;
//    private Integer paymentStatus;
//
//    @ManyToOne
//    @JoinColumn(name = "idUser", referencedColumnName = "id")
//    private AppUser appUser;
//}
