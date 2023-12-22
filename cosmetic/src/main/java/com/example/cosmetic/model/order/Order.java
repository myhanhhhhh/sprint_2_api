package com.example.cosmetic.model.order;

import com.example.cosmetic.model.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dateOfOrder;
    private String timeOfOrder;
    private Double totalMoney;
    private Integer paymentStatus;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private AppUser appUser;
}
