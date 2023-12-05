//package com.example.mh_cosmetic.model.order;
//
//import com.example.mh_cosmetic.model.product.Product;
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
//public class OrderDetail {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;
//    private Double priceOfOrder;
//    private Integer quantity;
//
//    @ManyToOne
//    @JoinColumn(name = "idProduct", referencedColumnName = "id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "idOrder", referencedColumnName = "id")
//    private Order order;
//
//}
